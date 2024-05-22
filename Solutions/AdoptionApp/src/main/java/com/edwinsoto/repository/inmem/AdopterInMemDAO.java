package com.edwinsoto.repository.inmem;

import com.edwinsoto.model.Adopter;
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
public class AdopterInMemDAO implements DAO<Adopter> {

    private final List<Adopter> adopters = new ArrayList<>();

    private int nextId = 1;

    public AdopterInMemDAO() {
    }


    @Override
    public List<Adopter> findAll() {
        return adopters;
    }


    @Override
    public Optional<Adopter> findById(int id) {
        return adopters.stream().filter(a -> a.getId() == id).findFirst();
    }


    @Override
    public Adopter create(Adopter adopter) {
        Integer id = adopter.getId();

        if (id == 0) {
            adopter.setId(nextId++);
            adopters.add(adopter);
        }
        return adopter;
    }


    @Override
    public Adopter update(Adopter adopter) {
        delete(adopter.getId());
        adopters.add(adopter);
        return adopter;
    }


    @Override
    public int delete(int id) {
        adopters.removeIf(a -> a.getId() == id);
        return 1;
    }

    @PostConstruct
    public void init() {
        // Use the PostConstruct to init the adopters
        Adopter person1 = Adopter.builder()
                .id(nextId++)
                .name("Edwin Soto")
                .phoneNumber("111-111-1111")
                .email("edwin.soto@email.com")
                .isOver18(true)
                .dateCreated(LocalDate.now())
                .build();

        Adopter person2 = Adopter.builder()
                .id(nextId++)
                .name("Mackenzie Soto")
                .phoneNumber("222-222-2222")
                .email("mackenzie.soto@email.com")
                .isOver18(true)
                .dateCreated(LocalDate.now())
                .build();

        Adopter person3 = Adopter.builder()
                .id(nextId++)
                .name("Liam Soto")
                .phoneNumber("333-333-3333")
                .email("liam.soto@email.com")
                .isOver18(false)
                .dateCreated(LocalDate.now())
                .build();

        Adopter person4 = Adopter.builder()
                .id(nextId++)
                .name("Leila Soto")
                .phoneNumber("444-444-4444")
                .email("leila.soto@email.com")
                .isOver18(false)
                .dateCreated(LocalDate.now())
                .build();

        Adopter person5 = Adopter.builder()
                .id(nextId++)
                .name("Levi Soto")
                .phoneNumber("555-555-5555")
                .email("levi.soto@email.com")
                .isOver18(false)
                .dateCreated(LocalDate.now())
                .build();

        adopters.add(person1);
        adopters.add(person2);
        adopters.add(person3);
        adopters.add(person4);
        adopters.add(person5);
    }
}
