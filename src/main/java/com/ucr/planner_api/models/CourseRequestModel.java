package com.ucr.planner_api.models;

import jakarta.validation.constraints.NotBlank;

public record CourseRequestModel(
    @NotBlank(message = "Nombre curso requerido") String courseName,
    @NotBlank(message = "Código curso requerido") String courseCode) {

}
