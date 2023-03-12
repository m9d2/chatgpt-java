package cn.m9d2.chatgpt.service.impl;

import cn.m9d2.chatgpt.AbstractService;
import cn.m9d2.chatgpt.config.OpenAIProperties;
import cn.m9d2.chatgpt.framwork.enums.ContentType;
import cn.m9d2.chatgpt.framwork.interceptor.AuthorizationInterceptor;
import cn.m9d2.chatgpt.model.audio.AudioModel;
import cn.m9d2.chatgpt.model.audio.AudioResponse;
import cn.m9d2.chatgpt.service.AudioService;
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

    private static final String MODEL_KEY = "model";

    private static final String FILE_KEY = "file";

    private static final String LANGUAGE_KEY = "language";

    public AudioServiceImpl(AuthorizationInterceptor interceptor, OpenAIProperties properties) {
        super(interceptor, properties);
    }

    @Override
    public String transcriptions(File file, AudioModel model) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(MODEL_KEY, model.getValue())
                .addPart(createFile(file))
                .build();
        Call<AudioResponse> call = this.client.transcriptions(requestBody);
        return getAudioResponse(call);
    }

    @Override
    public String transcriptions(File file) {
        return transcriptions(file, AudioModel.WHISPER_1);
    }

    @Override
    public String translations(File file, AudioModel model, Locale locale) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(MODEL_KEY, model.getValue())
                .addFormDataPart(LANGUAGE_KEY, locale.getLanguage())
                .addPart(createFile(file))
                .build();
        Call<AudioResponse> call = this.client.translations(requestBody);
        return getAudioResponse(call);
    }

    @Override
    public String translations(File file) {
        return translations(file, AudioModel.WHISPER_1, Locale.CHINESE);
    }

    @Override
    public String translations(File file, Locale locale) {
        return translations(file, AudioModel.WHISPER_1, locale);
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
        return MultipartBody.Part.createFormData(FILE_KEY, file.getName(), fileBody);
    }

}
