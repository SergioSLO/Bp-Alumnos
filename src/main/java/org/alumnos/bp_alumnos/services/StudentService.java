package org.alumnos.bp_alumnos.services;

import org.alumnos.bp_alumnos.Model.Student;
import org.alumnos.bp_alumnos.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Obtener todos los estudiantes
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Crear un nuevo estudiante
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    // Obtener un estudiante por ID
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }
}
