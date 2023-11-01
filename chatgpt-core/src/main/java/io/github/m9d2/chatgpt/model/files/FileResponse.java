package io.github.m9d2.chatgpt.model.files;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileResponse {

    @JsonProperty("data")
    private List<File> data;

    @JsonProperty("object")
    private String object;

    @JsonProperty("has_more")
    private Boolean hasMore;
}
