package dev.Java10xCourse.MagicFridgeAI.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${openai.api.url}")
    private String chatGptUrl;
    @Value("${openai.api.key}")
    private String openAiKey;

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .baseUrl(chatGptUrl)
                .defaultHeader("Authorization", "Bearer " + openAiKey)
                .build();
    }

}
