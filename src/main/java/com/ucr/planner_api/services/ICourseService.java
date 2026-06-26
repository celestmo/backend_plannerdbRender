package com.ucr.planner_api.services;

import java.util.List;
import java.util.UUID;

import com.ucr.planner_api.Dtos.CourseRequestDto;
import com.ucr.planner_api.entities.Course;

public interface ICourseService {
    
    List<Course> getAll();

    //aAquí CREATE
    Course create(Course course);

    Course getById(String courseCode);  

    //Update ->
    Course updateCourse(UUID resourceId_Course, CourseRequestDto courseDto);

        void removeCourse(UUID resourceId_Course);

        Course getByResourceId(UUID resourceId_Course);
}
