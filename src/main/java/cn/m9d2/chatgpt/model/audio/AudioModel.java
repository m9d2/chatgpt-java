package cn.m9d2.chatgpt.model.audio;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AudioModel {
    WHISPER_1("whisper-1");

    private final String value;

}