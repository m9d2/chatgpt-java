# Java ChatGPT

[![GitHub release](https://img.shields.io/github/v/release/m9d2/chatgpt-java)](https://github.com/m9d2/chatgpt/releases)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.m9d2/chatgpt-spring-boot-starter)](https://mvnrepository.com/artifact/cn.m9d2.chatgpt/chatgpt)
[![GitHub license](https://img.shields.io/github/license/m9d2/chatgpt-java)](https://github.com/m9d2/chatgpt/blob/main/LICENSE)

Java版本openai客户端，支持Spring boot快速开始。

Languages： 中文 | [English](README.md)

&nbsp;

## 支持的APIs


- [Audio](https://platform.openai.com/docs/api-reference/audio)
- [Chat](https://platform.openai.com/docs/api-reference/chat)
- [Completions](https://platform.openai.com/docs/api-reference/completions)
- [images](https://platform.openai.com/docs/api-reference/images)


## 快速使用

### 引入依赖

```xml

<dependency>
    <groupId>io.github.m9d2</groupId>
    <artifactId>chatgpt-spring-boot-starter</artifactId>
    <version>{release-version}</version>
</dependency>
```

&nbsp;

### application.yml 配置

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

### 示例代码

```java

@SpringBootApplication
public class SampleApplication implements ApplicationRunner {

    @Autowired
    private OpenAIService openAIService;

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
        CompletionsResponse response = openAIService.completions(completions);
        for (CompletionsResponse.Choice choice : response.getChoices()) {
            System.out.print(choice.getMessage().getContent());
        }
    }
}
```
