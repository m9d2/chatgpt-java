package cn.m9d2.chatgpt.model.chat;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CompletionsResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String object;

    private Long created;

    private String model;

    private List<Choice> choices;

    private Usage usage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public static class Choice {

        private Double index;

        private Message message;

        @SerializedName("finish_reason")
        private String finishReason;

        public Double getIndex() {
            return index;
        }

        public void setIndex(Double index) {
            this.index = index;
        }

        public Message getMessage() {
            return message;
        }

        public void setMessage(Message message) {
            this.message = message;
        }

        public String getFinishReason() {
            return finishReason;
        }

        public void setFinishReason(String finishReason) {
            this.finishReason = finishReason;
        }
    }

    public static class Usage {

        @SerializedName("prompt_tokens")
        private Double promptTokens;

        @SerializedName("completion_tokens")
        private Double completionTokens;

        @SerializedName("total_tokens")
        private Double totalTokens;

        public Double getPromptTokens() {
            return promptTokens;
        }

        public void setPromptTokens(Double promptTokens) {
            this.promptTokens = promptTokens;
        }

        public Double getCompletionTokens() {
            return completionTokens;
        }

        public void setCompletionTokens(Double completionTokens) {
            this.completionTokens = completionTokens;
        }

        public Double getTotalTokens() {
            return totalTokens;
        }

        public void setTotalTokens(Double totalTokens) {
            this.totalTokens = totalTokens;
        }
    }
}
