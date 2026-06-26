package com.ucr.planner_api.Dtos;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TaskRequestDto {
    private Integer taskId;
    private String taskName;
    private LocalDateTime createdDate;
    private LocalDateTime dueDate;
    private Boolean done;
    private String details;
    private String state;
    private String priority;
    private String userId;
    private String courseCode;

}