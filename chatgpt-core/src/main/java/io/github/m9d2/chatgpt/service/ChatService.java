package io.github.m9d2.chatgpt.service;

import io.github.m9d2.chatgpt.MessageListener;
import io.github.m9d2.chatgpt.OpenAIService;
import io.github.m9d2.chatgpt.model.chat.Completions;
import io.github.m9d2.chatgpt.model.chat.CompletionsResponse;

public interface ChatService extends OpenAIService {

    /**
     * 问答
     *
     * @param completions 参数
     * @return 回答内容
     */
    CompletionsResponse completions(Completions completions);

    /**
     * 问答，流式输出
     *
     * @param completions     参数
     * @param messageListener MessageListener实例
     *                        需要实现 onMessaged
     *                        onDone
     */
    <T extends MessageListener> void completions(Completions completions, T messageListener);

}
