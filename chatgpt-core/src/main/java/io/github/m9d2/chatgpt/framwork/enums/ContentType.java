package io.github.m9d2.chatgpt.framwork.enums;

public enum ContentType {

    JSON("application/json"),

    MULTIPART("multipart/form-data");
    private final String value;

    ContentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
