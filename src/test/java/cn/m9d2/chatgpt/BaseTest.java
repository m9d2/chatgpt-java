package cn.m9d2.chatgpt;

import cn.m9d2.chatgpt.config.OpenAIProperties;
import cn.m9d2.chatgpt.framwork.interceptor.AuthorizationInterceptor;
import cn.m9d2.chatgpt.service.AudioService;
import cn.m9d2.chatgpt.service.BillingService;
import cn.m9d2.chatgpt.service.ChatService;
import cn.m9d2.chatgpt.service.ImagesService;
import cn.m9d2.chatgpt.service.impl.AudioServiceImpl;
import cn.m9d2.chatgpt.service.impl.BillingServiceImpl;
import cn.m9d2.chatgpt.service.impl.ChatServiceImpl;
import cn.m9d2.chatgpt.service.impl.ImagesServiceImpl;
import org.junit.Before;

import java.net.Proxy;

public class BaseTest {

    protected AuthorizationInterceptor authorizationInterceptor;

    protected ChatService chatService;

    protected AudioService audioService;

    protected ImagesService imagesService;

    protected BillingService billingService;

    @Before
    public void before() {
        String key = System.getenv("OPENAI_KEY");
        OpenAIProperties properties = new OpenAIProperties();
        properties.setApiKey(key);
        properties.setConnectTimeout(10000L);
        properties.setReadTimeout(60000L);
        properties.setProxy(getProxy());
        authorizationInterceptor = new AuthorizationInterceptor(properties.getApiKey());
        chatService = new ChatServiceImpl(authorizationInterceptor, properties);
        audioService = new AudioServiceImpl(authorizationInterceptor, properties);
        imagesService = new ImagesServiceImpl(authorizationInterceptor, properties);
        billingService = new BillingServiceImpl(authorizationInterceptor, properties);
    }

    private OpenAIProperties.Proxy getProxy() {
        OpenAIProperties.Proxy proxy = new OpenAIProperties.Proxy();
        proxy.setEnable(true);
        proxy.setPort(1087);
        proxy.setHostname("127.0.0.1");
        proxy.setType(Proxy.Type.HTTP);
        return proxy;
    }
}
