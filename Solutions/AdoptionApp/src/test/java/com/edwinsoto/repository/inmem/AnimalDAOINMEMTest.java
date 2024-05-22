package com.edwinsoto.repository.inmem;

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

@SpringBootTest(classes = AnimalInMemDAO.class)
@ActiveProfiles(profiles = "inmem")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AnimalDAOINMEMTest {

    @Autowired
    private AnimalInMemDAO animalInMemDAO;

    @Test
    void findAll() {
        List<Animal> animals = animalInMemDAO.findAll();
        assertNotNull(animals);
        assertEquals(5, animals.size());
    }

    @Test
    void findByValidId() {
        List<Animal> animals = animalInMemDAO.findAll();
        System.out.println(animals.size());
        Optional<Animal> animal = animalInMemDAO.findById(5);
        assertEquals(5, animal.get().getId());
    }

    @Test
    void findByInvalidId() {

        Optional<Animal> animal = animalInMemDAO.findById(250);
        assertInstanceOf(Optional.class, animal);
    }

    @Test
    void createAnimal() {
        Animal newAnimal = Animal.builder()
                .name("Skittles")
                .type("CAT")
                .dob(LocalDate.of(2024, 5, 9))
                .build();

        Animal savedAnimal = animalInMemDAO.create(newAnimal);
        assertNotNull(savedAnimal);

        List<Animal> animals = animalInMemDAO.findAll();
        assertEquals(6, animals.size());
    }

    @Test
    void updateAnimal() {
        Animal updatedAnimal = Animal.builder()
                .id(1)
                .name("New Animal")
                .breed("New Breed")
                .build();

        Animal upAnimal = animalInMemDAO.update(updatedAnimal);

        Optional<Animal> animal = animalInMemDAO.findById(1);
        assertEquals(updatedAnimal.getId(), animal.get().getId());
        assertEquals(updatedAnimal.getName(), animal.get().getName());
        assertEquals(updatedAnimal.getBreed(), animal.get().getBreed());
    }

    @Test
    void deleteAnimal() {
        int affectedRecords = animalInMemDAO.delete(1);
        assertEquals(1, affectedRecords);
    }
}