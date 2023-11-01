package io.github.m9d2.chatgpt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.m9d2.chatgpt.framwork.constants.RequestParam;
import io.github.m9d2.chatgpt.framwork.enums.ContentType;
import io.github.m9d2.chatgpt.framwork.excption.ChatGPTException;
import io.github.m9d2.chatgpt.framwork.interceptor.AuthorizationInterceptor;
import io.github.m9d2.chatgpt.model.audio.AudioModel;
import io.github.m9d2.chatgpt.model.audio.AudioResponse;
import io.github.m9d2.chatgpt.model.billing.BillingUsage;
import io.github.m9d2.chatgpt.model.billing.Subscription;
import io.github.m9d2.chatgpt.model.chat.Completions;
import io.github.m9d2.chatgpt.model.chat.CompletionsResponse;
import io.github.m9d2.chatgpt.model.files.File;
import io.github.m9d2.chatgpt.model.files.FileResponse;
import io.github.m9d2.chatgpt.model.images.Images;
import io.github.m9d2.chatgpt.model.images.ImagesResponse;
import okhttp3.*;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSources;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class OpenAIService {

    private final ChatGptConfig config;
    private final OpenAIClient client;
    private final OkHttpClient okHttpClient;

    private final ObjectMapper objectMapper;

    public OpenAIService(ChatGptConfig config) {
        this.config = config;
        this.okHttpClient = okHttpClient();
        objectMapper = new ObjectMapper();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(OpenAIClient.URL).client(okHttpClient).addConverterFactory(JacksonConverterFactory.create()).build();
        this.client = retrofit.create(OpenAIClient.class);
    }

    public String transcriptions(java.io.File file) {
        return transcriptions(file, AudioModel.WHISPER_1, Locale.getDefault(), "");
    }

    public String transcriptions(java.io.File file, Locale locale) {
        return transcriptions(file, AudioModel.WHISPER_1, locale, "");
    }

    public String transcriptions(java.io.File file, Locale locale, String prompt) {
        return transcriptions(file, AudioModel.WHISPER_1, locale, prompt);
    }

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
    public String transcriptions(java.io.File file, AudioModel model, Locale locale, String prompt) {
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

    public String translations(java.io.File file) {
        return translations(file, AudioModel.WHISPER_1, "");
    }

    public String translations(java.io.File file, String prompt) {
        return translations(file, AudioModel.WHISPER_1, prompt);
    }

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
    public String translations(java.io.File file, AudioModel model, String prompt) {
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

    private MultipartBody.Part createFile(java.io.File file) {
        RequestBody fileBody = RequestBody.create(MediaType.parse(ContentType.MULTIPART.getValue()), file);
        return MultipartBody.Part.createFormData(RequestParam.FILE, file.getName(), fileBody);
    }

    /**
     * Creates a model response for the given chat conversation.
     *
     * @param completions params
     */
    public CompletionsResponse completions(Completions completions) {
        Call<CompletionsResponse> call = client.completions(completions);
        Response<CompletionsResponse> response;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new ChatGPTException(e.getMessage());
        }
        if (!response.isSuccessful()) {
            handleErrorResponse(response);
        }
        return response.body();
    }

    /**
     * Creates a model response for the given chat conversation.
     * Partial message deltas will be sent
     *
     * @param completions     params
     * @param consumerListener MessageListener instance
     */
    public <T extends MessageListener> void completions(Completions completions, T consumerListener) {
        completions.setStream(true);
        EventSource.Factory factory = EventSources.createFactory(this.okHttpClient);
        Request request;
        try {
            request = new Request.Builder()
                    .url(OpenAIClient.URL + "chat/completions")
                    .post(RequestBody.create(MediaType.parse(ContentType.JSON.getValue()), objectMapper.writeValueAsString(completions)))
                    .build();
        } catch (JsonProcessingException e) {
            throw new ChatGPTException(e.getMessage());
        }
        factory.newEventSource(request, consumerListener);
    }

    /**
     * Subscription
     *
     * @return Subscription
     */
    @Deprecated
    public Subscription subscription() {
        Call<Subscription> call = this.client.subscription();
        Response<Subscription> response;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!response.isSuccessful()) {
            handleErrorResponse(response);
        }
        return response.body();
    }

    /**
     * Billing usage
     *
     * @param starDate start date
     * @param endDate  end date
     * @return BillingUsage
     */
    @Deprecated
    public BillingUsage billingUsage(LocalDate starDate, LocalDate endDate) {
        Call<BillingUsage> call = this.client.billingUsage(starDate, endDate);
        Response<BillingUsage> response;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!response.isSuccessful()) {
            handleErrorResponse(response);
        }
        return response.body();
    }

    /**
     * Creates an image given a prompt.
     *
     * @param images images
     */
    public ImagesResponse generations(Images images) {
        Call<ImagesResponse> call = client.generations(images);
        Response<ImagesResponse> response;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!response.isSuccessful()) {
            handleErrorResponse(response);
        }
        return response.body();
    }

    public FileResponse files() {
        Call<FileResponse> call = client.files();
        Response<FileResponse> response;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!response.isSuccessful()) {
            handleErrorResponse(response);
        }
        return response.body();
    }

    public File uploadFile(java.io.File file) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addPart(createFile(file))
                .addFormDataPart(RequestParam.PURPOSE, "fine-tune")
                .build();
        Call<File> call = client.uploadFile(requestBody);
        Response<File> response;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!response.isSuccessful()) {
            handleErrorResponse(response);
        }
        return response.body();
    }

    public void deleteFile(String fileId) {
        Call<Object> call = client.deleteFile(fileId);
        Response<Object> response;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!response.isSuccessful()) {
            handleErrorResponse(response);
        }
    }

    public File getFile(String fileId) {
        Call<File> call = client.getFile(fileId);
        Response<File> response;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!response.isSuccessful()) {
            handleErrorResponse(response);
        }
        return response.body();
    }

    public String getFileContent(String fileId) {
        Call<String> call = client.getFileContent(fileId);
        Response<String> response;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!response.isSuccessful()) {
            handleErrorResponse(response);
        }
        return response.body();
    }


    private <T> void handleErrorResponse(Response<T> response) {
        try (ResponseBody errorBody = response.errorBody()) {
            if (errorBody == null) {
                throw new ChatGPTException("response errorBody is null");
            }
            String errorMsg = errorBody.string();
            throw new ChatGPTException(errorMsg);
        } catch (IOException e) {
            throw new ChatGPTException(e.getMessage());
        }
    }

    private OkHttpClient okHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (config.getProxy().getEnable()) {
            builder.proxy(new Proxy(config.getProxy().getType(), new InetSocketAddress(config.getProxy().getHostname(), config.getProxy().getPort())));
        }
        builder.readTimeout(config.getReadTimeout(), TimeUnit.MILLISECONDS);
        builder.connectTimeout(config.getConnectTimeout(), TimeUnit.MILLISECONDS);
        builder.addInterceptor(AuthorizationInterceptor.getInstance(config.getApiKey()));
        return builder.build();
    }

}
