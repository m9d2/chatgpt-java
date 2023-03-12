package cn.m9d2.chatgpt.model.images;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ImagesResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long created;

    private List<ImagesData> data;

    @Data
    public static class ImagesData {

        private String url;

    }
}
