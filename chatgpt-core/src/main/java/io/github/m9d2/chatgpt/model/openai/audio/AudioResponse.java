package io.github.m9d2.chatgpt.model.openai.audio;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class AudioResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("text")
    private String text;

}
