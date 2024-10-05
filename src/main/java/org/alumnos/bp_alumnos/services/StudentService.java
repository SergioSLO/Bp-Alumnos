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

    // URL de la API de Python como variable de entorno
    private final String PYTHON_API_URL = System.getenv("PYTHON_API_URL");

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

    // Obtener los datos del Rockie desde la API de Python
    public Map<String, Object> getRockieData(Long studentId) {
        // Construir la URL con el ID del estudiante
        String url = UriComponentsBuilder.fromHttpUrl(PYTHON_API_URL + "/rockie/{id_estudiante}")
                .buildAndExpand(studentId)
                .toString();

        // Realizar la solicitud GET a la API de Python
        try {
            return restTemplate.getForObject(url, HashMap.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el Rockie desde la API de Python: " + e.getMessage());
        }
    }
}

