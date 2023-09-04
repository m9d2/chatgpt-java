# ChatGPT 

[![GitHub release](https://img.shields.io/github/v/release/m9d2/chatgpt)](https://github.com/m9d2/chatgpt/releases)
[![Maven Central](https://img.shields.io/maven-central/v/cn.m9d2.chatgpt/chatgpt)](https://mvnrepository.com/artifact/cn.m9d2.chatgpt/chatgpt)
[![GitHub license](https://img.shields.io/github/license/m9d2/chatgpt)](https://github.com/m9d2/chatgpt/blob/main/LICENSE)

Java版本openai api的SDK，支持spring boot快速开始。

&nbsp;

## 快速使用

### 一、引入依赖

```xml

<dependency>
    <groupId>cn.m9d2.chatgpt</groupId>
    <artifactId>chatgpt</artifactId>
    <version>{release-version}</version>
</dependency>
```

&nbsp;

### 二、application.yml 配置

```yaml
openai:
  api-key: "YOUR OWNER KEY"
  proxy:
    enable: true
    type: http
    hostname: 127.0.0.1
    port: 1087
  connect-timeout: 10000
  read-timeout: 30000
```

&nbsp;

### 三、示例代码

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
        message.setContent("你好");
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
&nbsp;

## Spring boot示例项目
[sample-chatgpt](https://github.com/m9d2/sample-chatgpt)
