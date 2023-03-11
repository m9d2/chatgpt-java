package cn.m9d2.chatgpt.framwork.excption;

public class OpenAIException extends RuntimeException {

    public OpenAIException() {
    }

    public OpenAIException(String message) {
        super(message);
    }
}
