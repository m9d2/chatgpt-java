package io.github.m9d2.chatgpt.framwork.interceptor;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class AuthorizationInterceptor implements Interceptor {

    private final String apiKey;

    private static volatile AuthorizationInterceptor interceptor;

    private AuthorizationInterceptor() {
        apiKey = null;
    }

    private AuthorizationInterceptor(String apiKey) {
        this.apiKey = apiKey;
    }

    public static AuthorizationInterceptor getInstance(String apiKey) {
        if (interceptor == null) {
            synchronized (AuthorizationInterceptor.class) {
                if (interceptor == null) {
                    return new AuthorizationInterceptor(apiKey);
                }
            }
        }
        return interceptor;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request authorised = originalRequest.newBuilder().addHeader("Authorization", "Bearer " + apiKey).build();
        return chain.proceed(authorised);
    }

}