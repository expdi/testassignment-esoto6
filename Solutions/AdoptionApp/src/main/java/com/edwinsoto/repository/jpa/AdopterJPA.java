package com.edwinsoto.repository.jpa;

import com.edwinsoto.model.Adopter;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile({"jpa", "tc_jpa"})
public interface AdopterJPA extends JpaRepository<Adopter, Integer> {
}
