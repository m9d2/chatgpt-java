package cn.m9d2.chatgpt.framwork.interceptor;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class AuthorizationInterceptor implements Interceptor {

    private final String apiKey;

    public AuthorizationInterceptor(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request authorised = originalRequest.newBuilder()
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("Content-Type", "application/json")
                .build();
        return chain.proceed(authorised);
    }

}