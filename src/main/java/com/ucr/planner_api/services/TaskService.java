package com.ucr.planner_api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucr.planner_api.Dtos.TaskRequestDto;
import com.ucr.planner_api.entities.Course;
import com.ucr.planner_api.entities.Task;
import com.ucr.planner_api.entities.User;
import com.ucr.planner_api.exceptions.CourseNotFoundException;
import com.ucr.planner_api.exceptions.DuplicateTaskDataException;
import com.ucr.planner_api.exceptions.TaskNotFoundException;
import com.ucr.planner_api.exceptions.UserNotFoundException;
import com.ucr.planner_api.repositories.CourseRepository;
import com.ucr.planner_api.repositories.TaskRepository;
import com.ucr.planner_api.repositories.UserRepository;


@Service
public class TaskService implements ITaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;


    @Override
    public List<Task> getAll() {
        return taskRepository.getAll();
    }

     @Override
    public Task create(Task task) {

        if (taskRepository.existsByTaskName(task.getTaskName())) {
            throw new DuplicateTaskDataException("El nombre de la tarea ya existe: " + task.getTaskName());
        }
        return taskRepository.add(task);
    }

    @Override
public Task updateTask(UUID resourceId_Task, Task updatedData) {
    Task task = taskRepository.findByResourceIdTask(resourceId_Task)
        .orElseThrow(() -> new TaskNotFoundException("Tarea no encontrada"));

    task.setTaskName(updatedData.getTaskName());
    task.setDetails(updatedData.getDetails());
    task.setDueDate(updatedData.getDueDate());
    task.setPriority(updatedData.getPriority());
    task.setState(updatedData.getState());
    task.setDone(updatedData.getDone());
    task.setCourse(updatedData.getCourse());   
    task.setUser(updatedData.getUser());       

    return taskRepository.updateTask(task);
}

    @Override
    public void removeTask(UUID resourceId_Task) {
       var task = taskRepository.findByResourceIdTask(resourceId_Task).orElseThrow(() -> new TaskNotFoundException("Tarea no encontrada"));

       taskRepository.delete(task);
    }

    @Override
    public Task getByResourceId(UUID resourceId_Task) {
        return taskRepository.findByResourceIdTask(resourceId_Task)
        .orElseThrow(() -> new TaskNotFoundException("Tarea no encontrada con resourceId: " + resourceId_Task));
    }
    
}
    
