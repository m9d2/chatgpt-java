package io.github.m9d2.chatgpt.service.impl;

import io.github.m9d2.chatgpt.AbstractService;
import io.github.m9d2.chatgpt.ChatGptConfig;
import io.github.m9d2.chatgpt.framwork.interceptor.AuthorizationInterceptor;
import io.github.m9d2.chatgpt.model.billing.BillingUsage;
import io.github.m9d2.chatgpt.model.billing.Subscription;
import io.github.m9d2.chatgpt.service.BillingService;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.time.LocalDate;

public class BillingServiceImpl extends AbstractService implements BillingService {

    public BillingServiceImpl(ChatGptConfig config) {
        super(config);
    }

    @Override
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

    @Override
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
}
