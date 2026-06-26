package com.ucr.planner_api.controllers;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ucr.planner_api.Facade.ICourseFacade;
import com.ucr.planner_api.mappers.CourseMapper;
import com.ucr.planner_api.models.CourseRequestModel;
import com.ucr.planner_api.models.CourseResponseModel;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private ICourseFacade courseFacade;
    @Autowired
    private CourseMapper courseMapper;

    @GetMapping
    public ResponseEntity<List<CourseResponseModel>> getAll() {
        var courses = courseFacade.getAll();
        var response = courseMapper.toCourseResponseModelList(courses);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{resourceId}")
    public ResponseEntity<CourseResponseModel> getByResourceId(@PathVariable UUID resourceId) {
        var courseDto = courseFacade.getByResourceId(resourceId);
        var response = courseMapper.toCourseResponseModel(courseDto);
        return ResponseEntity.ok(response);
    }

    
    @PostMapping
    public ResponseEntity<CourseResponseModel> create(
            @Valid @RequestBody CourseRequestModel request) {
        var dto = courseMapper.toCourseRequestDto(request);
        var created = courseFacade.create(dto);
        var response = courseMapper.toCourseResponseModel(created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{resourceId}")
    public ResponseEntity<CourseResponseModel> update(
            @PathVariable UUID resourceId,
            @Valid @RequestBody CourseRequestModel request) {
        var dto = courseMapper.toCourseRequestDto(request);
        var updated = courseFacade.updateCourse(resourceId, dto);
        var response = courseMapper.toCourseResponseModel(updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{resourceId}")
    public ResponseEntity<Void> delete(@PathVariable UUID resourceId) {
        courseFacade.removeCourse(resourceId);
        return ResponseEntity.noContent().build();
    }
}
