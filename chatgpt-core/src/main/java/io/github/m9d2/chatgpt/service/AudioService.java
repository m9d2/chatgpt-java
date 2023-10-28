package io.github.m9d2.chatgpt.service;

import io.github.m9d2.chatgpt.model.audio.AudioModel;

import java.io.File;
import java.util.Locale;

public interface AudioService {

    /**
     * 语音转文字
     *
     * @param file 语音文件，支持格式：mp3, mp4, mpeg, mpga, m4a, wav, or webm
     * @return 转换后的结果
     */
    String transcriptions(File file, AudioModel model);

    /**
     *语音转文字
     *
     * @param file 语音文件
     * @return 转换后的结果
     */
    String transcriptions(File file);

    /**
     * 语音翻译成文字
     *
     * @param file 语音文件，支持格式：mp3, mp4, mpeg, mpga, m4a, wav, or webm
     * @return 翻译后的结果
     */
    String translations(File file, AudioModel model, Locale locale);

    /**
     *
     * @param file 语音文件
     * @return 翻译后的结果
     */
    String translations(File file);

    /**
     *
     * @param file 语音文件
     * @param locale locale
     * @return 翻译后的结果
     */
    String translations(File file, Locale locale);

}
