package liuyuyang.net.dto.oauth;

import lombok.Data;

@Data
public class GitHubDTO {
    private String client_id;
    private String client_secret;
    private String code;
}
