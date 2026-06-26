package com.ucr.planner_api.Dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserRequestDto {
    private String userId;
    private String userName;
    private String password;
    private String email;
    private String avatarUrl;

}
