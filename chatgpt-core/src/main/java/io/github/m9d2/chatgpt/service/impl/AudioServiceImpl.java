package io.github.m9d2.chatgpt.service.impl;

import io.github.m9d2.chatgpt.AbstractService;
import io.github.m9d2.chatgpt.ChatGptConfig;
import io.github.m9d2.chatgpt.framwork.constants.RequestParam;
import io.github.m9d2.chatgpt.framwork.enums.ContentType;
import io.github.m9d2.chatgpt.model.audio.AudioModel;
import io.github.m9d2.chatgpt.model.audio.AudioResponse;
import io.github.m9d2.chatgpt.service.AudioService;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

public class AudioServiceImpl extends AbstractService implements AudioService {

    public AudioServiceImpl(ChatGptConfig config) {
        super(config);
    }

    @Override
    public String transcriptions(File file) {
        return transcriptions(file, AudioModel.WHISPER_1, Locale.getDefault(), "");
    }

    @Override
    public String transcriptions(File file, Locale locale) {
        return transcriptions(file, AudioModel.WHISPER_1, locale, "");
    }

    @Override
    public String transcriptions(File file, Locale locale, String prompt) {
        return transcriptions(file, AudioModel.WHISPER_1, locale, prompt);
    }

    @Override
    public String transcriptions(File file, AudioModel model, Locale locale, String prompt) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(RequestParam.MODEL, model.getValue())
                .addPart(createFile(file))
                .addFormDataPart(RequestParam.LANGUAGE, locale.getLanguage())
                .addFormDataPart(RequestParam.PROMPT, prompt)
                .build();
        Call<AudioResponse> call = this.client.transcriptions(requestBody);
        return getAudioResponse(call);
    }

    @Override
    public String translations(File file) {
        return translations(file, AudioModel.WHISPER_1, "");
    }

    @Override
    public String translations(File file, String prompt) {
        return translations(file, AudioModel.WHISPER_1, prompt);
    }

    @Override
    public String translations(File file, AudioModel model, String prompt) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(RequestParam.MODEL, model.getValue())
                .addFormDataPart(RequestParam.PROMPT, prompt)
                .addPart(createFile(file))
                .build();
        Call<AudioResponse> call = this.client.translations(requestBody);
        return getAudioResponse(call);
    }

    private String getAudioResponse(Call<AudioResponse> call) {
        Response<AudioResponse> response;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!response.isSuccessful()) {
            handleErrorResponse(response);
        }
        return Objects.requireNonNull(response.body()).getText();
    }

    private MultipartBody.Part createFile(File file) {
        RequestBody fileBody = RequestBody.create(MediaType.parse(ContentType.MULTIPART.getValue()), file);
        return MultipartBody.Part.createFormData(RequestParam.FILE, file.getName(), fileBody);
    }

}
