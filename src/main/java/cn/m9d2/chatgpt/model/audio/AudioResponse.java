package cn.m9d2.chatgpt.model.audio;

import lombok.Data;

import java.io.Serializable;

@Data
public class AudioResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String text;

}
