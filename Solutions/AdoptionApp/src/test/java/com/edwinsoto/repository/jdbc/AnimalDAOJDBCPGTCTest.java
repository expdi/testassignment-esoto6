package com.edwinsoto.repository.jdbc;

import com.edwinsoto.model.Animal;
import com.edwinsoto.testcontainer.TestContainerConfig;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@Testcontainers
@SpringBootTest(classes = AnimalJDBCDAO.class)
@ActiveProfiles("tc")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class AnimalDAOJDBCPGTCTest extends TestContainerConfig {

    @Autowired
    private AnimalJDBCDAO animalPostgresDAO;


    @Test
    @Order(1)
    void findAll() {
        List<Animal> animals = animalPostgresDAO.findAll();
        assertThat(animals).hasSize(5);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1, Fred",
            "2, Fido",
            "3, Gracie",
            "4, Spot",
            "5, Slimy"
    })
    @Order(2)
    void findById(Integer animalID, String animalName) {
        Optional<Animal> animal = animalPostgresDAO.findById(animalID);
        assertThat(animal).isPresent();
        assertEquals(animalName, animal.get().getName());
    }

    @Test
    @Order(3)
    @Disabled("Not Working...")
    void findByInvalidID() {
        Optional<Animal> animal = animalPostgresDAO.findById(-1);
        assertNull(animal);
    }

    @Test
    @Order(4)
    void create() {
        Animal animal = Animal.builder()
                .name("Skittles")
                .type("CAT")
                .dob(LocalDate.of(2024, 5, 9))
                .build();

        Animal newAnimal = animalPostgresDAO.create(animal);

        assertThat(newAnimal).isNotNull();
        assertThat(newAnimal.getName()).isEqualTo("Skittles");
        assertThat(newAnimal.getType()).isEqualTo("CAT");
        assertThat(newAnimal.getDob()).isEqualTo(LocalDate.of(2024, 5, 9));
        assertThat(animalPostgresDAO.findById(newAnimal.getId())).isPresent();
    }

    @Test
    @Order(5)
    void update() {
        Animal animalIndex1 = animalPostgresDAO.findById(1).get();
        animalIndex1.setName("New Name");
        animalPostgresDAO.update(animalIndex1);
        Optional<Animal> animal = animalPostgresDAO.findById(animalIndex1.getId());
        assertThat(animal).isPresent();
        assertThat(animal.get().getName()).isEqualTo("New Name");

    }

    @Test
    @Order(6)
    void delete() {
        Animal animalIndex1 = animalPostgresDAO.findById(1).get();
        assertThat(animalIndex1).isNotNull();

        int rowsAffected = animalPostgresDAO.delete(1);
        assertThat(rowsAffected).isEqualTo(1);

//        Optional<Animal> invalidIDxAnimal = animalPostgresDAO.findById(1);
//        assertThat(invalidIDxAnimal.orElse(null)).isNull();

    }
}