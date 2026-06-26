package com.ucr.planner_api.mappers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ucr.planner_api.Dtos.TaskDto;
import com.ucr.planner_api.Dtos.TaskRequestDto;
import com.ucr.planner_api.entities.Task;
import com.ucr.planner_api.models.TaskRequestModel;
import com.ucr.planner_api.models.TaskResponseModel;

@Component
public class TaskMapper {

    public TaskDto toTaskDto(Task task) {
        if (task == null) {
            return null;
        }

        return new TaskDto(
                task.getResourceIdTask(),
                task.getTaskId(),
                task.getTaskName(),
                task.getCreatedDate(),
                task.getDueDate(),
                task.getDone(),
                task.getDetails(),
                task.getState(),
                task.getPriority(),
                task.getUser(),
                task.getCourse(),
                task.getUser().getResourceIdUser(),
                task.getCourse().getResourceIdCourse()
        );

        
    }

    public List<TaskDto> toTaskDtosList(List<Task> tasks) {
        if (tasks == null) {
            return null;
        }

        return tasks.stream()
                .map(this::toTaskDto)
                .collect(Collectors.toList());
    }

   public TaskResponseModel toTaskResponseModel(TaskDto taskDto) {
    if (taskDto == null) {
        return null;
    }

    return new TaskResponseModel(
            taskDto.taskId(),
            taskDto.taskName(),
            taskDto.createdDate(),
            taskDto.dueDate(),
            taskDto.done(),
            taskDto.details(),
            taskDto.state(),
            taskDto.priority(),
            taskDto.course().getCourseCode(),
            taskDto.user().getUserId(),
            taskDto.resourceId_Task()
    );
}


    public List<TaskResponseModel> toTaskResponseModelList(List<TaskDto> taskDtos) {
        if (taskDtos == null) {
            return null;
        }

        return taskDtos.stream()
                .map(this::toTaskResponseModel)
                .collect(Collectors.toList());
    }

    //CREATE TOTASKENTITY
    public Task toEntity(TaskRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Task task = new Task();

        task.setTaskName(dto.getTaskName());
        task.setDueDate(dto.getDueDate());
        task.setDetails(dto.getDetails());
        task.setPriority(dto.getPriority());

        task.setCreatedDate(java.time.LocalDateTime.now());
        task.setDone(false);
        task.setState("pending");
        task.setResourceIdTask(UUID.randomUUID());

        return task;
    }


    //Update->
      public TaskRequestDto toTaskRequestDto(TaskRequestModel task) {
        if (task == null) {
            return null;
        }

        TaskRequestDto taskDto = new TaskRequestDto();
        taskDto.setTaskId(task.taskId());
        taskDto.setTaskName(task.taskName());   
        taskDto.setCreatedDate(task.createdDate());
        taskDto.setDueDate(task.dueDate());
        taskDto.setDone(task.done());
        taskDto.setDetails(task.details());
        taskDto.setState(task.state());
        taskDto.setPriority(task.priority());
        taskDto.setCourseCode(task.courseCode());
        taskDto.setUserId(task.userId());

        return taskDto;
    }
    
}

