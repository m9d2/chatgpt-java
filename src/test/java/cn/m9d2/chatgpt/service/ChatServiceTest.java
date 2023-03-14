package cn.m9d2.chatgpt.service;

import cn.m9d2.chatgpt.BaseTest;
import cn.m9d2.chatgpt.MessageListener;
import cn.m9d2.chatgpt.model.chat.Completions;
import cn.m9d2.chatgpt.model.chat.CompletionsResponse;
import cn.m9d2.chatgpt.model.chat.Message;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ChatServiceTest extends BaseTest {

    @Test
    public void testCompletions() {
        String content = "你好";
        String role = "user";
        String user = "user1234";
        List<Message> messages = new ArrayList<>();
        messages.add(Message.builder().content(content).role(role).build());
        Completions completions = Completions.builder()
                .messages(messages)
                .user(user)
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
        String content = "写一篇赞美母爱的200字文章";
        String role = "user";
        String user = "user1234";
        List<Message> messages = new ArrayList<>();
        messages.add(Message.builder().content(content).role(role).build());
        Completions completions = Completions.builder()
                .messages(messages)
                .user(user)
                .build();
        CountDownLatch countDownLatch = new CountDownLatch(1);
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