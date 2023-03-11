package cn.m9d2.chatgpt;

import cn.m9d2.chatgpt.model.chat.Completions;
import cn.m9d2.chatgpt.model.chat.CompletionsResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OpenAIClient {

    String URL = "https://api.openai.com/v1/";

    @POST("chat/completions")
    Call<CompletionsResponse> completions(@Body Completions completions);

}
