package com.ucr.planner_api.repositories;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; 
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ucr.planner_api.entities.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

    default List<Course> getAll() {
        return findAll();
    }

    default Course add(Course course) {
        return save(course);
    }

    default Course updateCourse(Course course) {
        return save(course);
    }

    default Course getByCourseCode(String courseCode) {
        return findByCourseCode(courseCode)
            .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado: " + courseCode));
    }

    Optional<Course> findByResourceIdCourse(UUID resourceIdCourse);

    Optional<Course> findByCourseCode(String courseCode);

    boolean existsByCourseCode(String courseCode);

    @Query("SELECT c FROM Course c WHERE c.resourceIdCourse = :resourceId")
    Optional<Course> findByResourceId(@Param("resourceId") UUID resourceId);
}
