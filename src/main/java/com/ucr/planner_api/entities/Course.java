package com.ucr.planner_api.entities;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Course")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Course {

    @Column(name = "Course_Name", nullable = false, length = 100)
    private String courseName;

    @Id
    @Column(name = "Course_Code", nullable = false, length = 10)
    private String courseCode;

    @Column(name = "resource_id_Course", nullable = false, unique = true, length = 36)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID resourceIdCourse;

}
