package cn.m9d2.chatgpt.service;

import cn.m9d2.chatgpt.BaseTest;
import cn.m9d2.chatgpt.model.billing.BillingUsage;
import cn.m9d2.chatgpt.model.billing.Subscription;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

@Slf4j
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