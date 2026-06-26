package com.ucr.planner_api.Dtos;

import java.util.UUID;

public record LoginResponseDto(
    UUID resourceId_User,
    String userId,
    String userName,
    String email
) {
    
}
