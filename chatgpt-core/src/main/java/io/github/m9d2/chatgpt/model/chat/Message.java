package io.github.m9d2.chatgpt.model.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("role")
    private String role;

    @JsonProperty("content")
    private String content;

}
