package com.edwinsoto.controller;

import com.edwinsoto.model.Adopter;
import com.edwinsoto.model.Animal;
import com.edwinsoto.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/animal")
public class AnimalController {

    private final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public ResponseEntity<?> getAllAnimals() {
        List<Animal> animals = animalService.getAllAnimals();
        if (animals.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(animals);
    }

    @GetMapping("/id={id}")
    public ResponseEntity<Object> getAdopterById(@PathVariable("id") int id) {
        Optional<Animal> animal = animalService.getAnimalById(id);
        if (animal.isPresent()) {
            return ResponseEntity.ok().body(animal);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<Integer> addAnimal(@RequestBody @Valid Animal animal) {
        int id = animalService.createAnimal(animal);

        if (id > 0) {
            return ResponseEntity.ok().body(id);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/id={id}")
    public ResponseEntity<Boolean> updateAnimal(@RequestBody @Valid Animal animal, @PathVariable("id") int id) {
        Optional<Animal> foundAnimal = animalService.getAnimalById(id);
        if (animalService.getAnimalById(id).isPresent()){
            Animal upAnimal = foundAnimal.get();

            if (animal.getName() != null){
                upAnimal.setName(animal.getName());
            }

            if(animal.getType() != null){
                upAnimal.setType(animal.getType());
            }

            if(animal.getDob() != null){
                upAnimal.setDob(animal.getDob());
            }

            if(animal.getBreed() != null){
                upAnimal.setBreed(animal.getBreed());
            }

            if(animal.getAdoptedDate() != null){
                upAnimal.setAdoptedDate(animal.getAdoptedDate());
            }
            boolean isUpdated = animalService.updateAnimal(upAnimal);
            return ResponseEntity.ok().body(isUpdated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/id={id}")
    public ResponseEntity<Integer> deleteAdopter(@PathVariable("id") int id) {
        Optional<Animal> foundAnimal = animalService.getAnimalById(id);
        if (foundAnimal.isPresent()){
            int rowsAffected = animalService.deleteAnimal(id);
            return ResponseEntity.ok().body(rowsAffected);
        }
        return ResponseEntity.notFound().build();
    }


}
