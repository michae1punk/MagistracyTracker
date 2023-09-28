package com.myakishev.magistracyTracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int numberInList;

    @Column
    private String snils;

    @Column
    private int testPoints;

    @Column
    private int additionalPoints;

    @Column
    private int sumPoints;
}
