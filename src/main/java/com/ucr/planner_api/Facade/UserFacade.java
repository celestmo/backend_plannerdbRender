package com.ucr.planner_api.Facade;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ucr.planner_api.Dtos.LoginRequestDto;
import com.ucr.planner_api.Dtos.UserDto;
import com.ucr.planner_api.Dtos.UserRequestDto;
import com.ucr.planner_api.exceptions.InvalidUserDataException;
import com.ucr.planner_api.mappers.UserMapper;
import com.ucr.planner_api.services.IUserService;

@Component
public class UserFacade implements IUserFacade{

    @Autowired
    private IUserService userService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDto> getAll() {
        return userMapper.toUserDtosList(userService.getAll());
    }

    //Aquí CREATE
    @Override
    public UserDto create(UserRequestDto dto) {
        var userEntity = userMapper.toEntity(dto);
        var saved = userService.create(userEntity);
        return userMapper.toUserDto(saved);
    }


    //Update ->
    @Override
    public UserDto updateUser(UUID resourceId_User, UserRequestDto userDto) {
       
        var userEntity = userService.updateUser(resourceId_User, userDto);
        return userMapper.toUserDto(userEntity);
    }

    @Override
    public UserDto updateUserByUserId(String userId, UserRequestDto userDto) {
        var userEntity = userService.updateUserByUserId(userId, userDto);
        return userMapper.toUserDto(userEntity);
    }

    @Override
    public UserDto findByUserId(String userId) {
        var userEntity = userService.findByUserId(userId);
        return userMapper.toUserDto(userEntity);
    }

    @Override
    public void removeUser(UUID resourceId_User) {
        userService.removeUser(resourceId_User);
    }

   @Override
public UserDto login(LoginRequestDto loginRequest) {
    var userEntity = userService.login(loginRequest);

    if (!userEntity.getPassword().equals(loginRequest.password())) {
        throw new InvalidUserDataException("Credenciales inválidas");
    }

    return userMapper.toUserDto(userEntity);
}

 @Override
    public UserDto getByResourceId(UUID resourceId_User) {
        var userEntity = userService.getByResourceId(resourceId_User);
        return userMapper.toUserDto(userEntity);
    }
    

}