package top.luoyuanxiang.api.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import top.luoyuanxiang.api.entity.Assistant;
import top.luoyuanxiang.api.service.IAssistantService;
import top.luoyuanxiang.api.service.IDocumentService;
import top.luoyuanxiang.api.vo.AssistantRequest;
import top.luoyuanxiang.api.vo.DocumentMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文档处理
 *
 * @author luoyuanxiang
 */
@Slf4j
@Service
public class DocumentServiceImpl implements IDocumentService {

    @Resource
    private IAssistantService assistantService;
    @Resource
    private WebClient webClient;

    /**
     * 流程文档
     *
     * @param content   内容
     * @param operation 操作
     * @return {@link String }
     */
    @Override
    public Flux<String> processDocument(String content, String operation) {
        AssistantRequest assistantRequest = buildPrompt(content, operation);
        Assistant assistant = assistantService.getOne(Wrappers.lambdaQuery(Assistant.class)
                .eq(Assistant::getIsDefault, 1));
        if (Objects.isNull(assistant)) {
            throw new RuntimeException("未找到默认的助手");
        }
        assistantRequest.setModel(assistant.getModelId());
        if (assistantRequest.isStream()) {
            return callAssistantStreaming(assistant, assistantRequest);
        }
        return null;
    }

    /**
     * 生成标题和描述
     *
     * @param document 公文
     * @return {@link DocumentMeta }
     */
    @Override
    public Mono<DocumentMeta> generateTitleAndDescription(String document) {
        AssistantRequest assistantRequest = buildPrompt(document, "generateTitleAndDescription");
        Assistant assistant = assistantService.getOne(Wrappers.lambdaQuery(Assistant.class)
                .eq(Assistant::getIsDefault, 1));
        if (Objects.isNull(assistant)) {
            throw new RuntimeException("未找到默认的助手");
        }
        assistantRequest.setModel(assistant.getModelId());
        return callAssistantNonStreaming(assistant, assistantRequest);

    }

    /**
     * 提取 JSON 内容
     *
     * @param input 输入
     * @return {@link String }
     */
    private String extractJsonContent(String input) {
        Pattern pattern = Pattern.compile("```json\\s*(.*?)\\s*```", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(input);
        return matcher.find() ? matcher.group(1).trim() : input;
    }

    /**
     * 构建提示
     *
     * @param content   内容
     * @param operation 操作
     * @return {@link String }
     */
    private AssistantRequest buildPrompt(String content, String operation) {
        String prompt;
        int max_tokens;
        double temperature;
        boolean stream;
        switch (operation) {
            case "continue":
                prompt = "请续写以下文档，保持原有风格：\n" + content;
                max_tokens = 5000;
                temperature = 0.7;
                stream = true;
                break;
            case "optimize":
                prompt = "请优化以下文档，保持内容不变但更流畅：\n" + content;
                max_tokens = 5000;
                temperature = 0.3;
                stream = true;
                break;
            case "rewrite":
                prompt = "请重写以下文档，保持原有风格：\n" + content;
                max_tokens = 5000;
                temperature = 0.7;
                stream = true;
                break;
            case "translate":
                prompt = "请将以下文档翻译为英文：\n" + content;
                max_tokens = 5000;
                temperature = 0.7;
                stream = true;
                break;
            case "translate_zh":
                prompt = "请将以下文档翻译为中文：\n" + content;
                max_tokens = 5000;
                temperature = 0.7;
                stream = true;
                break;
            case "test":
                prompt = "测试连接";
                max_tokens = 4;
                temperature = 0.7;
                stream = true;
                break;
            case "generateTitleAndDescription":
                prompt = "请根据以下文章内容生成一个合适的标题和简短的简介：\n" +
                        "文章内容：\n" + content + "\n\n" +
                        "要求：\n" +
                        "1. 标题要简洁有力，不超过20个字\n" +
                        "2. 简介要概括文章主要内容，不超过100字\n" +
                        "3. 返回格式为JSON对象，包含title和description字段";
                max_tokens = 5000;
                temperature = 0.5;
                stream = false;
                break;
            default:
                throw new IllegalArgumentException("不支持的操作用户");
        }
        AssistantRequest assistantRequest = new AssistantRequest();
        List<AssistantRequest.Message> messages = new ArrayList<>();
        messages.add(new AssistantRequest.Message("system", "你是 Kimi，由 Moonshot AI 提供的人工智能助手，你更擅长中文和英文的对话。你会为用户提供安全，有帮助，准确的回答。"));
        messages.add(new AssistantRequest.Message("user", prompt));
        assistantRequest.setMessages(messages);
        assistantRequest.setTemperature(temperature);
        assistantRequest.setMax_tokens(max_tokens);
        assistantRequest.setStream(stream);
        return assistantRequest;
    }

    /**
     * 调用助理非流式处理
     *
     * @param assistant        助理
     * @param assistantRequest 助理请求
     * @return {@link Mono }<{@link String }>
     */
    public Mono<DocumentMeta> callAssistantNonStreaming(Assistant assistant, AssistantRequest assistantRequest) {
        return webClient.post()
                .uri(assistant.getBaseUrl() + "/v1/chat/completions")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + assistant.getApiKey())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(assistantRequest)
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> {
                    log.info("callAssistantNonStreaming Response: {}", response);
                    DocumentMeta documentMeta = new DocumentMeta();
                    // 解析JSON并获取字段
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        JsonNode jsonNode = mapper.readTree(response);
                        JsonNode choices = jsonNode.get("choices");
                        String text = choices.get(0).get("message").get("content").asText();
                        // 提取JSON内容
                        String jsonStr = extractJsonContent(text);
                        // 解析JSON并获取字段
                        JsonNode root = mapper.readTree(jsonStr);

                        String title = root.get("title").asText();
                        String description = root.get("description").asText();

                        documentMeta.setTitle(title);
                        documentMeta.setDescription(description);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return documentMeta;
                })
                .onErrorMap(e -> new RuntimeException("Assistant API调用失败: " + e.getMessage(), e));
    }

    /**
     * 调用助理流式传输
     *
     * @param assistant        助理
     * @param assistantRequest 助理请求
     * @return {@link Flux }<{@link String }>
     */
    public Flux<String> callAssistantStreaming(Assistant assistant, AssistantRequest assistantRequest) {
        return webClient.post()
                .uri(assistant.getBaseUrl() + "/v1/chat/completions")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + assistant.getApiKey())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(assistantRequest)
                .retrieve()
                .bodyToFlux(String.class)
                .map(response -> {
                    log.info("callAssistantStreaming Response: {}", response);
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(response);
                        return jsonNode.get("choices").get(0).get("delta").get("content").asText();
                    } catch (Exception e) {
                        return "";
                    }
                })
                .onErrorMap(e -> new RuntimeException("Assistant API流式调用失败: " + e.getMessage(), e));
    }
}
