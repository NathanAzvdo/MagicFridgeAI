package dev.Java10xCourse.MagicFridgeAI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "food_items")
@NoArgsConstructor
@AllArgsConstructor
public class FoodItem {

    private Long id;
    private String name;
    private FoodItemCategoria category;


}
