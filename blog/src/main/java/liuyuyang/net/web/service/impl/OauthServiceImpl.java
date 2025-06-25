package liuyuyang.net.web.service.impl;

import liuyuyang.net.common.properties.OAuthProperties;
import liuyuyang.net.web.service.OauthService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class OauthServiceImpl implements OauthService {
    @Resource
    private OAuthProperties oAuthProperties;

    public String githubLogin(String code) {
        String clientId = oAuthProperties.getGithub().getClientId();
        String clientSecret = oAuthProperties.getGithub().getClientSecret();

        return "";
    }
}
