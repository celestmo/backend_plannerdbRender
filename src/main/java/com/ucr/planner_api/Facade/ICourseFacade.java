package com.ucr.planner_api.Facade;

import java.util.List;
import java.util.UUID;

import com.ucr.planner_api.Dtos.CourseDto;
import com.ucr.planner_api.Dtos.CourseRequestDto;

public interface ICourseFacade {
    
    List<CourseDto> getAll();

    //Aquí CREATE
    CourseDto create(CourseRequestDto dto);

    //Update ->

     CourseDto updateCourse(UUID resourceId_Course, CourseRequestDto courseDto);

    //  Delete

    void removeCourse(UUID resourceId_Course);

    CourseDto getByResourceId(UUID resourceId_Course);
}
