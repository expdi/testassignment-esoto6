package com.edwinsoto.repository.jdbc;

import com.edwinsoto.model.Animal;
import com.edwinsoto.repository.DAO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@Profile({"tc", "jdbc"})
public class AnimalJDBCDAO extends ConnectionDAO implements DAO<Animal> {

    @Override
    public List<Animal> findAll() {

        List<Animal> animals = Collections.emptyList();

        String sql = "SELECT * FROM animals";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            animals = new ArrayList<>();

            while (rs.next()) {
                Animal animal = Animal.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .type(rs.getString("pet_type"))
                        .dob(rs.getDate("dob").toLocalDate())
                        .breed(rs.getString("breed"))
                        .adoptedDate(rs.getDate("adopted_date") != null ? rs.getDate("adopted_date").toLocalDate() : null)
                        .build();

                animals.add(animal);
            }
        } catch (SQLException sqe) {
            sqe.printStackTrace();
        }
        return animals;
    }


    @Override
    public Optional<Animal> findById(int id) {
        Optional<Animal> animal = Optional.empty();

        String sql = "SELECT * FROM animals WHERE id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);


            try (ResultSet rs = stmt.executeQuery()) {
                Animal resAnimal = null;
                if (rs.next()) {
                    resAnimal = Animal.builder()
                            .id(rs.getInt("id"))
                            .name(rs.getString("name"))
                            .type(rs.getString("pet_type"))
                            .dob(rs.getDate("dob").toLocalDate())
                            .breed(rs.getString("breed"))
                            .adoptedDate(rs.getDate("adopted_date") != null ? rs.getDate("adopted_date").toLocalDate() : null)
                            .build();


                }
                animal = Optional.of(resAnimal);
            }
        } catch (SQLException sqe) {
            sqe.printStackTrace();
        }
        return animal;
    }


    @Override
    public Animal create(Animal animal) {
        String sql = "INSERT INTO animals (name, pet_type, dob, breed) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, animal.getName());
            stmt.setString(2, animal.getType());
            stmt.setDate(3, Date.valueOf(animal.getDob()));
            stmt.setString(4, animal.getBreed());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    animal.setId(rs.getInt(1));
                }
            }
        } catch (SQLException sqe) {
            sqe.printStackTrace();
        }
        return animal;
    }


    @Override
    public Animal update(Animal animal) {

        String sql = "UPDATE animals SET name = ?, pet_type = ?, dob = ?, breed = ?, adopted_date = ? WHERE id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, animal.getName());
            stmt.setString(2, animal.getType());
            stmt.setDate(3, Date.valueOf(animal.getDob()));
            stmt.setString(4, animal.getBreed());
            stmt.setDate(5, Date.valueOf(LocalDate.now()));
            stmt.setInt(6, animal.getId());

            stmt.executeUpdate();

        } catch (SQLException sqe) {
            sqe.printStackTrace();
        }
        return animal;
    }

    @Override
    public int delete(int id) {
        int rowsAffected = 0;

        String sql = "DELETE FROM animals WHERE id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)

        ) {
            stmt.setInt(1, id);
            rowsAffected = stmt.executeUpdate();

        } catch (SQLException sqe) {
            sqe.printStackTrace();
        }
        return rowsAffected;
    }
}
