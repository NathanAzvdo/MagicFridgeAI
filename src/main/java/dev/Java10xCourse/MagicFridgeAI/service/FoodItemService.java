package dev.Java10xCourse.MagicFridgeAI.service;

import dev.Java10xCourse.MagicFridgeAI.repository.FoodItemRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class FoodItemService {

    private FoodItemRepository foodItemRepository;

    public FoodItemService(FoodItemRepository foodItemRepository){
        this.foodItemRepository = foodItemRepository;
    }



}
