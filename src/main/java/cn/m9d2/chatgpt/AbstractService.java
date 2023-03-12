package cn.m9d2.chatgpt;

import cn.m9d2.chatgpt.config.OpenAIProperties;
import cn.m9d2.chatgpt.framwork.excption.OpenAIException;
import cn.m9d2.chatgpt.framwork.interceptor.AuthorizationInterceptor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class AbstractService implements OpenAIService {

    private static Retrofit retrofit = null;
    protected AuthorizationInterceptor interceptor;
    protected OpenAIProperties properties;
    protected String apiKey;
    protected OpenAIClient client;
    protected OkHttpClient okHttpClient;

    public AbstractService(AuthorizationInterceptor interceptor, OpenAIProperties properties) {
        this.interceptor = interceptor;
        this.properties = properties;
        this.apiKey = properties.getApiKey();
        this.okHttpClient = okHttpClient();
        this.client = getRetrofit().create(OpenAIClient.class);
    }

    protected <T> void handleErrorResponse(Response<T> response) {
        try (ResponseBody errorBody = response.errorBody()) {
            if (errorBody == null) {
                throw new OpenAIException("response errorBody is null");
            }
            String errorMsg = errorBody.string();
            throw new OpenAIException(errorMsg);
        } catch (IOException e) {
            throw new OpenAIException(e.getMessage());
        }
    }

    private synchronized Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(OpenAIClient.URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private OkHttpClient okHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (properties.getProxy().getEnable()) {
            builder.proxy(new Proxy(properties.getProxy().getType(),
                    new InetSocketAddress(properties.getProxy().getHostname(), properties.getProxy().getPort())));
        }
        builder.readTimeout(properties.getReadTimeout(), TimeUnit.MILLISECONDS);
        builder.connectTimeout(properties.getConnectTimeout(), TimeUnit.MILLISECONDS);
        builder.addInterceptor(interceptor);
        return builder.build();
    }

}
