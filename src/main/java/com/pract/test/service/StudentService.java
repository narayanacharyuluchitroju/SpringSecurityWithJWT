package com.pract.test.service;

import com.pract.test.config.StudentMapper;
import com.pract.test.exception.StudentNotFoundException;
import com.pract.test.model.Student;
import com.pract.test.repository.StudentRepository;
import com.pract.test.studentDTO.StudentDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

//    @Autowired
//    private ModelMapper modelMapper;

    @Autowired
    private StudentMapper studentMapper;

    public StudentDTO getStudentById(UUID id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            throw new StudentNotFoundException("Student not found with id: " + id);
        }
        return studentMapper.toDTO(student.get());
    }

    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(student -> studentMapper.toDTO(student))
                .toList();
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = studentMapper.toEntity(studentDTO);
        student.setId(UUID.randomUUID()); // Ensure a new UUID is generated for the new student
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toDTO(savedStudent);
    }


    public Student updateStudent(UUID id, Student student) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        student.setId(id);
        return studentRepository.save(student);
    }

    public void deleteStudent(UUID id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }
}
