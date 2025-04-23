package dev.Java10xCourse.MagicFridgeAI.controller;


import dev.Java10xCourse.MagicFridgeAI.model.FoodItem;
import dev.Java10xCourse.MagicFridgeAI.service.ChatGptService;
import dev.Java10xCourse.MagicFridgeAI.service.FoodItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private ChatGptService chatService;
    private FoodItemService foodItemService;

    public RecipeController(ChatGptService chatService, FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
        this.chatService = chatService;
    }

    @GetMapping("/generate-recipe")
    public Mono<ResponseEntity<String>> generateRecipe() {
        List<FoodItem> foodItems = foodItemService.findAll();
        return chatService.generateRecipe(foodItems)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

}
