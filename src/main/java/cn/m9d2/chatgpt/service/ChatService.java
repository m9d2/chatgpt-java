package cn.m9d2.chatgpt.service;

import cn.m9d2.chatgpt.OpenAIService;
import cn.m9d2.chatgpt.model.chat.Completions;
import cn.m9d2.chatgpt.model.chat.CompletionsResponse;

public interface ChatService extends OpenAIService {

    CompletionsResponse completions(Completions completions);

}
