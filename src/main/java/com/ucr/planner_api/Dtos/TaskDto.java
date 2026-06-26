package com.ucr.planner_api.Dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ucr.planner_api.entities.Course;
import com.ucr.planner_api.entities.User;

public record TaskDto(
    UUID resourceId_Task,
    Integer taskId,
    String taskName,
    LocalDateTime createdDate,
    LocalDateTime dueDate,
    Boolean done,
    String details,
    String state,
    String priority,
    User user,
    Course course,
    UUID resourceId_User,
    UUID resourceId_Course) {
}
