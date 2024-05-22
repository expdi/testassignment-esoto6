package com.edwinsoto.service;

import com.edwinsoto.model.Adopter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@Testcontainers
@ActiveProfiles(profiles = "inmem")
//@SpringBootTest(classes = AdopterService.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AdopterServiceINMEMTest {

    @Autowired
    private AdopterService adopterService;

    @Test
    void getAllAdopters() {
        assertNotNull(adopterService.getAllAdopters());
        assertEquals(5, adopterService.getAllAdopters().size());
    }

    @Test
    void getAdopterByValidId() {
        assertEquals(1, adopterService.getAdopterById(1).get().getId());
    }

    @Test
    void getAdopterByInvalidId() {
        assertEquals(Optional.empty(), adopterService.getAdopterById(250));
    }

    @Test
    void createAdopter() {
        Adopter newAdopter = Adopter.builder()
                .name("New Name")
                .email("new.name@email.com")
                .phoneNumber("999-999-9999")
                .isOver18(true)
                .dateCreated(LocalDate.now())
                .build();
        int createdAdopteridx = adopterService.createAdopter(newAdopter);
        assertEquals(6, createdAdopteridx);
    }

    @Test
    void updateAdopter() {
        Adopter updatedAdopter = Adopter.builder()
                .id(1)
                .name("New Name")
                .email("new.name@email.com")
                .phoneNumber("999-999-9999")
                .isOver18(true)
                .build();

        boolean isUpdated = adopterService.updateAdopter(updatedAdopter);
        assertTrue(isUpdated);

        assertEquals("New Name", adopterService.getAdopterById(1).get().getName());
    }

    @Test
    void deleteAdopter() {
        int rowsAffected = adopterService.deleteAdopter(5);
        assertEquals(1, rowsAffected);

    }
}