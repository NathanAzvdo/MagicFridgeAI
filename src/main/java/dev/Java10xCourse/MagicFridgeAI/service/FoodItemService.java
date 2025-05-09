package dev.Java10xCourse.MagicFridgeAI.service;

import dev.Java10xCourse.MagicFridgeAI.exceptions.FoodItemNotFoundException;
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

    public Optional<FoodItem> updateById(FoodItem item, Long id) {
        FoodItem existingItem = foodItemRepository.findById(id)
                .orElseThrow(() -> new FoodItemNotFoundException(id));

        if (item.getName() != null) {
            existingItem.setName(item.getName());
        }

        if (item.getCategory() != null) {
            existingItem.setCategory(item.getCategory());
        }

        if (item.getQuantity() != null) {
            existingItem.setQuantity(item.getQuantity());
        }

        if (item.getValidity() != null) {
            existingItem.setValidity(item.getValidity());
        }

        return Optional.of(foodItemRepository.save(existingItem));
    }

}
