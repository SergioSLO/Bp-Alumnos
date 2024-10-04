package org.alumnos.bp_alumnos.services;

import org.alumnos.bp_alumnos.Model.Activity;
import org.alumnos.bp_alumnos.repositories.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    // Obtener todas las actividades
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    // Crear una nueva actividad
    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }
}
