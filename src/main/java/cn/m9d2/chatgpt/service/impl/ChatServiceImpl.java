package cn.m9d2.chatgpt.service.impl;

import cn.m9d2.chatgpt.AbstractService;
import cn.m9d2.chatgpt.config.OpenAIProperties;
import cn.m9d2.chatgpt.framwork.interceptor.AuthorizationInterceptor;
import cn.m9d2.chatgpt.model.chat.Completions;
import cn.m9d2.chatgpt.model.chat.CompletionsResponse;
import cn.m9d2.chatgpt.service.ChatService;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class ChatServiceImpl extends AbstractService implements ChatService {

    public ChatServiceImpl(AuthorizationInterceptor interceptor, OpenAIProperties properties) {
        super(interceptor, properties);
    }

    @Override
    public CompletionsResponse completions(Completions completions) {
        Call<CompletionsResponse> call = client.completions(completions);
        Response<CompletionsResponse> response;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!response.isSuccessful()) {
            handlerError(response);
        }
        return response.body();
    }

}
