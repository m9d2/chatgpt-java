package io.github.m9d2.chatgpt.service;

import io.github.m9d2.chatgpt.BaseTest;
import io.github.m9d2.chatgpt.MessageListener;
import io.github.m9d2.chatgpt.model.chat.Completions;
import io.github.m9d2.chatgpt.model.chat.CompletionsResponse;
import io.github.m9d2.chatgpt.model.chat.Message;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ChatServiceTest extends BaseTest {

    @Test
    public void testCompletions() {
        List<Message> messages = new ArrayList<>();
        messages.add(Message.builder().content("你是linux服务器，我输入命令，你执行命令，并返回执行结果").role(Message.Role.USER.getValue()).build());
        messages.add(Message.builder().content("ls").role(Message.Role.ASSISTANT.getValue()).build());
        Completions completions = Completions.builder()
                .messages(messages)
                .user("user")
                .build();
        CompletionsResponse response = chatService.completions(completions);
        String text = null;
        for (CompletionsResponse.Choice choice : response.getChoices()) {
            text = choice.getMessage().getContent();
        }
        System.out.println(text);
        Assert.assertNotNull(text);
    }

    @Test
    public void testCompletionsForStream() {
        String content = "你好";
        String role = Message.Role.USER.getValue();
        String user = "user1234";
        List<Message> messages = new ArrayList<>();
        messages.add(Message.builder().content(content).role(role).build());
        Completions completions = Completions.builder()
                .messages(messages)
                .user(user)
                .build();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        chatService.completions(completions, new MessageListener() {
            @Override
            public void onMessaged(String data) {
                System.out.println(data);
            }

            @Override
            public void onDone() {
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}