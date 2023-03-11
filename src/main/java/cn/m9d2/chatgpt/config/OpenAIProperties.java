package cn.m9d2.chatgpt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({OpenAIProperties.class})
@ConfigurationProperties(prefix = "openai")
public class OpenAIProperties {

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

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Long getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Long connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Long getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(Long readTimeout) {
        this.readTimeout = readTimeout;
    }

    public Proxy getProxy() {
        return proxy;
    }

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

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

        public Boolean getEnable() {
            return enable;
        }

        public void setEnable(Boolean enable) {
            this.enable = enable;
        }

        public java.net.Proxy.Type getType() {
            return type;
        }

        public void setType(java.net.Proxy.Type type) {
            this.type = type;
        }

        public String getHostname() {
            return hostname;
        }

        public void setHostname(String hostname) {
            this.hostname = hostname;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }
    }

}
