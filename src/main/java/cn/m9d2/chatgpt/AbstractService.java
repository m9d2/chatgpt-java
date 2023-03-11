package cn.m9d2.chatgpt;

import cn.m9d2.chatgpt.config.OpenAIProperties;
import cn.m9d2.chatgpt.framwork.excption.OpenAIException;
import cn.m9d2.chatgpt.framwork.interceptor.AuthorizationInterceptor;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

public abstract class AbstractService implements OpenAIService {

    protected final static Logger LOGGER = LoggerFactory.getLogger(AbstractService.class);

    protected AuthorizationInterceptor interceptor;

    protected OpenAIProperties properties;

    protected OpenAIClient client;

    private static Retrofit retrofit = null;

    public AbstractService(AuthorizationInterceptor interceptor, OpenAIProperties properties) {
        this.interceptor = interceptor;
        this.properties = properties;
        this.client = getRetrofit().create(OpenAIClient.class);
    }

    protected <T> void handlerError(Response<T> response) {
        String errorMsg;
        try {
            if (response.errorBody() == null) {
                throw new RuntimeException();
            }
            errorMsg = response.errorBody().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        throw new OpenAIException(errorMsg);
    }

    private synchronized Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(OpenAIClient.URL)
                    .client(httpClientBuilder().build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private OkHttpClient.Builder httpClientBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (properties.getProxy().getEnable()) {
            builder.proxy(new Proxy(properties.getProxy().getType(),
                    new InetSocketAddress(properties.getProxy().getHostname(), properties.getProxy().getPort())));
        }
        builder.readTimeout(properties.getReadTimeout(), TimeUnit.MILLISECONDS);
        builder.connectTimeout(properties.getConnectTimeout(), TimeUnit.MILLISECONDS);
        builder.addInterceptor(interceptor);
        return builder;
    }

}
