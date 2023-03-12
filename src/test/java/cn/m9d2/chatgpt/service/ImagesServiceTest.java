package cn.m9d2.chatgpt.service;

import cn.m9d2.chatgpt.BaseTest;
import cn.m9d2.chatgpt.model.images.Images;
import cn.m9d2.chatgpt.model.images.ImagesResponse;
import org.junit.Test;

public class ImagesServiceTest extends BaseTest {

    @Test
    public void generations() {
        String prompt = "A cute baby sea otter";
        Integer n = 2;
        String size = "1024x1024";
        Images images = Images.builder().prompt(prompt).n(n).size(size).build();
        ImagesResponse response = imagesService.generations(images);
        System.out.println(response.toString());
    }
}