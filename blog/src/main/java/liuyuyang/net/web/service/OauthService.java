package liuyuyang.net.web.service;

public interface OauthService {
    void githubLogin(String code);
    String getGithubAuthToken(String code);
}
