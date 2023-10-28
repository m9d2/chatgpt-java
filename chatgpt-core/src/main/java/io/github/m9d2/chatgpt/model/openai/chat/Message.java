package io.github.m9d2.chatgpt.model.openai.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The role of the messages author. One of system, user, assistant, or function.
     */
    @JsonProperty("role")
    private String role;

    /**
     * The contents of the message.
     */
    @JsonProperty("content")
    private String content;

    @Getter
    @AllArgsConstructor
    public enum Role {

        SYSTEM("system"),
        USER("user"),
        ASSISTANT("assistant");

        private final String value;

    }

}
