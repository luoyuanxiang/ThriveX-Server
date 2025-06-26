package liuyuyang.net.web.service;

import java.util.Map;

public interface OauthService {
    Map<String, Object> authGithubLogin(String code);

    void bindGithubLogin(String code, String userId);

    String getGithubAuthToken(String code);
}
