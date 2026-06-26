package com.ucr.planner_api.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Task")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Task_ID", nullable = false)
    private Integer taskId;

    @Column(name = "Task_Name", nullable = false, length = 50)
    private String taskName;

    @Column(name = "Created_Date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "Due_Date", nullable = false)
    private LocalDateTime dueDate;

    @Column(name = "Done", nullable = false)
    private Boolean done;

    @Column(name = "Details", length = 100)
    private String details;

    @Column(name = "State", nullable = false, length = 25)
    private String state;

    @Column(name = "Priority", nullable = false, length = 100)
    private String priority;

    @ManyToOne
    @JoinColumn(name = "User_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "Course_Code", nullable = false)
    private Course course;

    @Column(name = "resource_id_Task", nullable = false, unique = true, length = 36)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID resourceIdTask;
}
