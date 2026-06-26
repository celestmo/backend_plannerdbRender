package com.ucr.planner_api.repositories;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param; 
import org.springframework.stereotype.Repository;

import com.ucr.planner_api.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    default List<Task> getAll() {
        return findAll();
    }

    default Task add(Task task) {
        return save(task);
    }

    default Task updateTask(Task task) {
        return save(task);
    }

    @Query("SELECT t FROM Task t WHERE t.resourceIdTask = :resourceId")
    Optional<Task> findByResourceId(@Param("resourceId") UUID resourceId);
    Optional<Task> findByResourceIdTask(UUID resourceIdTask);

    Boolean existsByTaskName(String taskName);

}
