package cn.m9d2.chatgpt.service.impl;

import cn.m9d2.chatgpt.AbstractService;
import cn.m9d2.chatgpt.config.OpenAIProperties;
import cn.m9d2.chatgpt.framwork.interceptor.AuthorizationInterceptor;
import cn.m9d2.chatgpt.model.billing.BillingUsage;
import cn.m9d2.chatgpt.model.billing.Subscription;
import cn.m9d2.chatgpt.service.BillingService;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.time.LocalDate;

public class BillingServiceImpl extends AbstractService implements BillingService {

    public BillingServiceImpl(AuthorizationInterceptor interceptor, OpenAIProperties properties) {
        super(interceptor, properties);
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
