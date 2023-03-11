package cn.m9d2.chatgpt.model.chat;

import java.io.Serializable;
import java.util.List;

public class Completions implements Serializable {

    private static final long serialVersionUID = 1L;

    private String model = Model.GPT_3_5_TURBO.getValue();

    private List<Message> messages;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public enum Model {
        GPT_3_5_TURBO("gpt-3.5-turbo"),
        GPT_3_5_TURBO_0301("gpt-3.5-turbo")
        ;

        private final String value;

        Model(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

}
