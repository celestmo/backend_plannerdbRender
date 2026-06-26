package com.ucr.planner_api.Facade;

import java.util.List;
import java.util.UUID;

import com.ucr.planner_api.Dtos.TaskDto;
import com.ucr.planner_api.Dtos.TaskRequestDto;

public interface ITaskFacade {
    
    List<TaskDto> getAll();

//Aquí CREATE
    TaskDto create(TaskRequestDto dto);

//Update ->
    TaskDto updateTask(UUID resourceId_Task, TaskRequestDto taskDto);

//Delete

    void removeTask(UUID resourceId_Task);

    TaskDto getByResourceId(UUID resourceId_Task);
}
