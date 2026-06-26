package com.ucr.planner_api.config;

import java.util.List;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ucr.planner_api.entities.Course;
import com.ucr.planner_api.repositories.CourseRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CourseRepository courseRepository;

    public DataInitializer(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        List<Course> courses = List.of(
            Course.builder().courseCode("IF-0001").courseName("DESARROLLO DE SOFTWARE").resourceIdCourse(UUID.randomUUID()).build(),
            Course.builder().courseCode("IF-0002").courseName("INTRODUCCIÓN A LA INFORMÁTICA EMPRESARIAL").resourceIdCourse(UUID.randomUUID()).build(),
            Course.builder().courseCode("IF-0003").courseName("MATEMÁTICA BÁSICA PARA INFORMÁTICA EMPRESARIAL").resourceIdCourse(UUID.randomUUID()).build(),
            Course.builder().courseCode("IF-0004").courseName("DESARROLLO DE SOFTWARE II").resourceIdCourse(UUID.randomUUID()).build(),
            Course.builder().courseCode("IF-0005").courseName("MATEMÁTICAS DISCRETAS PARA INFORMÁTICA EMPRESARIAL").resourceIdCourse(UUID.randomUUID()).build(),
            Course.builder().courseCode("IF-0006").courseName("DESARROLLO DE SOFTWARE III").resourceIdCourse(UUID.randomUUID()).build(),
            Course.builder().courseCode("IF-0007").courseName("BASES DE DATOS I").resourceIdCourse(UUID.randomUUID()).build(),
            Course.builder().courseCode("IF-0008").courseName("CÁLCULO I PARA INFORMÁTICA EMPRESARIAL").resourceIdCourse(UUID.randomUUID()).build(),
            Course.builder().courseCode("IF-3001").courseName("ALGORITMOS Y ESTRUCTURAS DE DATOS").resourceIdCourse(UUID.randomUUID()).build(),
            Course.builder().courseCode("IF-0009").courseName("DESARROLLO DE SOFTWARE IV").resourceIdCourse(UUID.randomUUID()).build(),
            Course.builder().courseCode("IF-0010").courseName("BASES DE DATOS II").resourceIdCourse(UUID.randomUUID()).build(),
            Course.builder().courseCode("IF-0011").courseName("REDES DE COMPUTADORAS").resourceIdCourse(UUID.randomUUID()).build(),
            Course.builder().courseCode("IF-0012").courseName("ÁLGEBRA LINEAL PARA INFORMÁTICA EMPRESARIAL").resourceIdCourse(UUID.randomUUID()).build(),
            Course.builder().courseCode("IF-0015").courseName("INTRODUCCIÓN A LA ADMINISTRACIÓN DE NEGOCIOS").resourceIdCourse(UUID.randomUUID()).build(),
            Course.builder().courseCode("IF-0016").courseName("INTRODUCCIÓN A LA ESTADÍSTICA Y ANÁLISIS DE DATOS").resourceIdCourse(UUID.randomUUID()).build(),
            Course.builder().courseCode("IF-0017").courseName("MÉTODOS NUMÉRICOS Y ANÁLISIS COMPUTACIONAL").resourceIdCourse(UUID.randomUUID()).build(),
            Course.builder().courseCode("IF-0019").courseName("SEGURIDAD EN SISTEMAS INFORMÁTICOS").resourceIdCourse(UUID.randomUUID()).build(),
            Course.builder().courseCode("IF-7201").courseName("GESTIÓN DE PROYECTOS").resourceIdCourse(UUID.randomUUID()).build()
        );

        for (Course c : courses) {
            if (!courseRepository.existsByCourseCode(c.getCourseCode())) {
                courseRepository.save(c);
            }
        }
    }
}
