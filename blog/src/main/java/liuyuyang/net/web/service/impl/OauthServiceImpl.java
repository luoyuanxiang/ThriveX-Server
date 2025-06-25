package liuyuyang.net.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liuyuyang.net.common.config.WebClientConfig;
import liuyuyang.net.common.execption.CustomException;
import liuyuyang.net.common.properties.OAuthProperties;
import liuyuyang.net.model.User;
import liuyuyang.net.web.mapper.UserMapper;
import liuyuyang.net.web.service.OauthService;
import liuyuyang.net.web.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OauthServiceImpl implements OauthService {
    @Resource
    private OAuthProperties oAuthProperties;
    @Resource
    private WebClientConfig webClientConfig;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserService userService;

    public Map<String, Object> githubLogin(String code) {
        String token = getGithubAuthToken(code);
        String userId = getGithubUserId(token);
        System.out.println(1111);
        System.out.println(userId);

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getGithubId, userId);
        User user = userMapper.selectOne(lambdaQueryWrapper);
        if (user == null) throw new CustomException("该用户未绑定 GitHub 登录");

        return userService.loginLogic(user);
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

    // 通过 Token 拿到用户 Id
    public String getGithubUserId(String token) {
        try {
            String accept = webClientConfig.webClient().get()
                    .uri("https://api.github.com/user")
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> res = mapper.readValue(accept, Map.class);
            return res.get("id").toString();
        } catch (WebClientResponseException | JsonProcessingException e) {
            throw new CustomException(e.getMessage());
        }
    }
}
