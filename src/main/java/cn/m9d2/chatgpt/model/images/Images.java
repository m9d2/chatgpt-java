package cn.m9d2.chatgpt.model.images;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Images implements Serializable {

    private static final long serialVersionUID = 1L;

    private String prompt;

    @Builder.Default
    private Integer n = 1;

    @Builder.Default
    private String size = "1024x1024";

}
