package io.github.m9d2.chatgpt;

import io.github.m9d2.chatgpt.service.AudioService;
import io.github.m9d2.chatgpt.service.ChatService;
import io.github.m9d2.chatgpt.service.ImagesService;
import io.github.m9d2.chatgpt.service.impl.AudioServiceImpl;
import io.github.m9d2.chatgpt.service.impl.ChatServiceImpl;
import io.github.m9d2.chatgpt.service.impl.ImagesServiceImpl;
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
    public ChatService chatService() {
        return new ChatServiceImpl(properties.getConfig());
    }

    @Bean
    public AudioService audioService() {
        return new AudioServiceImpl(properties.getConfig());
    }

    @Bean
    public ImagesService imagesService() {
        return new ImagesServiceImpl(properties.getConfig());
    }

}
