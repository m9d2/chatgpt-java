package cn.m9d2.chatgpt.service;

import cn.m9d2.chatgpt.ConsumerListener;
import cn.m9d2.chatgpt.OpenAIService;
import cn.m9d2.chatgpt.model.chat.Completions;
import cn.m9d2.chatgpt.model.chat.CompletionsResponse;

import java.util.function.Supplier;

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
     * @param completions 参数
     * @param supplier    ConsumerListener对象
     */
    <T extends ConsumerListener> void completions(Completions completions, Supplier<T> supplier);

}
