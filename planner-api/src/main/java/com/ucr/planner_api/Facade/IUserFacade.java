package com.ucr.planner_api.Facade;

import java.util.List;
import java.util.UUID;

import com.ucr.planner_api.Dtos.LoginRequestDto;
import com.ucr.planner_api.Dtos.UserDto;
import com.ucr.planner_api.Dtos.UserRequestDto;

public interface IUserFacade {
    
    List<UserDto> getAll();

    //Aquí CREATE
    UserDto create(UserRequestDto dto);

    //Update ->
    UserDto updateUser(UUID resourceId_User, UserRequestDto userDto);

    UserDto updateUserByUserId(String userId, UserRequestDto userDto);

    UserDto findByUserId(String userId);

//Delete

    void removeUser(UUID resourceId_User);



    UserDto login(LoginRequestDto loginRequest);

       UserDto getByResourceId(UUID resourceId_User);

}
