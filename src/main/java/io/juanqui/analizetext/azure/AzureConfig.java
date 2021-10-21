package io.juanqui.analizetext.azure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@PropertySource("classpath:azure.properties")
@ConfigurationProperties
public class AzureConfig {
    private String baseUrl;
    private String apiHeaderName;
    private String contentType;
    private String applicationJson;

    private String sentimentEndpoint;



    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getApiHeaderName() {
        return apiHeaderName;
    }

    public void setApiHeaderName(String apiHeaderName) {
        this.apiHeaderName = apiHeaderName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getApplicationJson() {
        return applicationJson;
    }

    public void setApplicationJson(String applicationJson) {
        this.applicationJson = applicationJson;
    }

    public String getSentimentEndpoint() {
        return sentimentEndpoint;
    }

    public void setSentimentEndpoint(String sentimentEndpoint) {
        this.sentimentEndpoint = sentimentEndpoint;
    }
}
