package cn.m9d2.chatgpt.model.chat;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompletionsResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String object;

    private Long created;

    private String model;

    private List<Choice> choices;

    private Usage usage;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Choice {

        private Double index;

        private Message message;

        @SerializedName("finish_reason")
        private String finishReason;

    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Usage {

        @SerializedName("prompt_tokens")
        private Double promptTokens;

        @SerializedName("completion_tokens")
        private Double completionTokens;

        @SerializedName("total_tokens")
        private Double totalTokens;

    }
}
