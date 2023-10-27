package io.github.m9d2.chatgpt.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.m9d2.chatgpt.AbstractService;
import io.github.m9d2.chatgpt.ChatGptConfig;
import io.github.m9d2.chatgpt.MessageListener;
import io.github.m9d2.chatgpt.OpenAIClient;
import io.github.m9d2.chatgpt.framwork.enums.ContentType;
import io.github.m9d2.chatgpt.framwork.excption.ChatGPTException;
import io.github.m9d2.chatgpt.framwork.interceptor.AuthorizationInterceptor;
import io.github.m9d2.chatgpt.model.chat.Completions;
import io.github.m9d2.chatgpt.model.chat.CompletionsResponse;
import io.github.m9d2.chatgpt.service.ChatService;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSources;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class ChatServiceImpl extends AbstractService implements ChatService {

    private final ObjectMapper objectMapper;

    public ChatServiceImpl(ChatGptConfig config) {
        super(config);
        objectMapper = new ObjectMapper();
    }

    @Override
    public CompletionsResponse completions(Completions completions) {
        Call<CompletionsResponse> call = client.completions(completions);
        Response<CompletionsResponse> response;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new ChatGPTException(e.getMessage());
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
        Request request;
        try {
            request = new Request.Builder()
                    .url(OpenAIClient.URL + "chat/completions")
                    .post(RequestBody.create(MediaType.parse(ContentType.JSON.getValue()), objectMapper.writeValueAsString(completions)))
                    .build();
        } catch (JsonProcessingException e) {
            throw new ChatGPTException(e.getMessage());
        }
        factory.newEventSource(request, consumerListener);
    }

}
