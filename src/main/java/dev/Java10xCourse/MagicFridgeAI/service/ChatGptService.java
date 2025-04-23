package dev.Java10xCourse.MagicFridgeAI.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class ChatGptService {

    private final WebClient webClient;

    public ChatGptService(WebClient webClient){
        this.webClient = webClient;
    }



    public Mono<String> generateRecipe() {
        String prompt = "Me sugira receitas simples com ingredientes comuns";

        Map<String, Object> requestBody = Map.of(
                "model", "gpt-4.1",
                "messages", List.of(
                        Map.of("role", "system", "content", "Você é um assistente que cria receitas. Me ajude a criar receitas com os ingredientes que eu tenho disponível."),
                        Map.of("role", "user", "content", prompt)
                )
        );

        return webClient.post()
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
                    if (choices != null && !choices.isEmpty()) {
                        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                        return (String) message.get("content");
                    }
                    return "Nenhuma resposta recebida.";
                });
    }

}
