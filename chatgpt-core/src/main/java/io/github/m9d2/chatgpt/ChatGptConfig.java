package io.github.m9d2.chatgpt;

import lombok.Data;

@Data
public class ChatGptConfig {

    /**
     * <a href="https://platform.openai.com">获取key</>
     */
    private String apiKey;

    /**
     * 连接超时时间 默认：10s 单位：毫秒
     */
    private Long connectTimeout = 10000L;

    /**
     * 连接超时时间 默认：10s 单位：毫秒
     */
    private Long readTimeout = 10000L;

    private Proxy proxy;

    @Data
    public static class Proxy {

        private Boolean enable = false;

        /**
         * 代理类型
         */
        private java.net.Proxy.Type type = java.net.Proxy.Type.HTTP;

        /**
         * 主机名
         */
        private String hostname = "127.0.0.1";

        /**
         * 端口
         */
        private Integer port = 1087;

    }

}
