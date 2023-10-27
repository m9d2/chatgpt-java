package io.github.m9d2.chatgpt;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

@Slf4j
public abstract class MessageListener extends EventSourceListener {

    public abstract void onMessaged(String data);

    public abstract void onDone();

    @Override
    public void onEvent(EventSource eventSource, String id, String type, String data) {
        super.onEvent(eventSource, id, type, data);
        if (data.equals("[DONE]")) {
            onDone();
        } else {
            onMessaged(data);
        }
    }

    @Override
    public void onOpen(EventSource eventSource, Response response) {
        super.onOpen(eventSource, response);
    }

    @Override
    public void onClosed(EventSource eventSource) {
        super.onClosed(eventSource);
    }

    @Override
    public void onFailure(EventSource eventSource, Throwable t, Response response) {
        super.onFailure(eventSource, t, response);
    }
}
