package io.github.m9d2.chatgpt.service;

import io.github.m9d2.chatgpt.model.billing.BillingUsage;
import io.github.m9d2.chatgpt.model.billing.Subscription;

import java.time.LocalDate;

public interface BillingService {

    /**
     * Subscription
     *
     * @return Subscription
     */
    @Deprecated
    Subscription subscription();

    /**
     * Billing usage
     *
     * @param starDate start date
     * @param endDate  end date
     * @return BillingUsage
     */
    @Deprecated
    BillingUsage billingUsage(LocalDate starDate, LocalDate endDate);
}
