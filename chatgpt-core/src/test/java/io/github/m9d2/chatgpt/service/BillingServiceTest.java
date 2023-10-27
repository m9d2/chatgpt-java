package io.github.m9d2.chatgpt.service;

import io.github.m9d2.chatgpt.BaseTest;
import io.github.m9d2.chatgpt.model.billing.BillingUsage;
import io.github.m9d2.chatgpt.model.billing.Subscription;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;

@Ignore
public class BillingServiceTest extends BaseTest {

    @Test
    public void testSubscription() {
        Subscription subscription = billingService.subscription();
        Assert.assertNotNull(subscription.getAccountName());
    }

    @Test
    public void testBillingUsage() {
        LocalDate end = LocalDate.now();
        LocalDate start = end.withDayOfMonth(1);
        BillingUsage billingUsage = billingService.billingUsage(start, end);
        Assert.assertNotNull(billingUsage.getTotalUsage());
    }

}