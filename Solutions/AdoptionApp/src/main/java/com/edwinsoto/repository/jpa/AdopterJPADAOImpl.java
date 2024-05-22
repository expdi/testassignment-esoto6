package com.edwinsoto.repository.jpa;

import com.edwinsoto.model.Adopter;
import com.edwinsoto.repository.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Profile({"jpa", "tc_jpa"})
@Repository
public class AdopterJPADAOImpl implements DAO<Adopter> {

    private final AdopterJPA jpaDAO;

    @Autowired
    public AdopterJPADAOImpl(AdopterJPA jpaDAO) {

        this.jpaDAO = jpaDAO;
    }

    @Override
    public List<Adopter> findAll() {
        return jpaDAO.findAll();
    }

    @Override
    public Optional<Adopter> findById(int id) {
        return jpaDAO.findById(id);
    }

    @Override
    public Adopter create(Adopter adopter) {
        return jpaDAO.save(adopter);
    }

    // TODO: Need to update
    @Override
    public Adopter update(Adopter adopter) {
        return jpaDAO.save(adopter);
    }

    @Override
    public int delete(int id) {

        jpaDAO.deleteById(id);
        return 1;
    }
}
