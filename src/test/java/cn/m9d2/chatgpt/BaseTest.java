package cn.m9d2.chatgpt;

import cn.m9d2.chatgpt.config.OpenAIProperties;
import cn.m9d2.chatgpt.framwork.interceptor.AuthorizationInterceptor;
import cn.m9d2.chatgpt.service.AudioService;
import cn.m9d2.chatgpt.service.ChatService;
import cn.m9d2.chatgpt.service.impl.AudioServiceImpl;
import cn.m9d2.chatgpt.service.impl.ChatServiceImpl;
import org.junit.Before;

import java.net.Proxy;

public class BaseTest {

    protected AuthorizationInterceptor authorizationInterceptor;

    protected ChatService chatService;

    protected AudioService audioService;

    @Before
    public void before() {
        OpenAIProperties properties = new OpenAIProperties();
        properties.setApiKey("sk-...");
        properties.setConnectTimeout(10000L);
        properties.setReadTimeout(60000L);
        properties.setProxy(getProxy());
        authorizationInterceptor = new AuthorizationInterceptor(properties.getApiKey());
        chatService = new ChatServiceImpl(authorizationInterceptor, properties);
        audioService = new AudioServiceImpl(authorizationInterceptor, properties);
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
