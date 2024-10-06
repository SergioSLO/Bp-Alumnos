package org.alumnos.bp_alumnos.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Students", description = "Student Management System")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    @Operation(summary = "View a list of available students", description = "Get a list of all students", tags = { "Students" })
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a student by Id", description = "Get a student by their ID", tags = { "Students" })
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentService.getStudentById(id);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new student", description = "Create a new student", tags = { "Students" })
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("/{id}/rockie")
    @Operation(summary = "Get student and their Rockie data", description = "Get student and their Rockie data by student ID", tags = { "Students" })
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