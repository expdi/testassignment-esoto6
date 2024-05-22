package com.edwinsoto.controller;

import com.edwinsoto.model.Adopter;
import com.edwinsoto.service.AdopterService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AdopterControllerTest {

    @InjectMocks
    private AdopterController adopterController;

    @Mock
    private AdopterService adopterService;

    List<Adopter> adopters = List.of(
            Adopter.builder().id(1).name("Edwin Soto").phoneNumber("111-111-1111").isOver18(true).build(),
            Adopter.builder().id(2).name("Mackenzie Soto").phoneNumber("222-222-2222").isOver18(false).build()
    );


    @Test
    void getAllAdopters() {
        when(adopterService.getAllAdopters()).thenReturn(adopters);
        ResponseEntity<?> response = adopterController.getAdopters();
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(2, ((List<?>) Objects.requireNonNull(response.getBody())).size());

    }

    @Test
    void getAllAdoptersEmpty() {
        when(adopterService.getAllAdopters()).thenReturn(List.of());
        ResponseEntity<?> response = adopterController.getAdopters();
        assertTrue(response.getStatusCode().is4xxClientError());
    }
    @Test
    void getAdopterById() {
        when(adopterService.getAdopterById(1)).thenReturn(Optional.ofNullable(adopters.get(0)));
        ResponseEntity<?> response = adopterController.getAdopterById(1);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    void getAdopterByInvalidId(){
        when(adopterService.getAdopterById(1)).thenReturn(Optional.empty());
        ResponseEntity<?> response = adopterController.getAdopterById(1);
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    void addAdopter() {
        Adopter newAdopter = Adopter.builder()
                .name("First Last")
                .email("first.last@email.com")
                .build();

        when(adopterService.createAdopter(newAdopter)).thenReturn(3);
        ResponseEntity<?> response = adopterController.addAdopter(newAdopter);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    @Disabled("To be continued...")
    void updateAdopter() {
        Adopter adopter = adopters.getFirst();

        adopter.setName("New Name");

        when(adopterService.getAdopterById(1)).thenReturn(Optional.ofNullable(adopters.getFirst()));
        when(adopterService.updateAdopter(adopter)).thenReturn(true);
        ResponseEntity<?> response = adopterController.updateAdopter(adopter, 1);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    void deleteValidAdopter() {
        when(adopterService.getAdopterById(1)).thenReturn(Optional.ofNullable(adopters.get(0)));
        when(adopterService.deleteAdopter(1)).thenReturn(1);
        ResponseEntity<?> response = adopterController.deleteAdopter(1);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    void deleteInvalidAdopter() {
        when(adopterService.getAdopterById(100)).thenReturn(Optional.empty());
        ResponseEntity<?> response = adopterController.deleteAdopter(100);
        assertTrue(response.getStatusCode().is4xxClientError());
    }
}