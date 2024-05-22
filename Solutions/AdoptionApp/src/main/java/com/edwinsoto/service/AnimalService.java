package com.edwinsoto.service;

import com.edwinsoto.model.Animal;
import com.edwinsoto.repository.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    private final DAO<Animal> animalDAO;

    @Autowired
    public AnimalService(DAO<Animal> animalDAO) {
        this.animalDAO = animalDAO;
    }

    public List<Animal> getAllAnimals() {
        return animalDAO.findAll();
    }

    public Optional<Animal> getAnimalById(int id) {
        //TODO: Handle when index is out of range. Creating NullPointerException
        // Handle here or in the controller?
        Optional<Animal> optAnimal = animalDAO.findById(id);
        return optAnimal;
    }

    public int createAnimal(Animal animal) {
        Animal newAnimal = animalDAO.create(animal);
        return newAnimal.getId();
    }

    public boolean updateAnimal(Animal animal) {
        Animal updatedAnimal = animalDAO.update(animal);
        System.out.println(updatedAnimal.toString());
        return updatedAnimal != null;
    }

    public int deleteAnimal(int id) {
        return animalDAO.delete(id);
    }

}
