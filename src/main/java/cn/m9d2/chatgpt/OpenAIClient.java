package cn.m9d2.chatgpt;

import cn.m9d2.chatgpt.model.audio.AudioResponse;
import cn.m9d2.chatgpt.model.chat.Completions;
import cn.m9d2.chatgpt.model.chat.CompletionsResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OpenAIClient {

    String URL = "https://api.openai.com/v1/";

    @POST("chat/completions")
    Call<CompletionsResponse> completions(@Body Completions body);

    @POST("audio/transcriptions")
    Call<AudioResponse> transcriptions(@Body RequestBody requestBody);

    @POST("audio/transcriptions")
    Call<AudioResponse> translations(@Body RequestBody requestBody);


}
