package io.github.m9d2.chatgpt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Data
@ConfigurationProperties(prefix = "chatpgt")
public class ChatGPTProperties {

    @NestedConfigurationProperty
    private ChatGptConfig config = new ChatGptConfig();

}
