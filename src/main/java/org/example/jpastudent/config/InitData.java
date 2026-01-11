package org.example.jpastudent.config;

import org.example.jpastudent.model.Student;
import org.example.jpastudent.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component //For at run-metoden udføres
public class InitData implements CommandLineRunner {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
        Student s1 = new Student();
        s1.setName("Sofie");
        s1.setBornDate(LocalDate.of(1995, 8, 28));
        s1.setBornTime(LocalTime.of(2, 10, 14));
        studentRepository.save(s1);

        Student s2 = new Student();
        s2.setName("Morten");
        s2.setBornDate(LocalDate.of(2008, 10, 12));
        s2.setBornTime(LocalTime.of(10, 8, 10));
        studentRepository.save(s2);

        Student s3 = new Student();
        s3.setName("Bo");
        s3.setBornDate(LocalDate.of(1961, 9, 29));
        s3.setBornTime(LocalTime.of(6, 33, 10));
        studentRepository.save(s3);

        Student s4 = new Student("Bob", LocalDate.of(2005, 10, 31), LocalTime.of(3, 4, 10));
        Student s5 = new Student("Alice", LocalDate.of(2004, 5, 12), LocalTime.of(8, 15, 30));
        Student s6 = new Student("Charlie", LocalDate.of(2006, 1, 23), LocalTime.of(14, 45, 5));
        Student s7 = new Student("Diana", LocalDate.of(2003, 7, 8), LocalTime.of(11, 20, 55));
        Student s8 = new Student("Ethan", LocalDate.of(2005, 12, 19), LocalTime.of(9, 0, 0));
        Student s9 = new Student("Fiona", LocalDate.of(2004, 3, 3), LocalTime.of(16, 30, 25));
        Student s10 = new Student("George", LocalDate.of(2006, 9, 14), LocalTime.of(7, 5, 40));

        studentRepository.save(s4);
        studentRepository.save(s5);
        studentRepository.save(s6);
        studentRepository.save(s7);
        studentRepository.save(s8);
        studentRepository.save(s9);
        studentRepository.save(s10);
    }
}
