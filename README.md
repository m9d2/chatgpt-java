# Java ChatGPT

[![GitHub release](https://img.shields.io/github/v/release/m9d2/chatgpt)](https://github.com/m9d2/chatgpt/releases)
[![Maven Central](https://img.shields.io/maven-central/v/cn.m9d2.chatgpt/chatgpt)](https://mvnrepository.com/artifact/cn.m9d2.chatgpt/chatgpt)
[![GitHub license](https://img.shields.io/github/license/m9d2/chatgpt)](https://github.com/m9d2/chatgpt/blob/main/LICENSE)

This library provides unofficial Java clients for OpenAI's apis.

Languages： English | [中文](README_CN.md)

&nbsp;

## Supported APIs

- [Audio](https://platform.openai.com/docs/api-reference/audio)
- [Chat](https://platform.openai.com/docs/api-reference/chat)
- [Completions](https://platform.openai.com/docs/api-reference/completions)
- [images](https://platform.openai.com/docs/api-reference/images)

## Quick start

### Introducing Dependencies

```xml

<dependency>
    <groupId>io.github.m9d2</groupId>
    <artifactId>chatgpt-spring-boot-starter</artifactId>
    <version>{release-version}</version>
</dependency>
```

&nbsp;

### Configure application.yml

```yaml
chatpgt:
  config:
    api-key: "YOUR OWNER KEY"
    proxy:
      enable: true
      type: http
      hostname: 127.0.0.1
      port: 1087
    connect-timeout: 60000
    read-timeout: 60000
```

&nbsp;

### Sample

```java

@SpringBootApplication
public class SampleApplication implements ApplicationRunner {

    @Autowired
    private ChatService chatService;

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Completions completions = new Completions();
        List<Message> messages = new ArrayList<>();
        Message message = new Message();
        message.setContent("hello");
        message.setRole("user");
        messages.add(message);
        completions.setMessages(messages);
        CompletionsResponse response = chatService.completions(completions);
        for (CompletionsResponse.Choice choice : response.getChoices()) {
            System.out.print(choice.getMessage().getContent());
        }
    }
}
```
