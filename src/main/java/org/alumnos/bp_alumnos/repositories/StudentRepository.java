package org.alumnos.bp_alumnos.repositories;

import org.alumnos.bp_alumnos.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
