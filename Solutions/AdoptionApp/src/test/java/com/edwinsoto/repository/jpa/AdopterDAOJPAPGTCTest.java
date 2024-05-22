package com.edwinsoto.repository.jpa;

import com.edwinsoto.model.Adopter;
import com.edwinsoto.testcontainer.TestContainerConfig;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@Testcontainers
@DataJpaTest
@ActiveProfiles({"tc", "jpa"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class AdopterDAOJPAPGTCTest extends TestContainerConfig {

    private final AdopterJPA jpaRepo;

    @Autowired
    AdopterDAOJPAPGTCTest(AdopterJPA jpaRepo) {
        this.jpaRepo = jpaRepo;
    }


    @Test
    @Order(2)
    void findAll() {
        List<Adopter> adopters = jpaRepo.findAll();
        assertThat(adopters).hasSize(5);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1, Edwin Soto",
            "2, Spouse Soto",
            "3, Kido One Soto",
            "4, Kido Two Soto",
            "5, Kido Three Soto"
    })
    @Order(3)
    void findById(Integer adopterID, String adopterName) {
        Optional<Adopter> adopter = jpaRepo.findById(adopterID);
        assertThat(adopter).isPresent();
        assertThat(adopter.get().getName()).isEqualTo(adopterName);
    }

    @Test
    @Order(4)
    void findByInvalidID() {
        Optional<Adopter> adopter = jpaRepo.findById(-1);
        assertThat(adopter).isNotPresent();
    }

    @Test
    @Order(5)
    void create() {
        Adopter adopter = Adopter.builder()
                .name("New Adopter")
                .phoneNumber("000-888-8889")
                .build();

        Adopter newAdopter = jpaRepo.save(adopter);

        assertThat(newAdopter).isNotNull();
        assertThat(newAdopter.getName()).isEqualTo(adopter.getName());
        assertThat(newAdopter.getPhoneNumber()).isEqualTo(adopter.getPhoneNumber());
        assertThat(jpaRepo.findById(newAdopter.getId())).isPresent();
    }

    @Test
    @Order(6)
    void update() {
        Adopter adopterIdx1 = jpaRepo.findById(1).get();
        adopterIdx1.setName("Not Edwin");
        jpaRepo.save(adopterIdx1);

        Optional<Adopter> adopter = jpaRepo.findById(1);
        assertThat(adopter).isPresent();
        assertThat(adopter.get().getName()).isEqualTo(adopterIdx1.getName());

    }

    @Test
    @Order(7)
    void delete() {
        Adopter adopteridx1 = jpaRepo.findById(1).get();
        assertThat(adopteridx1).isNotNull();

        jpaRepo.delete(adopteridx1);

        assertThat(jpaRepo.findById(1)).isEmpty();
    }
}