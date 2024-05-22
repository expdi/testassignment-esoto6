package com.edwinsoto.controller;

import com.edwinsoto.model.Adopter;
import com.edwinsoto.service.AdopterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/adopter")
public class AdopterController {

    private final AdopterService adopterService;

    @Autowired
    public AdopterController(AdopterService adopterService) {
        this.adopterService = adopterService;
    }

    @GetMapping
    public ResponseEntity<?> getAdopters() {
        List<Adopter> adopters = adopterService.getAllAdopters();
        if (adopters.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(adopters);
    }

    @GetMapping("/id={id}")
    public ResponseEntity<Object> getAdopterById(@PathVariable("id") int id) {
        Optional<Adopter> adopter = adopterService.getAdopterById(id);
        if (adopter.isPresent()) {
            return ResponseEntity.ok().body(adopter);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<Integer> addAdopter(@RequestBody @Valid Adopter adopter) {
        int id = adopterService.createAdopter(adopter);

        if (id > 0) {
            return ResponseEntity.ok().body(id);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/id={id}")
    public ResponseEntity<Boolean> updateAdopter(@RequestBody @Valid Adopter adopter, @PathVariable("id") int id) {
        Optional<Adopter> foundAdopter = adopterService.getAdopterById(id);
        if (foundAdopter.isPresent()) {
            Adopter upAdopter = foundAdopter.get();

            if (adopter.getName() != null) {
                upAdopter.setName(adopter.getName());
            }
            if (adopter.getPhoneNumber() != null) {
                upAdopter.setPhoneNumber(adopter.getPhoneNumber());
            }
            if (adopter.getEmail() != null) {
                upAdopter.setEmail(adopter.getEmail());
            }
            boolean isUpdated = adopterService.updateAdopter(upAdopter);
            return ResponseEntity.ok().body(isUpdated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/id={id}")
    public ResponseEntity<Integer> deleteAdopter(@PathVariable("id") int id) {
        Optional<Adopter> foundAdopter = adopterService.getAdopterById(id);
        if (foundAdopter.isPresent()) {
            int rowsAffected = adopterService.deleteAdopter(id);
            return ResponseEntity.ok().body(rowsAffected);
        }
        return ResponseEntity.notFound().build();
    }
}
