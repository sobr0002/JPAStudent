package org.example.jpastudent.controller;

import org.example.jpastudent.model.Student;
import org.example.jpastudent.repositories.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController //Specialiseret controller til at bygge REST API'er
// konverteres til JSON i HTTP-responset
public class StudentRestController {

    private final StudentRepository studentRepository;

    public StudentRestController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/students")
    public List<Student> students() {
        var obj = studentRepository.findAll();
        return obj;
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> studentById(@PathVariable int id) {
        Optional<Student> orgStudent = studentRepository.findById(id);
        if (orgStudent.isPresent()) {
            return new ResponseEntity<>(orgStudent.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }



    @GetMapping("addStudent")
    public List<Student> addStudent() {
        Student std = new Student();
        std.setName("Tut");
        std.setBornDate(LocalDate.of(2004, 10, 10));
        std.setBornTime(LocalTime.of(12, 3, 15));
        studentRepository.save(std);
        List<Student> lst = studentRepository.findAll();
        return lst;
    }

    @GetMapping("students/{name}")
    public ResponseEntity<List<Student>> getAllStudentsByName(@PathVariable String name) {
        List<Student> students = studentRepository.findAllByName(name);

        if (!students.isEmpty()) {
            return new ResponseEntity<>(students, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/student")
    @ResponseStatus(HttpStatus.CREATED)
    public Student postStudent(@RequestBody Student student) {
        System.out.println(student);
        return studentRepository.save(student);
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<Student> putStudent(@PathVariable int id, @RequestBody Student student) {
        Optional<Student> orgStudent = studentRepository.findById(id);
        if (orgStudent.isPresent()) {
            studentRepository.save(student);
            //return new ResponseEntity<>(student, HttpStatus.OK);
            return ResponseEntity.ok(student);
        }
        else {
            //return new ResponseEntity<>(new Student(), HttpStatus.NOT_FOUND);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        Optional<Student> orgStudent = studentRepository.findById(id);

        if (orgStudent.isPresent()) {
            studentRepository.deleteById(id);
            return ResponseEntity.ok("Student deleted");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
    }


}
