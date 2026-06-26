package com.ucr.planner_api.services;

import java.util.List;
import java.util.UUID;

import com.ucr.planner_api.Dtos.LoginRequestDto;
import com.ucr.planner_api.Dtos.UserRequestDto;
import com.ucr.planner_api.entities.User;

public interface IUserService {

    List<User> getAll(); 

    //Aquí CREATE
    User create(User user);

    User getById(String userId);

    //Update ->
    User updateUser(UUID resourceId_User, UserRequestDto userDto);

    User updateUserByUserId(String userId, UserRequestDto userDto);

        void removeUser(UUID resourceId_User);

        User findByUserId(String userId);

 User getByResourceId(UUID resourceId);
 
    User login(LoginRequestDto loginRequest);
}