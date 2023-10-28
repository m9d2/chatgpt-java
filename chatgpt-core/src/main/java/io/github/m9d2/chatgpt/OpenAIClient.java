package io.github.m9d2.chatgpt;

import io.github.m9d2.chatgpt.model.openai.audio.AudioResponse;
import io.github.m9d2.chatgpt.model.openai.billing.BillingUsage;
import io.github.m9d2.chatgpt.model.openai.billing.Subscription;
import io.github.m9d2.chatgpt.model.openai.chat.Completions;
import io.github.m9d2.chatgpt.model.openai.chat.CompletionsResponse;
import io.github.m9d2.chatgpt.model.openai.images.Images;
import io.github.m9d2.chatgpt.model.openai.images.ImagesResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.time.LocalDate;

public interface OpenAIClient {

    String URL = "https://api.openai.com/v1/";

    @POST("audio/transcriptions")
    Call<AudioResponse> transcriptions(@Body RequestBody requestBody);

    @POST("audio/translations")
    Call<AudioResponse> translations(@Body RequestBody requestBody);

    @POST("chat/completions")
    Call<CompletionsResponse> completions(@Body Completions body);

    @POST("images/generations")
    Call<ImagesResponse> generations(@Body Images body);

    @GET("dashboard/billing/subscription")
    Call<Subscription> subscription();

    @GET("dashboard/billing/usage")
    Call<BillingUsage> billingUsage(@Query("start_date") LocalDate starDate, @Query("end_date") LocalDate endDate);
}
