package org.alumnos.bp_alumnos.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.alumnos.bp_alumnos.Model.Activity;
import org.alumnos.bp_alumnos.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@Tag(name = "Activities", description = "Activity Management System")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping
    @Operation(summary = "View a list of available activities", description = "Get a list of all activities", tags = { "Activities" })
    public List<Activity> getAllActivities() {
        return activityService.getAllActivities();
    }

    @PostMapping
    @Operation(summary = "Create a new activity", description = "Create a new activity", tags = { "Activities" })
    public Activity createActivity(@RequestBody Activity activity) {
        return activityService.createActivity(activity);
    }
}