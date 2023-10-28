package io.github.m9d2.chatgpt.service;

import io.github.m9d2.chatgpt.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.Locale;

public class AudioServiceTest extends BaseTest {

    @Test
    public void transcriptions() {
        URL url = AudioServiceTest.class.getClassLoader().getResource("introduce.m4a");
        if (url == null) {
            Assert.fail();
        }
        File file = new File(url.getFile());
        String text = audioService.transcriptions(file);
        System.out.println(text);
        Assert.assertNotNull(text);
    }

    @Test
    public void translations() {
        URL url = AudioServiceTest.class.getClassLoader().getResource("introduce.m4a");
        if (url == null) {
            Assert.fail();
        }
        File file = new File(url.getFile());
        String text = audioService.translations(file);
        System.out.println(text);
        Assert.assertNotNull(text);
    }
}