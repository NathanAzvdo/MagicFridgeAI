package dev.Java10xCourse.MagicFridgeAI.controller;

import dev.Java10xCourse.MagicFridgeAI.service.FoodItemService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService){
        this.foodItemService = foodItemService;
    }
}
