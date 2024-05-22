package com.edwinsoto.controller;

import com.edwinsoto.model.Adopter;
import com.edwinsoto.model.Animal;
import com.edwinsoto.service.AnimalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnimalControllerTest {

    @InjectMocks
    private AnimalController animalController;

    @Mock
    private AnimalService animalService;

    List<Animal> animals = List.of(
            Animal.builder().id(1).name("Gracie").type("DOG").dob(LocalDate.of(2022,1,23)).breed("Terrier Mix").build(),
            Animal.builder().id(2).name("Fido").type("CAT").dob(LocalDate.of(2023,7,29)).breed("Siamese").build()
    );


    @Test
    void getAllAnimals() {
        when(animalService.getAllAnimals()).thenReturn(animals);
        ResponseEntity<?> response = animalController.getAllAnimals();
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(2, ((List<?>) Objects.requireNonNull(response.getBody())).size());
    }
    @Test
    void getAllAnimalsEmpty(){
        when(animalService.getAllAnimals()).thenReturn(List.of());
        ResponseEntity<?> response = animalController.getAllAnimals();
        assertTrue(response.getStatusCode().is4xxClientError());
    }
    @Test
    void getAdopterById() {
        when(animalService.getAnimalById(1)).thenReturn(Optional.ofNullable(animals.get(0)));
        ResponseEntity<?> response = animalController.getAdopterById(1);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    void getAdoptersByInvalidId(){
        when(animalService.getAnimalById(1)).thenReturn(Optional.empty());
        ResponseEntity<?> response = animalController.getAdopterById(1);
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    void addAnimal() {
        Animal newAnimal = Animal.builder().name("New Dog").build();

        when(animalService.createAnimal(newAnimal)).thenReturn(3);
        ResponseEntity<?> response = animalController.addAnimal(newAnimal);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    @Disabled("To be Continued...")
    void updateAnimal() {
    }

    @Test
    void deleteValidAnimal() {
        when(animalService.getAnimalById(1)).thenReturn(Optional.ofNullable(animals.get(0)));
        when(animalService.deleteAnimal(1)).thenReturn(1);
        ResponseEntity<?> response = animalController.deleteAdopter(1);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    void deleteInvalidAnimal() {
        when(animalService.getAnimalById(100)).thenReturn(Optional.empty());
        ResponseEntity<?> response = animalController.deleteAdopter(100);
        assertTrue(response.getStatusCode().is4xxClientError());
    }
}