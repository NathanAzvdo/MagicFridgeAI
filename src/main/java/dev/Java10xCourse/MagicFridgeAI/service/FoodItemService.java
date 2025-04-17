package dev.Java10xCourse.MagicFridgeAI.service;

import dev.Java10xCourse.MagicFridgeAI.model.FoodItem;
import dev.Java10xCourse.MagicFridgeAI.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodItemService {

    private FoodItemRepository foodItemRepository;

    public FoodItemService(FoodItemRepository foodItemRepository){
        this.foodItemRepository = foodItemRepository;
    }

    public FoodItem save(FoodItem item){
        return foodItemRepository.save(item);
    }
    public List<FoodItem> findAll(){
        return foodItemRepository.findAll();
    }

    public Optional<FoodItem> findById(Long id){
        return foodItemRepository.findById(id);
    }
    public void deleteById(Long id){
        foodItemRepository.deleteById(id);
    }

    public FoodItem updateById(FoodItem item, Long id){
        FoodItem foodItem = foodItemRepository.findById(id).orElseThrow();
        foodItem.setName(item.getName());
        foodItem.setCategory(item.getCategory());
        foodItem.setQuantity(item.getQuantity());
        foodItem.setValidity(item.getValidity());
        return foodItemRepository.save(foodItem);
    }

}
