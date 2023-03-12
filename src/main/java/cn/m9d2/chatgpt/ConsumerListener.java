package cn.m9d2.chatgpt;

import cn.m9d2.chatgpt.framwork.excption.OpenAIException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

@Slf4j
public abstract class ConsumerListener extends EventSourceListener {

    public abstract void onMessage(String data);

    @Override
    public void onEvent(EventSource eventSource, String id, String type, String data) {
        super.onEvent(eventSource, id, type, data);
        onMessage(data);
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
        eventSource.cancel();
        throw new OpenAIException(t.getMessage());
    }
}
