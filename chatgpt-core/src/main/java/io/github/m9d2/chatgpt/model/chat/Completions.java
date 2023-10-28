package io.github.m9d2.chatgpt.model.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Completions implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("model")
    @Builder.Default
    private String model = Model.GPT_3_5_TURBO.getValue();

    @JsonProperty("messages")
    private List<Message> messages;

    @JsonProperty("stream")
    @Builder.Default
    private Boolean stream = false;

    @JsonProperty("stop")
    private List<String> stop;

    @JsonProperty("max_tokens")
    @Builder.Default
    private Integer maxTokens = 2048;

    @JsonProperty("presence_penalty")
    @Builder.Default
    private double presencePenalty = 0;

    @JsonProperty("frequency_penalty")
    @Builder.Default
    private double frequencyPenalty = 0;

    @JsonProperty("user")
    private String user;

    @Getter
    @AllArgsConstructor
    public enum Model {
        GPT_3_5_TURBO("gpt-3.5-turbo"),
        GPT_3_5_TURBO_0613("gpt-3.5-turbo-0613"),
        GPT_3_5_TURBO_16K("gpt-3.5-turbo-16k"),
        GPT_3_5_TURBO_16K_0613("gpt-3.5-turbo-16k-0613"),
        GPT_4("gpt-4"),
        GPT_4_32K("gpt-4-32k"),
        GPT_4_32K_0613("gpt-4-32k-0613"),
        ;
        private final String value;

    }

}
