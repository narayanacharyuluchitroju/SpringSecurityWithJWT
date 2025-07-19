package com.pract.test.controller;

import com.pract.test.model.Student;
import com.pract.test.service.StudentService;
import com.pract.test.studentDTO.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/greet")
    public String greet(){
        return "Hello, welcome to the Student Management System!";
    }


    @GetMapping("/all")
    public ResponseEntity<List<StudentDTO>> getStudents() {
        List<StudentDTO> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping
    public ResponseEntity<StudentDTO> getStudentById(@RequestParam(value = "id") UUID id) {
        StudentDTO student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @PostMapping("/create")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO student) {
        StudentDTO createdStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createdStudent);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(UUID id, Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent);
    }
}
