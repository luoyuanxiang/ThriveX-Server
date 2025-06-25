package liuyuyang.net.web.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import liuyuyang.net.common.config.WebClientConfig;
import liuyuyang.net.common.properties.OAuthProperties;
import liuyuyang.net.web.service.OauthService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class OauthServiceImpl implements OauthService {
    @Resource
    private OAuthProperties oAuthProperties;
    @Resource
    private WebClientConfig webClientConfig;

    public String githubLogin(String code) {
        String clientId = oAuthProperties.getGithub().getClientId();
        String clientSecret = oAuthProperties.getGithub().getClientSecret();
        return "";
    }
}
