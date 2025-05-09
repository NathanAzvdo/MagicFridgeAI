package dev.Java10xCourse.MagicFridgeAI.controller;

import dev.Java10xCourse.MagicFridgeAI.controller.Mapper.FoodItemMapper;
import dev.Java10xCourse.MagicFridgeAI.exceptions.FoodItemNotFoundException;
import dev.Java10xCourse.MagicFridgeAI.model.FoodItem;
import dev.Java10xCourse.MagicFridgeAI.service.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import dev.Java10xCourse.MagicFridgeAI.controller.DTO.FoodItemDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private final FoodItemService service;

    public FoodItemController(FoodItemService foodItemService){
        this.service = foodItemService;
    }

    @GetMapping()
    public ResponseEntity<?> getAll(){
        List<FoodItem> foods = service.findAll();
        if(foods.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há registros!");
        }

        List<FoodItemDTO> foodItemDTOs = foods.stream()
                .map(FoodItemMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(foodItemDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodItemDTO> getById(@PathVariable Long id){
        return service.findById(id)
                .map(foodItem -> {
                    FoodItemDTO foodItemDTO = FoodItemMapper.toDTO(foodItem);
                    return ResponseEntity.ok(foodItemDTO);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<?> saveFoodItem(@RequestBody FoodItemDTO foodItemDTO){
        try {
            FoodItem foodItem = FoodItemMapper.toEntity(foodItemDTO);
            FoodItem savedFoodItem = service.save(foodItem);
            FoodItemDTO savedFoodItemDTO = FoodItemMapper.toDTO(savedFoodItem);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedFoodItemDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFoodItem(@PathVariable Long id){
        try {
            Optional<FoodItem> item = service.findById(id);
            if (item.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            else{
                service.deleteById(id);
                return ResponseEntity.ok().body("Item deletado com sucesso!");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao deletar!");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateFoodItem(@PathVariable Long id, @RequestBody FoodItemDTO foodItemDTO){
        try {
            FoodItem foodItem = FoodItemMapper.toEntity(foodItemDTO);
            Optional<FoodItem> updatedFoodItemOpt = service.updateById(foodItem, id);

            FoodItemDTO updatedFoodItemDTO = FoodItemMapper.toDTO(updatedFoodItemOpt.get());
            return ResponseEntity.ok(updatedFoodItemDTO);

        } catch (FoodItemNotFoundException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar: " + e.getMessage());
        }
    }



}
