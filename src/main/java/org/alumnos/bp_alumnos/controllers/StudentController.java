package org.alumnos.bp_alumnos.controllers;

import org.alumnos.bp_alumnos.Model.Student;
import org.alumnos.bp_alumnos.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentService.getStudentById(id);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    // Nuevo endpoint para obtener la información del estudiante y su Rockie
    @GetMapping("/{id}/rockie")
    public ResponseEntity<Map<String, Object>> getStudentAndRockie(@PathVariable Long id) {
        Optional<Student> student = studentService.getStudentById(id);

        if (student.isPresent()) {
            Map<String, Object> rockieData = studentService.getRockieData(id);

            Map<String, Object> response = Map.of(
                "student", student.get(),
                "rockie", rockieData
            );

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

