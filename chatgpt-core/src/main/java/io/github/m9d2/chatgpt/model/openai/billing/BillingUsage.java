package io.github.m9d2.chatgpt.model.openai.billing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
public class BillingUsage {

    @JsonProperty("object")
    private String object;

    @JsonProperty("daily_costs")
    private List<DailyCost> dailyCosts;

    @JsonProperty("total_usage")
    private BigDecimal totalUsage;

}
