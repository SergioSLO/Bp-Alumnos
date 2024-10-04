package org.alumnos.bp_alumnos.repositories;

import org.alumnos.bp_alumnos.Model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
