package org.alumnos.bp_alumnos.services;

import org.alumnos.bp_alumnos.Model.Student;
import org.alumnos.bp_alumnos.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    private final RestTemplate restTemplate = new RestTemplate();
// con el network se podrá acceder al contenedor de python
    private final String PYTHON_API_URL = "http://rockie_container:8001";

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Map<String, Object> getRockieData(Long studentId) {

        String url = UriComponentsBuilder.fromHttpUrl(PYTHON_API_URL + "/rockie/{id_estudiante}")
                .buildAndExpand(studentId)
                .toString();

        try {
            return restTemplate.getForObject(url, HashMap.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el Rockie desde la API de Python: " + e.getMessage());
        }
    }
}

