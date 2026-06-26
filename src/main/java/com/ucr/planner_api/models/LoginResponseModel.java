package com.ucr.planner_api.models;

import java.util.UUID;

public record LoginResponseModel(
    UUID resourceId_User,
    String userId,
    String userName,
    String email
) {}
