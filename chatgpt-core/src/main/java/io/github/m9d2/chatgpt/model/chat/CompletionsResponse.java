package io.github.m9d2.chatgpt.model.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("id")
    private String id;

    @JsonProperty("object")
    private String object;

    @JsonProperty("created")
    private Long created;

    @JsonProperty("model")
    private String model;

    @JsonProperty("choices")
    private List<Choice> choices;

    @JsonProperty("usage")
    private Usage usage;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Choice {

        @JsonProperty("index")
        private Double index;

        @JsonProperty("message")
        private Message message;

        @JsonProperty("finish_reason")
        private String finishReason;

    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Usage {

        @JsonProperty("prompt_tokens")
        private Double promptTokens;

        @JsonProperty("completion_tokens")
        private Double completionTokens;

        @JsonProperty("total_tokens")
        private Double totalTokens;

    }
}
