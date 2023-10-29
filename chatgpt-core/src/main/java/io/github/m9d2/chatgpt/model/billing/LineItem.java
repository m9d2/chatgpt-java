package io.github.m9d2.chatgpt.model.billing;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 描述：金额消耗列表
 */
@Data
public class LineItem {

    /**
     * 模型名称
     */
    private String name;

    /**
     * 消耗金额
     */
    private BigDecimal cost;
}
