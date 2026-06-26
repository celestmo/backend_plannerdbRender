package com.ucr.planner_api.models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskResponseModel (
     @NotNull(message = "ID de tarea requerido") Integer taskId,
     @NotBlank(message = "Nombre de tarea requerido") String taskName,
     @NotNull(message = "Fecha de creación requerida") LocalDateTime createdDate,
     @Future(message = "La fecha de entrega debe ser futura") LocalDateTime dueDate,
     @NotNull(message = "Estado de completado requerido") Boolean done,
     String details,
     @NotBlank(message = "Estado requerido") String state,
     @NotBlank(message = "Prioridad requerida") String priority,
     @NotNull(message = "Curso requerido") String courseCode,
     @NotBlank(message = "Usuario requerido") String userId,
     @NotNull(message = "ID de recurso requerido") UUID resourceId
) {}