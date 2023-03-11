package cn.m9d2.chatgpt.config;

import cn.m9d2.chatgpt.framwork.interceptor.AuthorizationInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(OpenAIProperties.class)
@ConditionalOnMissingBean(AuthorizationInterceptor.class)
public class InterceptorAutoConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterceptorAutoConfig.class);

    private final OpenAIProperties properties;

    public InterceptorAutoConfig(OpenAIProperties properties) {
        LOGGER.info("ChatGPT: initializing");
        this.properties = properties;
    }

    @Bean
    public AuthorizationInterceptor authorizationInterceptor() {
        return new AuthorizationInterceptor(properties.getApiKey());
    }
}
