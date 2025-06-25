package liuyuyang.net.web.service;

import java.util.Map;

public interface OauthService {
    Map<String, Object> githubLogin(String code);
    String getGithubAuthToken(String code);
}
