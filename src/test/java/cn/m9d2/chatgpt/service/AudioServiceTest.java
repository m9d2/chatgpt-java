package cn.m9d2.chatgpt.service;

import cn.m9d2.chatgpt.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

public class AudioServiceTest extends BaseTest {

    @Test
    public void transcriptions() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:introduce.m4a");
        String text = audioService.transcriptions(file);
        System.out.println(text);
        Assert.assertNotNull(text);
    }

    @Test
    public void translations() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:introduce.m4a");
        String text = audioService.translations(file);
        System.out.println(text);
        Assert.assertNotNull(text);
    }
}