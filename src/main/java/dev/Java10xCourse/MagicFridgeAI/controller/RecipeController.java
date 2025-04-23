package dev.Java10xCourse.MagicFridgeAI.controller;

import dev.Java10xCourse.MagicFridgeAI.service.ChatGptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private ChatGptService chatService;

    public RecipeController(ChatGptService chatService){
        this.chatService = chatService;
    }

    @GetMapping("/generate-recipe")
    public ResponseEntity<Mono<String>> generateRecipe() {
        return ResponseEntity.ok(chatService.generateRecipe());
    }
}
