package io.github.m9d2.chatgpt.model.openai.images;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Images implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("prompt")
    private String prompt;

    @JsonProperty("n")
    @Builder.Default
    private Integer n = 1;

    @JsonProperty("size")
    @Builder.Default
    private String size = "1024x1024";

}
