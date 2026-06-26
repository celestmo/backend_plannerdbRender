package com.ucr.planner_api.Dtos;

import java.util.UUID;

public record CourseDto(
    UUID resourceId_Course,
    String courseName,
    String courseCode) {
}

