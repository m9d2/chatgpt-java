package cn.m9d2.chatgpt.service;

import cn.m9d2.chatgpt.BaseTest;
import cn.m9d2.chatgpt.ConsumerListener;
import cn.m9d2.chatgpt.model.chat.Completions;
import cn.m9d2.chatgpt.model.chat.CompletionsResponse;
import cn.m9d2.chatgpt.model.chat.Message;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
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
        for (CompletionsResponse.Choice choice : response.getChoices()) {
            System.out.println(choice.getMessage().getContent());
        }
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
        chatService.completions(completions, ChatEventListener::new);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Slf4j
    public static class ChatEventListener extends ConsumerListener {

        @Override
        public void onMessage(String data) {
            if (!data.equals("[DONE]")) {
                JsonObject jsonObject = (JsonObject) new JsonParser().parse(data);
                JsonObject choice = (JsonObject) jsonObject.getAsJsonArray("choices").get(0);
                JsonObject delta = choice.getAsJsonObject("delta");
                JsonElement jsonElement = delta.get("content");
                if (jsonElement != null) {
                    String content = jsonElement.getAsString();
                    System.out.print(content);
                }
            }
        }
    }
}