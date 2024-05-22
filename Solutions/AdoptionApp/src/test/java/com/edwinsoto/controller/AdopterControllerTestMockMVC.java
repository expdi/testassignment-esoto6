package com.edwinsoto.controller;

import com.edwinsoto.model.Adopter;
import com.edwinsoto.service.AdopterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = AdopterController.class)
@ContextConfiguration(classes = AdopterController.class)
class AdopterControllerTestMockMVC {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdopterService adopterService;

    private List<Adopter> adopters;

    @BeforeEach
    void setUp() {
        adopters = new ArrayList<>();
        Adopter person1 = Adopter.builder()
                .id(1)
                .name("Edwin Soto")
                .phoneNumber("111-111-1111")
                .email("edwin.soto@email.com")
                .isOver18(true)
                .dateCreated(LocalDate.now())
                .build();

        Adopter person2 = Adopter.builder()
                .id(2)
                .name("Mackenzie Soto")
                .phoneNumber("222-222-2222")
                .email("mackenzie.soto@email.com")
                .isOver18(true)
                .dateCreated(LocalDate.now())
                .build();
        adopters.add(person1);
        adopters.add(person2);
    }

    @Test
    void getAllAdopters() throws Exception {

        assertFalse(adopters.isEmpty());
        when(adopterService.getAllAdopters()).thenReturn(adopters);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/adopter")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    void getAllAdoptersEmpty() throws Exception {
        when(adopterService.getAllAdopters()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/adopter")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void getAdopterByValidId() throws Exception{
        when(adopterService.getAdopterById(1)).thenReturn(Optional.ofNullable(adopters.getFirst()));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/adopter/id=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    void getAdopterByInvalidId() throws Exception{
        when(adopterService.getAdopterById(25)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/adopter/id=5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @Disabled("Need to resolve this test")
    void addAdopter() throws Exception {
        Adopter newAdopter = Adopter.builder()
                .name("New Name")
                .email("new.name@email.com")
                .phoneNumber("999-999-9999")
                .isOver18(true)
                .dateCreated(LocalDate.now())
                .build();

        when(adopterService.createAdopter(newAdopter)).thenReturn(3);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/adopter")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(newAdopter)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(3));

    }

    @Test
    @Disabled("Need to finish...")
    void updateAdopter() throws Exception{

        Adopter updatedAdopter = Adopter.builder()
                .name("New Name")
                .build();

        when(adopterService.getAdopterById(1)).thenReturn(Optional.ofNullable(updatedAdopter));
        when(adopterService.updateAdopter(updatedAdopter)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/v1/adopter/id=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(true));

    }

    @Test
    @Disabled("Need to finish")
    void deleteAdopter() throws Exception {

        when(adopterService.deleteAdopter(1)).thenReturn(1);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/v1/adopter/id=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}