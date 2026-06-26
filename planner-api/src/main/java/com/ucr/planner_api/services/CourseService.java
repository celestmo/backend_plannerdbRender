package com.ucr.planner_api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucr.planner_api.Dtos.CourseRequestDto;
import com.ucr.planner_api.entities.Course;
import com.ucr.planner_api.exceptions.CourseNotFoundException;
import com.ucr.planner_api.exceptions.DuplicateCourseDataException;
import com.ucr.planner_api.exceptions.InvalidCourseDataException;
import com.ucr.planner_api.repositories.CourseRepository;

@Service
public class CourseService implements ICourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getAll() {
        return courseRepository.getAll();
    }

    @Override
    public Course create(Course course) {
        if (course.getCourseCode() == null || !course.getCourseCode().matches("^[A-Z]{2}-\\d{4}$")) {
        throw new InvalidCourseDataException("El código debe tener el formato XX-0000 (ej: IF-0007)");
    }

        if (courseRepository.existsByCourseCode(course.getCourseCode())) {
            throw new DuplicateCourseDataException("El código del curso ya existe: " + course.getCourseCode());
        }
        return courseRepository.add(course);
    }

    @Override
    public Course getById(String courseCode) {
        return courseRepository.findById(courseCode)
            .orElseThrow(() -> new CourseNotFoundException("Curso no encontrado con código: " + courseCode));
    }

    @Override
    public Course updateCourse(UUID resourceId_Course, CourseRequestDto courseDto) {
        var course = courseRepository.findByResourceIdCourse (resourceId_Course)
        .orElseThrow(()-> new CourseNotFoundException("Curso no encontrado"));
        
        course.setCourseName(courseDto.getCourseName());
        course.setCourseCode(courseDto.getCourseCode());

        return courseRepository.updateCourse(course);
    }

    @Override
    public void removeCourse(UUID resourceId_Course) {
        var course = courseRepository.findByResourceIdCourse (resourceId_Course).orElseThrow(() -> new CourseNotFoundException("Curso no encontrado"));
        courseRepository.delete(course);
    }

    @Override
    public Course getByResourceId(UUID resourceId_Course) {
        return courseRepository.findByResourceIdCourse(resourceId_Course)
        .orElseThrow(() -> new CourseNotFoundException("Curso no encontrado con resourceId: " + resourceId_Course));
    }
}