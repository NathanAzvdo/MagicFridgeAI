package dev.Java10xCourse.MagicFridgeAI.controller.Mapper;
import dev.Java10xCourse.MagicFridgeAI.controller.DTO.FoodItemDTO;
import dev.Java10xCourse.MagicFridgeAI.model.FoodItem;


public class FoodItemMapper {

    public static FoodItemDTO toDTO(FoodItem foodItem){
        return new FoodItemDTO(
                foodItem.getId(),
                foodItem.getName(),
                foodItem.getCategory(),
                foodItem.getQuantity(),
                foodItem.getValidity()
        );
    }
    public static FoodItem toEntity(FoodItemDTO foodItemDTO){
        return new FoodItem(
                foodItemDTO.id(),
                foodItemDTO.name(),
                foodItemDTO.category(),
                foodItemDTO.quantity(),
                foodItemDTO.validity()
        );
    }
}
