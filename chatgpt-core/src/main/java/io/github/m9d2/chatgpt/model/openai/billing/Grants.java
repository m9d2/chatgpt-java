package io.github.m9d2.chatgpt.model.openai.billing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 描述：
 */
@Data
public class Grants {

    private String object;

    @JsonProperty("data")
    private List<Datum> data;
}
