package io.github.m9d2.chatgpt.service;

import io.github.m9d2.chatgpt.model.audio.AudioModel;

import java.io.File;
import java.util.Locale;

public interface AudioService {

    String transcriptions(File file);

    String transcriptions(File file, Locale locale);

    String transcriptions(File file, Locale locale, String prompt);

    /**
     * Transcribes audio into the input language.
     *
     * @param file   The audio file object (not file name) to transcribe,
     *               in one of these formats: flac, mp3, mp4, mpeg, mpga, m4a, ogg, wav, or webm.
     * @param model  ID of the model to use. Only whisper-1 is currently available.
     * @param locale The language of the input audio.
     * @param prompt An optional text to guide the model's style or continue a previous audio segment.
     *               The prompt should match the audio language.
     * @return Transcriptions result
     */
    String transcriptions(File file, AudioModel model, Locale locale, String prompt);

    String translations(File file);

    String translations(File file, String prompt);

    /**
     * Translates audio into English.
     *
     * @param file   The audio file object (not file name) translate,
     *               in one of these formats: flac, mp3, mp4, mpeg, mpga, m4a, ogg, wav, or webm.
     * @param model  ID of the model to use. Only whisper-1 is currently available.
     * @param prompt An optional text to guide the model's style or continue a previous audio segment.
     *               The prompt should be in English
     * @return Translations result
     */
    String translations(File file, AudioModel model, String prompt);

}
