package com.edwinsoto.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "animals")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Animal {

//    public enum PetType {
//        DOG,
//        CAT,
//        TURTLE
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    protected int id;

    @NotNull
    @Column(name = "NAME")
    protected String name;

    @Column(name = "PET_TYPE")
    protected String type;

    @Column(name = "DOB")
    protected LocalDate dob;

    @Column(name = "BREED")
    protected String breed;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column(name = "ADOPTED_DATE")
    protected LocalDate adoptedDate;

    @JoinColumn(name = "ADOPTER_ID")
    //@ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    protected Adopter owner;
}

