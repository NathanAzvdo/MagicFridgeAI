package dev.Java10xCourse.MagicFridgeAI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "food_items")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FoodItem {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private FoodItemCategoria category;
    private Integer quantity;
    private LocalDateTime validity;

}
