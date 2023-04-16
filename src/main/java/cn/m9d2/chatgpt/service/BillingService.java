package cn.m9d2.chatgpt.service;

import cn.m9d2.chatgpt.model.billing.BillingUsage;
import cn.m9d2.chatgpt.model.billing.Subscription;

import java.time.LocalDate;

public interface BillingService {

    /**
     * 账户信息
     * @return Subscription
     */
    Subscription subscription();

    /**
     *  消耗金额信息查询
     * @param starDate 开始时间
     * @param endDate 结束时间
     * @return BillingUsage
     */
    BillingUsage billingUsage(LocalDate starDate, LocalDate endDate);
}
