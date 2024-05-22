package com.edwinsoto.repository.jpa;

import com.edwinsoto.model.Animal;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("jpa")
public interface AnimalJPA extends JpaRepository<Animal, Integer> {
}
