package top.luoyuanxiang.api.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssistantRequest {
    private String model;
    private List<Message> messages;
    private double temperature;
    private int max_tokens;
    private boolean stream;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Message {
        private String role;
        private String content;
    }
}

