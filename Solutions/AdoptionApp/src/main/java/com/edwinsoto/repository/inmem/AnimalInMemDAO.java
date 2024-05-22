package com.edwinsoto.repository.inmem;

import com.edwinsoto.model.Animal;
import com.edwinsoto.repository.DAO;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("inmem")
public class AnimalInMemDAO implements DAO<Animal> {

    private final List<Animal> animals = new ArrayList<>();
    private int nextId = 1;

    public AnimalInMemDAO() {
    }


    @Override
    public List<Animal> findAll() {
        return animals;
    }

    @Override
    public Optional<Animal> findById(int id) {
        return animals.stream().filter(a -> a.getId() == id).findFirst();
    }

    @Override
    public Animal create(Animal animal) {
        Integer id = animal.getId();

        if (id == 0) {
            animal.setId(nextId++);
            animals.add(animal);
        }
        return animal;
    }

    @Override
    public Animal update(Animal animal) {
        delete(animal.getId());
        animals.add(animal);
        return animal;
    }

    @Override
    public int delete(int id) {
        animals.removeIf(a -> a.getId() == id);
        return 1;
    }

    @PostConstruct
    public void init() {
        Animal animal1 = Animal.builder()
                .id(nextId++)
                .name("Gracie")
                .type("DOG")
                .dob(LocalDate.of(2022, 1, 23))
                .breed("Terrier Mix")
                .build();

        Animal animal2 = Animal.builder()
                .id(nextId++)
                .name("Fido")
                .type("CAT")
                .dob(LocalDate.of(2023, 7, 29))
                .breed("Siamese")
                .build();

        Animal animal3 = Animal.builder()
                .id(nextId++)
                .name("Fred")
                .type("CAT")
                .dob(LocalDate.of(2024, 1, 23))
                .breed("Persian")
                .build();

        Animal animal4 = Animal.builder()
                .id(nextId++)
                .name("Spot")
                .type("DOG")
                .dob(LocalDate.of(2023, 12, 24))
                .breed("Labrador")
                .build();

        Animal animal5 = Animal.builder()
                .id(nextId++)
                .name("Slimy")
                .type("TURTLE")
                .dob(LocalDate.of(2022, 03, 23))
                .breed("Yellow Belly Slider")
                .build();

        animals.add(animal1);
        animals.add(animal2);
        animals.add(animal3);
        animals.add(animal4);
        animals.add(animal5);
    }
}
