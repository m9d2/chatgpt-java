package cn.m9d2.chatgpt.service.impl;

import cn.m9d2.chatgpt.AbstractService;
import cn.m9d2.chatgpt.config.OpenAIProperties;
import cn.m9d2.chatgpt.framwork.interceptor.AuthorizationInterceptor;
import cn.m9d2.chatgpt.model.images.Images;
import cn.m9d2.chatgpt.model.images.ImagesResponse;
import cn.m9d2.chatgpt.service.ImagesService;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class ImagesServiceImpl extends AbstractService implements ImagesService {

    public ImagesServiceImpl(AuthorizationInterceptor interceptor, OpenAIProperties properties) {
        super(interceptor, properties);
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
