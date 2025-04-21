package dev.Java10xCourse.MagicFridgeAI.exceptions;

public class FoodItemNotFoundException extends RuntimeException {
    public FoodItemNotFoundException(Long id) {
        super("Food item with ID " + id + " not found.");
    }
}
