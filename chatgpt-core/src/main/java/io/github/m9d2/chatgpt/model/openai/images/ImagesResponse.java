package io.github.m9d2.chatgpt.model.openai.images;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ImagesResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("created")
    private Long created;

    @JsonProperty("data")
    private List<ImagesData> data;

    @Data
    public static class ImagesData {

        @JsonProperty("url")
        private String url;

    }
}
