package cn.m9d2.chatgpt.service;

import cn.m9d2.chatgpt.OpenAIService;
import cn.m9d2.chatgpt.model.images.Images;
import cn.m9d2.chatgpt.model.images.ImagesResponse;

public interface ImagesService extends OpenAIService {

    ImagesResponse generations(Images images);

}
