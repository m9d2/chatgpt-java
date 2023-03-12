package cn.m9d2.chatgpt.service;

import cn.m9d2.chatgpt.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class AudioServiceTest extends BaseTest {

    @Test
    public void transcriptions() {
        String file = "test.m4a";
        String text = audioService.transcriptions(new File(file));
        System.out.println(text);
        Assert.assertNotNull(text);
    }

    @Test
    public void translations() {
        String file = "test.m4a";
        String text = audioService.translations(new File(file));
        System.out.println(text);
        Assert.assertNotNull(text);
    }
}