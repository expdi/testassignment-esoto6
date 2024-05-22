package com.edwinsoto.service;

import com.edwinsoto.model.Adopter;
import com.edwinsoto.repository.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdopterService {

    private final DAO<Adopter> adopterDAO;

    @Autowired
    public AdopterService(DAO<Adopter> adopterDAO) {
        this.adopterDAO = adopterDAO;
    }

    public List<Adopter> getAllAdopters() {
        return adopterDAO.findAll();
    }

    public Optional<Adopter> getAdopterById(int id) {
        //TODO: Handle when index is out of range. Creating NullPointerException
        // Handle here or in the controller?
        Optional<Adopter> optAdopter = adopterDAO.findById(id);
        return optAdopter;
    }

    public int createAdopter(Adopter adopter) {
        Adopter newAdopter = adopterDAO.create(adopter);
        return newAdopter.getId();
    }

    public boolean updateAdopter(Adopter adopter) {
        Adopter updatedAdopter = adopterDAO.update(adopter);
        System.out.println(updatedAdopter.toString());
        return true;
    }

    public int deleteAdopter(int id) {
        return adopterDAO.delete(id);
    }

}

