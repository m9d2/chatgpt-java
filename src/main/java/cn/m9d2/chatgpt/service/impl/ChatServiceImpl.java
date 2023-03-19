package cn.m9d2.chatgpt.service.impl;

import cn.m9d2.chatgpt.AbstractService;
import cn.m9d2.chatgpt.MessageListener;
import cn.m9d2.chatgpt.OpenAIClient;
import cn.m9d2.chatgpt.config.OpenAIProperties;
import cn.m9d2.chatgpt.framwork.enums.ContentType;
import cn.m9d2.chatgpt.framwork.excption.OpenAIException;
import cn.m9d2.chatgpt.framwork.interceptor.AuthorizationInterceptor;
import cn.m9d2.chatgpt.model.chat.Completions;
import cn.m9d2.chatgpt.model.chat.CompletionsResponse;
import cn.m9d2.chatgpt.service.ChatService;
import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSources;
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
            throw new OpenAIException(e.getMessage());
        }
        if (!response.isSuccessful()) {
            handleErrorResponse(response);
        }
        return response.body();
    }

    @Override
    public <T extends MessageListener> void completions(Completions completions, T consumerListener) {
        completions.setStream(true);
        EventSource.Factory factory = EventSources.createFactory(this.okHttpClient);
        Request request = new Request.Builder()
                .url(OpenAIClient.URL + "chat/completions")
                .post(RequestBody.create(MediaType.parse(ContentType.JSON.getValue()), new Gson().toJson(completions)))
                .build();
        factory.newEventSource(request, consumerListener);
    }

}
