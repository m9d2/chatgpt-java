package io.github.m9d2.chatgpt.model.files;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class File {

    @JsonProperty("id")
    private String id;
    @JsonProperty("object")
    private String object;
    @JsonProperty("bytes")
    private Integer bytes;
    @JsonProperty("created_at")
    private Integer createdAt;
    @JsonProperty("filename")
    private String filename;
    @JsonProperty("purpose")
    private String purpose;
    @JsonProperty("status")
    private String status;
    @JsonProperty("status_details")
    private String statusDetails;
}
