package com.ucr.planner_api.controllers;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ucr.planner_api.Facade.ITaskFacade;
import com.ucr.planner_api.mappers.TaskMapper;
import com.ucr.planner_api.models.TaskRequestModel;
import com.ucr.planner_api.models.TaskResponseModel;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private ITaskFacade taskFacade;
    @Autowired
    private TaskMapper taskMapper;

    @GetMapping
    public ResponseEntity<List<TaskResponseModel>> getAll() {
        var tasks = taskFacade.getAll();
        var response = taskMapper.toTaskResponseModelList(tasks);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{resourceId}")
    public ResponseEntity<TaskResponseModel> getByResourceId(@PathVariable UUID resourceId) {
        var taskDto = taskFacade.getByResourceId(resourceId);
        var response = taskMapper.toTaskResponseModel(taskDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<TaskResponseModel> create(
            @Valid @RequestBody TaskRequestModel request) {
        var dto = taskMapper.toTaskRequestDto(request);
        var created = taskFacade.create(dto);
        var response = taskMapper.toTaskResponseModel(created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{resourceId}")
    public ResponseEntity<TaskResponseModel> update(
            @PathVariable UUID resourceId,
            @Valid @RequestBody TaskRequestModel request) {
        var dto = taskMapper.toTaskRequestDto(request);
        var updated = taskFacade.updateTask(resourceId, dto);
        var response = taskMapper.toTaskResponseModel(updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{resourceId}")
    public ResponseEntity<Void> delete(@PathVariable UUID resourceId) {
        taskFacade.removeTask(resourceId);
        return ResponseEntity.noContent().build();
    }
}
