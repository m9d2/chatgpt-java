package io.github.m9d2.chatgpt.service;

import io.github.m9d2.chatgpt.MessageListener;
import io.github.m9d2.chatgpt.OpenAIService;
import io.github.m9d2.chatgpt.model.chat.Completions;
import io.github.m9d2.chatgpt.model.chat.CompletionsResponse;

public interface ChatService extends OpenAIService {

    /**
     * Creates a model response for the given chat conversation.
     *
     * @param completions params
     */
    CompletionsResponse completions(Completions completions);

    /**
     * Creates a model response for the given chat conversation.
     * Partial message deltas will be sent
     *
     * @param completions     params
     * @param messageListener MessageListener instance
     */
    <T extends MessageListener> void completions(Completions completions, T messageListener);

}
