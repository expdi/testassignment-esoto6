package com.edwinsoto.repository.inmem;

import com.edwinsoto.model.Adopter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = AdopterInMemDAO.class)
@ActiveProfiles(profiles = "inmem")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AdopterDAOINMEMTest {

    @Autowired
    private AdopterInMemDAO adopterInMemDAO;

    @Test
    void findAll() {
        List<Adopter> adopters = adopterInMemDAO.findAll();
        assertNotNull(adopters);
        assertEquals(5, adopters.size());
    }

    @Test
    void findByValidID() {
        Optional<Adopter> adopter = adopterInMemDAO.findById(1);
        assertEquals(1, adopter.get().getId());
    }

    @Test
    void findByInvalidID() {
        Optional<Adopter> adopter = adopterInMemDAO.findById(250);
        assertInstanceOf(Optional.class, adopter);
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

        Adopter updatedAdopter = adopterInMemDAO.create(newAdopter);
        assertNotNull(updatedAdopter);

        List<Adopter> adopters = adopterInMemDAO.findAll();
        assertEquals(6, adopters.size());
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

        Adopter upAdopter = adopterInMemDAO.update(updatedAdopter);

        Optional<Adopter> adopter = adopterInMemDAO.findById(1);
        assertEquals(updatedAdopter.getId(), adopter.get().getId());
        assertEquals(updatedAdopter.getName(), adopter.get().getName());
        assertEquals(updatedAdopter.getPhoneNumber(), adopter.get().getPhoneNumber());
        assertEquals(updatedAdopter.isOver18(), adopter.get().isOver18());
    }

    @Test
    void deleteAdopter() {
        int affectedRecords = adopterInMemDAO.delete(1);
        assertEquals(1, affectedRecords);
    }
}