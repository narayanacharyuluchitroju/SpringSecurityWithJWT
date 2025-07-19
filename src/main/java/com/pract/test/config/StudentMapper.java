package com.pract.test.config;

import com.pract.test.model.Student;
import com.pract.test.studentDTO.StudentDTO;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDTO toDTO(Student student);
    Student toEntity(StudentDTO studentDTO);
}
