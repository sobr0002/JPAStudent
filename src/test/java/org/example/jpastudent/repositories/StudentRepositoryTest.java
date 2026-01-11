package org.example.jpastudent.repositories;

import org.example.jpastudent.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    void testOneTim() {
        List<Student> studentList = studentRepository.findAllByName("Tim");
        assertEquals(1, studentList.size());
    }

    @Test
    void testOneMorten() {
        List<Student> studentList = studentRepository.findAllByName("Morten");
        assertEquals(1, studentList.size());
    }

}