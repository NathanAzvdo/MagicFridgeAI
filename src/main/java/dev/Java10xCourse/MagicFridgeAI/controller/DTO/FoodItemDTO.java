package dev.Java10xCourse.MagicFridgeAI.controller.DTO;

import dev.Java10xCourse.MagicFridgeAI.model.FoodItemCategoria;

import java.time.LocalDateTime;

public record FoodItemDTO(Long id, String name, FoodItemCategoria category, Integer quantity, LocalDateTime validity) {
}
