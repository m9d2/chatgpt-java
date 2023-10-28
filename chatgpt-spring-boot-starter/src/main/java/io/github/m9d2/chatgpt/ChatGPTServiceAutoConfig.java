package io.github.m9d2.chatgpt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableConfigurationProperties(ChatGPTProperties.class)
public class ChatGPTServiceAutoConfig {

    private final ChatGPTProperties properties;

    public ChatGPTServiceAutoConfig(ChatGPTProperties properties) {
        log.info("ChatGPT - initialization...");
        this.properties = properties;
    }

    @Bean
    public OpenAIService openAIService() {
        return new OpenAIService(properties.getConfig());
    }

}
