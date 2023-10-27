package io.github.m9d2.chatgpt;

import io.github.m9d2.chatgpt.framwork.excption.ChatGPTException;
import io.github.m9d2.chatgpt.framwork.interceptor.AuthorizationInterceptor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class AbstractService implements OpenAIService {

    private static Retrofit retrofit = null;
    protected ChatGptConfig config;
    protected String apiKey;
    protected OpenAIClient client;
    protected OkHttpClient okHttpClient;

    public AbstractService(ChatGptConfig config) {
        this.config = config;
        this.apiKey = config.getApiKey();
        this.okHttpClient = okHttpClient();
        this.client = getRetrofit().create(OpenAIClient.class);
    }

    protected <T> void handleErrorResponse(Response<T> response) {
        try (ResponseBody errorBody = response.errorBody()) {
            if (errorBody == null) {
                throw new ChatGPTException("response errorBody is null");
            }
            String errorMsg = errorBody.string();
            throw new ChatGPTException(errorMsg);
        } catch (IOException e) {
            throw new ChatGPTException(e.getMessage());
        }
    }

    private synchronized Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(OpenAIClient.URL).client(okHttpClient).addConverterFactory(JacksonConverterFactory.create()).build();
        }
        return retrofit;
    }

    private OkHttpClient okHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (config.getProxy().getEnable()) {
            builder.proxy(new Proxy(config.getProxy().getType(), new InetSocketAddress(config.getProxy().getHostname(), config.getProxy().getPort())));
        }
        builder.readTimeout(config.getReadTimeout(), TimeUnit.MILLISECONDS);
        builder.connectTimeout(config.getConnectTimeout(), TimeUnit.MILLISECONDS);
        builder.addInterceptor(AuthorizationInterceptor.getInstance(config.getApiKey()));
        return builder.build();
    }

}
