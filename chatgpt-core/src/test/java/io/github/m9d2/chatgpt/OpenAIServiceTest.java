package io.github.m9d2.chatgpt;

import io.github.m9d2.chatgpt.model.chat.Completions;
import io.github.m9d2.chatgpt.model.chat.CompletionsResponse;
import io.github.m9d2.chatgpt.model.chat.Message;
import io.github.m9d2.chatgpt.model.files.File;
import io.github.m9d2.chatgpt.model.files.FileResponse;
import io.github.m9d2.chatgpt.model.images.Images;
import io.github.m9d2.chatgpt.model.images.ImagesResponse;
import org.junit.*;

import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Ignore
public class OpenAIServiceTest {

    private OpenAIService openAIService;

    @Before
    public void before() {
        String key = System.getenv("OPENAI_KEY");
        ChatGptConfig config = new ChatGptConfig();
        config.setApiKey(key);
        config.setConnectTimeout(10000L);
        config.setReadTimeout(60000L);
        config.setProxy(getProxy());
        openAIService = new OpenAIService(config);

        uploadFile();
    }

    private ChatGptConfig.Proxy getProxy() {
        ChatGptConfig.Proxy proxy = new ChatGptConfig.Proxy();
        proxy.setEnable(true);
        proxy.setPort(1087);
        proxy.setHostname("127.0.0.1");
        proxy.setType(Proxy.Type.HTTP);
        return proxy;
    }

    private void uploadFile() {
        URL url = OpenAIServiceTest.class.getClassLoader().getResource("data.jsonl");
        if (url == null) {
            Assert.fail();
        }
        java.io.File file = new java.io.File(url.getFile());
        openAIService.uploadFile(file);
    }

    @Test
    public void testCompletions() {
        List<Message> messages = new ArrayList<>();
        messages.add(Message.builder().content("你是linux服务器，我输入命令，你执行命令，并返回执行结果").role(Message.Role.USER.getValue()).build());
        messages.add(Message.builder().content("ls").role(Message.Role.ASSISTANT.getValue()).build());
        Completions completions = Completions.builder().messages(messages).user("user").build();
        CompletionsResponse response = openAIService.completions(completions);
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
        Completions completions = Completions.builder().messages(messages).user(user).build();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        openAIService.completions(completions, new MessageListener() {
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

    @Test
    public void testTranscriptions() {
        URL url = OpenAIServiceTest.class.getClassLoader().getResource("introduce.m4a");
        if (url == null) {
            Assert.fail();
        }
        java.io.File file = new java.io.File(url.getFile());
        String text = openAIService.transcriptions(file);
        System.out.println(text);
        Assert.assertNotNull(text);
    }

    @Test
    public void testTranslations() {
        URL url = OpenAIServiceTest.class.getClassLoader().getResource("introduce.m4a");
        if (url == null) {
            Assert.fail();
        }
        java.io.File file = new java.io.File(url.getFile());
        String text = openAIService.translations(file);
        System.out.println(text);
        Assert.assertNotNull(text);
    }

    @Test
    public void generations() {
        String prompt = "A cute baby sea otter";
        Integer n = 2;
        String size = "1024x1024";
        Images images = Images.builder().prompt(prompt).n(n).size(size).build();
        ImagesResponse response = openAIService.generations(images);
        System.out.println(response.toString());
    }

    @Test
    public void testFiles() {
        FileResponse list = openAIService.files();
        System.out.println(list);
    }

    @Test
    public void testGetFile() {
        FileResponse list = openAIService.files();
        for (File file : list.getData()) {
            if (file.getFilename().equals("data.jsonl")) {
                if (file.getStatus().equals("uploaded")) {
                    File f = openAIService.getFile(file.getId());
                    System.out.println(f);
                    Assert.assertNotNull(f);
                }

            }
        }
    }

    @Test
    @Ignore
    public void testGetFileContent() {
        FileResponse list = openAIService.files();
        for (File file : list.getData()) {
            if (file.getFilename().equals("data.jsonl")) {
                String content = openAIService.getFileContent(file.getId());
                System.out.println(content);
                Assert.assertNotNull(content);
            }
        }
    }

    @After
    public void testDeleteFile() {
        FileResponse list = openAIService.files();
        for (io.github.m9d2.chatgpt.model.files.File file : list.getData()) {
            if (file.getFilename().equals("data.jsonl")) {
                if (file.getStatus().equals("uploaded")) {
                    openAIService.deleteFile(file.getId());
                }
            }
        }
    }

}