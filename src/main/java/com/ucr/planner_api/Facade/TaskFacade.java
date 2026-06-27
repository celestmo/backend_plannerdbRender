package com.ucr.planner_api.Facade;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ucr.planner_api.Dtos.TaskDto;
import com.ucr.planner_api.Dtos.TaskRequestDto;
import com.ucr.planner_api.mappers.TaskMapper;
import com.ucr.planner_api.services.ICourseService;
import com.ucr.planner_api.services.ITaskService;
import com.ucr.planner_api.services.IUserService;

import jakarta.transaction.Transactional;

@Component
public class TaskFacade implements ITaskFacade {

    private static final String DEFAULT_COURSE_CODE = "IF-0001";

    @Autowired
    private ITaskService taskService;

    @Autowired 
    private TaskMapper taskMapper;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICourseService courseService;

    @Override
    public List<TaskDto> getAll() {
        return taskMapper.toTaskDtosList(taskService.getAll());
    }

    //Aquí CREATE
public TaskDto create(TaskRequestDto dto) {
     // 1. Verificar que el usuario existe
    var user = userService.getById(dto.getUserId());

    // 2. Verificar que el curso existe, o usar curso predeterminado si no viene en la solicitud
    var courseCode = dto.getCourseCode();
    if (courseCode == null || courseCode.isBlank()) {
        courseCode = DEFAULT_COURSE_CODE;
    }
    var course = courseService.getById(courseCode);

    // 3. Convertir DTO a entidad
    var taskEntity = taskMapper.toEntity(dto);

    // 4. Asignar relaciones
    taskEntity.setUser(user);
    taskEntity.setCourse(course);

    // 5. Guardar y retornar como DTO
    var saved = taskService.create(taskEntity);
    return taskMapper.toTaskDto(saved);
}

//Update ->

     @Override
    public TaskDto updateTask(UUID resourceId_Task, TaskRequestDto taskDto) {
    var user = userService.getById(taskDto.getUserId());

    var courseCode = taskDto.getCourseCode();
    if (courseCode == null || courseCode.isBlank()) {
        courseCode = DEFAULT_COURSE_CODE;
    }
    var course = courseService.getById(courseCode);

    var taskData = taskMapper.toEntity(taskDto);
    taskData.setUser(user);
    taskData.setCourse(course);

    var updated = taskService.updateTask(resourceId_Task, taskData);
    return taskMapper.toTaskDto(updated);
    }

    @Override
    @Transactional
    public void removeTask(UUID resourceId_Task) {
        taskService.removeTask(resourceId_Task);
    
    }

    @Override
    public TaskDto getByResourceId(UUID resourceId_Task) {
        throw new UnsupportedOperationException("Unimplemented method 'getByResourceId'");
    }

    

}

