package dev.Java10xCourse.MagicFridgeAI.controller;

import dev.Java10xCourse.MagicFridgeAI.model.FoodItem;
import dev.Java10xCourse.MagicFridgeAI.service.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.Java10xCourse.MagicFridgeAI.controller.DTO.FoodItemDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private final FoodItemService service;

    public FoodItemController(FoodItemService foodItemService){
        this.service = foodItemService;
    }

    @GetMapping()
    public ResponseEntity<List<FoodItemDTO>> getAll(){
        List<FoodItem> foods = service.findAll();
        if(foods.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<FoodItemDTO> foodItemDTOs = foods.stream()
                .map(foodItem -> new FoodItemDTO(foodItem.getId(), foodItem.getName(), foodItem.getCategory(), foodItem.getQuantity(), foodItem.getValidity()))
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(foodItemDTOs);
    }

    @GetMapping("/{id}")
    public Optional<ResponseEntity<FoodItemDTO>> getById(@PathVariable Long id){
        Optional<FoodItem> item= service.findById(id);
        return Optional.of(item.map(foodItem -> {
            FoodItemDTO foodItemDTO = new FoodItemDTO(foodItem.getId(), foodItem.getName(), foodItem.getCategory(), foodItem.getQuantity(), foodItem.getValidity());
            return ResponseEntity.status(HttpStatus.OK).body(foodItemDTO);
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

}
