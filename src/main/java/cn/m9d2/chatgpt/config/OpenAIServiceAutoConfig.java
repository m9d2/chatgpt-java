package cn.m9d2.chatgpt.config;

import cn.m9d2.chatgpt.framwork.interceptor.AuthorizationInterceptor;
import cn.m9d2.chatgpt.service.AudioService;
import cn.m9d2.chatgpt.service.ChatService;
import cn.m9d2.chatgpt.service.ImagesService;
import cn.m9d2.chatgpt.service.impl.AudioServiceImpl;
import cn.m9d2.chatgpt.service.impl.ChatServiceImpl;
import cn.m9d2.chatgpt.service.impl.ImagesServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@AutoConfigureAfter(InterceptorAutoConfig.class)
@ConditionalOnMissingBean({ChatService.class})
@EnableConfigurationProperties(OpenAIProperties.class)
public class OpenAIServiceAutoConfig {

    private final AuthorizationInterceptor interceptor;

    private final OpenAIProperties properties;

    public OpenAIServiceAutoConfig(AuthorizationInterceptor interceptor, OpenAIProperties properties) {
        log.info("ChatGPT - initialization...");
        this.interceptor = interceptor;
        this.properties = properties;
    }

    @Bean
    public ChatService chatService() {
        return new ChatServiceImpl(interceptor, properties);
    }

    @Bean
    public AudioService audioService() {
        return new AudioServiceImpl(interceptor, properties);
    }

    @Bean
    public ImagesService imagesService() {
        return new ImagesServiceImpl(interceptor, properties);
    }

}
