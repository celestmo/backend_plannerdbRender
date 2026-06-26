package com.ucr.planner_api.models;

import java.util.UUID;

public record CourseResponseModel (

     String courseName,
     String courseCode,
     UUID resourceId){
    }