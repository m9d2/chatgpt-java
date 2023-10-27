package io.github.m9d2.chatgpt;

import io.github.m9d2.chatgpt.service.AudioService;
import io.github.m9d2.chatgpt.service.BillingService;
import io.github.m9d2.chatgpt.service.ChatService;
import io.github.m9d2.chatgpt.service.ImagesService;
import io.github.m9d2.chatgpt.service.impl.AudioServiceImpl;
import io.github.m9d2.chatgpt.service.impl.BillingServiceImpl;
import io.github.m9d2.chatgpt.service.impl.ChatServiceImpl;
import io.github.m9d2.chatgpt.service.impl.ImagesServiceImpl;
import org.junit.Before;

import java.net.Proxy;

public class BaseTest {

    protected ChatService chatService;

    protected AudioService audioService;

    protected ImagesService imagesService;

    protected BillingService billingService;

    @Before
    public void before() {
        String key = System.getenv("OPENAI_KEY");
        ChatGptConfig config = new ChatGptConfig();
        config.setApiKey(key);
        config.setConnectTimeout(10000L);
        config.setReadTimeout(60000L);
        config.setProxy(getProxy());
        chatService = new ChatServiceImpl(config);
        audioService = new AudioServiceImpl(config);
        imagesService = new ImagesServiceImpl(config);
        billingService = new BillingServiceImpl(config);
    }

    private ChatGptConfig.Proxy getProxy() {
        ChatGptConfig.Proxy proxy = new ChatGptConfig.Proxy();
        proxy.setEnable(true);
        proxy.setPort(1087);
        proxy.setHostname("127.0.0.1");
        proxy.setType(Proxy.Type.HTTP);
        return proxy;
    }
}
