package cn.m9d2.chatgpt.model.chat;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Completions implements Serializable {

    private static final long serialVersionUID = 1L;

    @Builder.Default
    private String model = Model.GPT_3_5_TURBO.getValue();

    private List<Message> messages;

    @Builder.Default
    private Boolean stream = false;

    private List<String> stop;

    @SerializedName("max_tokens")
    @Builder.Default
    private Integer maxTokens = 2048;

    @SerializedName("presence_penalty")
    @Builder.Default
    private double presencePenalty = 0;

    @SerializedName("frequency_penalty")
    @Builder.Default
    private double frequencyPenalty = 0;

    private String user;

    @Getter
    @AllArgsConstructor
    public enum Model {
        GPT_3_5_TURBO("gpt-3.5-turbo"),
        GPT_3_5_TURBO_0301("gpt-3.5-turbo");
        private final String value;

    }

}
