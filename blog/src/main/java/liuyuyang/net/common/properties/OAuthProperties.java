package liuyuyang.net.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "oauth")  // 对应 oauth.github 和 oauth.gitee
public class OAuthProperties {
    private GithubConfig github;
    private GiteeConfig gitee;

    @Data
    public static class GithubConfig {
        private String clientId;
        private String clientSecret;
    }

    @Data
    public static class GiteeConfig {
        private String clientId;
        private String clientSecret;
    }
}