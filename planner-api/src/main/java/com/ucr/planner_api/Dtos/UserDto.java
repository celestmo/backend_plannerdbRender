package com.ucr.planner_api.Dtos;

import java.util.UUID;

public record UserDto(
     UUID resourceId_User,
     String userId,
     String userName,
     String password,
     String email,
     String avatarUrl) {
    
}
