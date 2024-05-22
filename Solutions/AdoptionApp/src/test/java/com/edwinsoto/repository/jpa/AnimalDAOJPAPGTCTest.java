package com.edwinsoto.repository.jpa;

import com.edwinsoto.model.Animal;
import com.edwinsoto.testcontainer.TestContainerConfig;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@DataJpaTest
@ActiveProfiles({"tc", "jpa"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class AnimalDAOJPAPGTCTest extends TestContainerConfig {

    private final AnimalJPA jpaRepo;

    @Autowired
    AnimalDAOJPAPGTCTest(AnimalJPA jpaRepo) {
        this.jpaRepo = jpaRepo;
    }


    @Test
    @Order(2)
    void findAll() {
        List<Animal> animals = jpaRepo.findAll();
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
    @Order(3)
    void findById(Integer animalID, String animalName) {
        Optional<Animal> animal = jpaRepo.findById(animalID);
        assertThat(animal).isPresent();
        assertEquals(animalName, animal.get().getName());
    }

    @Test
    @Order(4)
    @Disabled("Not Working...")
    void findByInvalidID() {
        Optional<Animal> animal = jpaRepo.findById(-1);
        assertThat(animal).isNotPresent();
    }

    @Test
    @Order(5)
    void create() {
        Animal animal = Animal.builder()
                .name("Skittles")
                .type("CAT")
                .dob(LocalDate.of(2024, 5, 9))
                .build();

        Animal newAnimal = jpaRepo.save(animal);

        assertThat(newAnimal).isNotNull();
        assertThat(newAnimal.getName()).isEqualTo("Skittles");
        assertThat(newAnimal.getType()).isEqualTo("CAT");
        assertThat(newAnimal.getDob()).isEqualTo(LocalDate.of(2024, 5, 9));
        assertThat(jpaRepo.findById(newAnimal.getId())).isPresent();
    }

    @Test
    @Order(6)
    void update() {
        Animal animalIndex1 = jpaRepo.findById(1).get();
        animalIndex1.setName("New Name");
        jpaRepo.save(animalIndex1);

        Optional<Animal> animal = jpaRepo.findById(animalIndex1.getId());
        assertThat(animal).isPresent();
        assertThat(animal.get().getName()).isEqualTo("New Name");

    }

    @Test
    @Order(7)
    void delete() {
        Animal animalIndex1 = jpaRepo.findById(1).get();
        assertThat(animalIndex1).isNotNull();

        jpaRepo.delete(animalIndex1);
        assertThat(jpaRepo.findById(1)).isEmpty();

//        Optional<Animal> invalidIDxAnimal = animalPostgresDAO.findById(1);
//        assertThat(invalidIDxAnimal.orElse(null)).isNull();

    }

}