package com.ucr.planner_api.models;

public record UserResponseModel (
     String userId,
     String userName,
     String password,
     String email,
     String avatarUrl,
     String resourceIdUser) {

    }
    