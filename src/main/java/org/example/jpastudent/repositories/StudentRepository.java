package org.example.jpastudent.repositories;

import org.example.jpastudent.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//Koden gemmer en række af Student-objektet i tabellen

public interface StudentRepository extends JpaRepository<Student, Integer> { //Navn på Entity-klasse + datatype for ID

    List<Student> findAllByName(String name);

}
