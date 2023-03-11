package cn.m9d2.chatgpt.config;

import cn.m9d2.chatgpt.framwork.interceptor.AuthorizationInterceptor;
import cn.m9d2.chatgpt.service.ChatService;
import cn.m9d2.chatgpt.service.impl.ChatServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@AutoConfigureAfter(InterceptorAutoConfig.class)
@ConditionalOnMissingBean({ChatService.class})
@EnableConfigurationProperties(OpenAIProperties.class)
public class OpenAIServiceAutoConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenAIServiceAutoConfig.class);

    private final AuthorizationInterceptor interceptor;

    private final OpenAIProperties properties;

    public OpenAIServiceAutoConfig(AuthorizationInterceptor interceptor, OpenAIProperties properties) {
        this.interceptor = interceptor;
        this.properties = properties;
    }

    @Bean
    public ChatService chatService() {
        return new ChatServiceImpl(interceptor, properties);
    }

}
