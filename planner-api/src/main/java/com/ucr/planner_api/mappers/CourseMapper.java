package com.ucr.planner_api.mappers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ucr.planner_api.Dtos.CourseDto;
import com.ucr.planner_api.Dtos.CourseRequestDto;
import com.ucr.planner_api.entities.Course;
import com.ucr.planner_api.models.CourseRequestModel;
import com.ucr.planner_api.models.CourseResponseModel;

@Component
public class CourseMapper {

    public CourseDto toCourseDto(Course course){
        if (course == null) {
            return null;
        }

        return new CourseDto(
            course.getResourceIdCourse(), 
            course.getCourseName(), 
            course.getCourseCode()
        );
    }

    public List<CourseDto> toCourseDtosList(List<Course> courses){

        if (courses == null) {
            return null;
        }

        return courses.stream()
                .map(this::toCourseDto)
                .collect(Collectors.toList());
    }

    public CourseResponseModel toCourseResponseModel(CourseDto courseDto){
        if (courseDto == null) {
            return null;
        }

        return new CourseResponseModel(courseDto.courseName(), courseDto.courseCode(), courseDto.resourceId_Course());

    }

    public List<CourseResponseModel> toCourseResponseModelList(List<CourseDto> courseDtos){
        if (courseDtos == null) {
            return null;
        }

        return courseDtos.stream()
                .map(this::toCourseResponseModel)
                .collect(Collectors.toList());
    }

   //CREATE TOCOURSEENTITY
    public Course toEntity(CourseRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Course course = new Course();
        course.setCourseName(dto.getCourseName());
        course.setCourseCode(dto.getCourseCode());
        course.setResourceIdCourse(UUID.randomUUID());

        return course;
    }

   //UPDATE->
    public CourseRequestDto toCourseRequestDto(CourseRequestModel course){
        if (course == null) {
            return null;
        }

        CourseRequestDto courseDto = new CourseRequestDto();
        courseDto.setCourseName(course.courseName());
        courseDto.setCourseCode(course.courseCode());

        return courseDto;
    }
    
}
