package com.edwinsoto.repository.jpa;

import com.edwinsoto.model.Animal;
import com.edwinsoto.repository.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Profile("jpa")
@Repository
public class AnimalJPADAOImpl implements DAO<Animal> {

    private final AnimalJPA jpaDAO;

    @Autowired
    public AnimalJPADAOImpl(AnimalJPA jpaDAO) {
        this.jpaDAO = jpaDAO;
    }


    @Override
    public List<Animal> findAll() {
        return jpaDAO.findAll();
    }

    @Override
    public Optional<Animal> findById(int id) {
        return jpaDAO.findById(id);
    }

    @Override
    public Animal create(Animal animal) {
        return jpaDAO.save(animal);
    }

    // TODO: Need to update
    @Override
    public Animal update(Animal animal) {
        return jpaDAO.save(animal);
    }

    @Override
    public int delete(int id) {

        jpaDAO.deleteById(id);
        return 1;
    }
}
