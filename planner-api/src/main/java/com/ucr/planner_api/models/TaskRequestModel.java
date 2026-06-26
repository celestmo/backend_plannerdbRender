package com.ucr.planner_api.models;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record TaskRequestModel(
    @NotNull(message = "ID de tarea requerido") Integer taskId,
    @NotBlank(message = "Nombre de tarea requerido") String taskName,
    @NotNull(message = "Fecha de creación requerida") LocalDateTime createdDate,
    @Future(message = "La fecha de entrega debe ser futura") LocalDateTime dueDate,
    @NotNull(message = "Estado de completado requerido") Boolean done,
    String details,
    @NotBlank(message = "Estado requerido") String state,
    @NotBlank(message = "Prioridad requerida") String priority,
    @NotBlank(message = "Curso requerido") String courseCode,
    @NotBlank(message = "Usuario requerido") String userId
) {}