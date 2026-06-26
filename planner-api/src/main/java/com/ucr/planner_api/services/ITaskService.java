package com.ucr.planner_api.services;

import java.util.List;
import java.util.UUID;

import com.ucr.planner_api.Dtos.TaskRequestDto;
import com.ucr.planner_api.entities.Task;

public interface ITaskService {
    List<Task> getAll();

    //Aquí CREATE
    Task create(Task task);
    
    //Update ->
    Task updateTask(UUID resourceId_Task, Task updatedData);

    void removeTask(UUID resourceId_Task);

    Task getByResourceId(UUID resourceId_Task);
}