package io.github.m9d2.chatgpt.service;

import io.github.m9d2.chatgpt.OpenAIService;
import io.github.m9d2.chatgpt.model.images.Images;
import io.github.m9d2.chatgpt.model.images.ImagesResponse;

public interface ImagesService extends OpenAIService {

    ImagesResponse generations(Images images);

}
