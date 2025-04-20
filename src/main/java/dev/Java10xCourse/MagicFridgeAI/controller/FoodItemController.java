package dev.Java10xCourse.MagicFridgeAI.controller;

import dev.Java10xCourse.MagicFridgeAI.model.FoodItem;
import dev.Java10xCourse.MagicFridgeAI.service.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping()
    public ResponseEntity<?> saveFoodItem(@RequestBody FoodItemDTO foodItemDTO){
        try {
            FoodItem foodItem = new FoodItem(foodItemDTO.id(), foodItemDTO.name(), foodItemDTO.category(), foodItemDTO.quantity(), foodItemDTO.validity());
            FoodItem savedFoodItem = service.save(foodItem);
            FoodItemDTO savedFoodItemDTO = new FoodItemDTO(savedFoodItem.getId(), savedFoodItem.getName(), savedFoodItem.getCategory(), savedFoodItem.getQuantity(), savedFoodItem.getValidity());
            return ResponseEntity.status(HttpStatus.CREATED).body(savedFoodItemDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar!");
        }
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteFoodItem(@RequestParam Long id){
        try {
            Optional<FoodItem> item = service.findById(id);
            if (item.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontrado!");
            }
            else{
                service.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body("Item deletado com sucesso!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao deletar!");
        }
    }

}
