package liuyuyang.net.web.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liuyuyang.net.common.config.WebClientConfig;
import liuyuyang.net.common.execption.CustomException;
import liuyuyang.net.common.properties.OAuthProperties;
import liuyuyang.net.web.service.OauthService;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class OauthServiceImpl implements OauthService {
    @Resource
    private OAuthProperties oAuthProperties;
    @Resource
    private WebClientConfig webClientConfig;

    public void githubLogin(String code) {
        String token = getGithubAuthToken(code);
        getGithubUserInfo(token);
    }

    // 拿到 Token
    public String getGithubAuthToken(String code) {
        String clientId = oAuthProperties.getGithub().getClientId();
        String clientSecret = oAuthProperties.getGithub().getClientSecret();

        // 创建请求体数据
        Map<String, String> requestData = new HashMap<>();
        requestData.put("client_id", clientId);
        requestData.put("client_secret", clientSecret);
        requestData.put("code", code);

        // 发起请求拿到 Token
        try {
            String accept = webClientConfig.webClient().post()
                    .uri("https://github.com/login/oauth/access_token")
                    .header("Accept", "application/json")
                    .body(Mono.just(requestData), Object.class)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            // 解析为自定义对象
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> res = mapper.readValue(accept, Map.class);

            // 处理失败请求
            if (accept != null && accept.contains("\"error\"")) {
                throw new CustomException(res.get("error_description"));
            }

            // 处理成功请求
            if (accept != null && accept.contains("\"access_token\"")) {
                String accessToken = res.get("access_token");
                return accessToken;
            }
        } catch (WebClientResponseException | JsonProcessingException e) {
            throw new CustomException(e.getMessage());
        }

        return null;
    }

    // 获取用户信息
    public void getGithubUserInfo(String token) {
        try {
            String accept = webClientConfig.webClient().get()
                    .uri("https://api.github.com/user")
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            System.out.println(accept);
            System.out.println(4444);
        } catch (WebClientResponseException e) {
            throw new CustomException(e.getMessage());
        }
    }
}
