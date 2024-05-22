package com.edwinsoto.repository.jdbc;

import com.edwinsoto.model.Adopter;
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
public class AdopterJDBCDAO extends ConnectionDAO implements DAO<Adopter> {


    @Override
    public List<Adopter> findAll() {

        List<Adopter> adopters = Collections.emptyList();

        String sql = "SELECT * FROM adopters";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            adopters = new ArrayList<>();

            while (rs.next()) {
                Adopter adopter = Adopter.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .phoneNumber(rs.getString("phone_number"))
                        .email(rs.getString("email"))
                        .isOver18(rs.getBoolean("is18"))
                        .dateCreated(rs.getDate("date_created").toLocalDate())
                        .dateModified(rs.getDate("date_modified") != null ? rs.getDate("date_modified").toLocalDate() : null)
                        .build();

                adopters.add(adopter);
            }
        } catch (SQLException sqe) {
            sqe.printStackTrace();
        }
        return adopters;
    }


    @Override
    public Optional<Adopter> findById(int id) {
        Optional<Adopter> adopter = Optional.empty();

        String sql = "SELECT * FROM adopters WHERE id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);


            try (ResultSet rs = stmt.executeQuery()) {
                Adopter resAdopter = null;
                if (rs.next()) {
                    resAdopter = Adopter.builder()
                            .id(rs.getInt("id"))
                            .name(rs.getString("name"))
                            .phoneNumber(rs.getString("phone_number"))
                            .email(rs.getString("email"))
                            .isOver18(rs.getBoolean("is18"))
                            .dateCreated(rs.getDate("date_created").toLocalDate())
                            .dateModified(rs.getDate("date_modified") != null ? rs.getDate("date_modified").toLocalDate() : null)
                            .build();


                }
                adopter = Optional.of(resAdopter);
            }
        } catch (SQLException sqe) {
            sqe.printStackTrace();
        }
        return adopter;
    }


    @Override
    public Adopter create(Adopter adopter) {
        String sql = "INSERT INTO adopters (name, phone_number, email, is18, date_created) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, adopter.getName());
            stmt.setString(2, adopter.getPhoneNumber());
            stmt.setString(3, adopter.getEmail());
            stmt.setBoolean(4, adopter.isOver18());
            stmt.setDate(5, Date.valueOf(LocalDate.now()));

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    adopter.setId(rs.getInt(1));
                }
            }
        } catch (SQLException sqe) {
            sqe.printStackTrace();
        }
        return adopter;
    }


    @Override
    public Adopter update(Adopter adopter) {

        String sql = "UPDATE adopters SET name = ?, phone_number = ?, email = ?, is18 = ?, date_modified = ? WHERE id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, adopter.getName());
            stmt.setString(2, adopter.getPhoneNumber());
            stmt.setString(3, adopter.getEmail());
            stmt.setBoolean(4, adopter.isOver18());
            stmt.setDate(5, Date.valueOf(LocalDate.now()));
            stmt.setInt(6, adopter.getId());

            stmt.executeUpdate();

        } catch (SQLException sqe) {
            sqe.printStackTrace();
        }
        return adopter;
    }

    @Override
    public int delete(int id) {
        int rowsAffected = 0;

        String sql = "DELETE FROM adopters WHERE id = ?";

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
