package com.edwinsoto.service;

import com.edwinsoto.model.Animal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles(profiles = "inmem")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AnimalServiceINMEMTest {

    @Autowired
    private AnimalService animalService;


    @Test
    void getAllAnimals() {
        List<Animal> animals = animalService.getAllAnimals();
        assertNotNull(animals);
        assertEquals(5, animals.size());
    }

    @Test
    void getAnimalByValidId() {
        assertEquals(1, animalService.getAnimalById(1).get().getId());
    }

    @Test
    void getAnimalByInvalidId() {
        assertEquals(Optional.empty(), animalService.getAnimalById(250));
    }

    @Test
    void createAnimal() {
        Animal newAnimal = Animal.builder()
                .name("Skittles")
                .type("CAT")
                .dob(LocalDate.of(2024, 5, 9))
                .build();
        int createdAnimalIdx = animalService.createAnimal(newAnimal);
        assertEquals(6, createdAnimalIdx);
    }

    @Test
    void updateAnimal() {
        Animal updatedAnimal = Animal.builder()
                .id(1)
                .name("New Animal")
                .breed("New Breed")
                .build();

        boolean isUpdated = animalService.updateAnimal(updatedAnimal);
        assertTrue(isUpdated);

        assertEquals("New Animal", animalService.getAnimalById(1).get().getName());
    }

    @Test
    void deleteAnimal() {
        int rowsAffected = animalService.deleteAnimal(5);
        assertEquals(1, rowsAffected);
    }
}