package io.github.m9d2.chatgpt.service.impl;

import io.github.m9d2.chatgpt.AbstractService;
import io.github.m9d2.chatgpt.ChatGptConfig;
import io.github.m9d2.chatgpt.framwork.interceptor.AuthorizationInterceptor;
import io.github.m9d2.chatgpt.model.images.Images;
import io.github.m9d2.chatgpt.model.images.ImagesResponse;
import io.github.m9d2.chatgpt.service.ImagesService;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class ImagesServiceImpl extends AbstractService implements ImagesService {

    public ImagesServiceImpl(ChatGptConfig config) {
        super(config);
    }

    @Override
    public ImagesResponse generations(Images images) {
        Call<ImagesResponse> call = client.generations(images);
        Response<ImagesResponse> response;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!response.isSuccessful()) {
            handleErrorResponse(response);
        }
        return response.body();
    }

}
