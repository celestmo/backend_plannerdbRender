package com.ucr.planner_api.Facade;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ucr.planner_api.Dtos.CourseDto;
import com.ucr.planner_api.Dtos.CourseRequestDto;
import com.ucr.planner_api.mappers.CourseMapper;
import com.ucr.planner_api.services.ICourseService;

@Component
public class CourseFacade implements ICourseFacade{

    @Autowired
    private ICourseService courseService;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<CourseDto> getAll() {
        return courseMapper.toCourseDtosList(courseService.getAll());
    }

 //Create
    @Override
    public CourseDto create(CourseRequestDto dto) {
        var courseEntity = courseMapper.toEntity(dto);
        var saved = courseService.create(courseEntity);
        return courseMapper.toCourseDto(saved);
    }


 //Update ->
    @Override
    public CourseDto updateCourse(UUID resourceId_Course, CourseRequestDto courseDto) {
        var courseEntity = courseService.updateCourse(resourceId_Course, courseDto);
        return courseMapper.toCourseDto(courseEntity);
    }

    @Override
    public void removeCourse(UUID resourceId_Course) {
        courseService.removeCourse(resourceId_Course);
    }

    @Override
    public CourseDto getByResourceId(UUID resourceId_Course) {
        var courseEntity = courseService.getByResourceId(resourceId_Course);
        return courseMapper.toCourseDto(courseEntity);
    }

    
    
}
