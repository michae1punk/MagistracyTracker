package com.myakishev.magistracyTracker.dao;

import com.myakishev.magistracyTracker.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao extends JpaRepository<Student, Long> {
    Student findOneBySnils(String Snils);
}
