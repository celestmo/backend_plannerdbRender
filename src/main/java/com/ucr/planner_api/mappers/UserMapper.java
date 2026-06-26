package com.ucr.planner_api.mappers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ucr.planner_api.Dtos.LoginResponseDto;
import com.ucr.planner_api.Dtos.UserDto;
import com.ucr.planner_api.Dtos.UserRequestDto;
import com.ucr.planner_api.entities.User;
import com.ucr.planner_api.models.LoginResponseModel;
import com.ucr.planner_api.models.UserRequestModel;
import com.ucr.planner_api.models.UserResponseModel;


@Component
public class UserMapper {

    public UserDto toUserDto(User user) {
    if (user == null) {
        return null;
    }

    return new UserDto(
        user.getResourceIdUser(),
        user.getUserId(),
        user.getUserName(),
        user.getPassword(),
        user.getEmail(),
        user.getAvatarUrl()
    );
}


    public List<UserDto> toUserDtosList(List<User> users){

        if (users == null) {
            return null;
        }

        return users.stream()
                .map(this::toUserDto)
                .collect(Collectors.toList());
    }

    public UserResponseModel toUserResponseModel(UserDto userDto){
        if (userDto == null) {
            return null;
        }

        return new UserResponseModel(
            userDto.userId(),
            userDto.userName(),
            userDto.password(),
            userDto.email(),
            userDto.avatarUrl(),
            userDto.resourceId_User() != null ? userDto.resourceId_User().toString() : null
        );

    }

    public List<UserResponseModel> toUserResponseModelList(List<UserDto> userDtos){
        if (userDtos == null) {
            return null;
        }

        return userDtos.stream()
                .map(this::toUserResponseModel)
                .collect(Collectors.toList());
    }

    // CREATE TOUSERENTITY
    public User toEntity(UserRequestDto dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setUserId(dto.getUserId());
        user.setUserName(dto.getUserName());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setAvatarUrl(dto.getAvatarUrl());
        user.setResourceIdUser(UUID.randomUUID());

        return user;
    }

    //UPDATE->
   public UserRequestDto toUserRequestDto(UserRequestModel user) {
    if (user == null) {
        return null;
    }

    UserRequestDto userDto = new UserRequestDto();
    userDto.setUserId(user.userId());     
    userDto.setUserName(user.userName());     
    userDto.setPassword(user.password());
    userDto.setEmail(user.email());
    userDto.setAvatarUrl(user.avatarUrl());

    return userDto;
}

public LoginResponseModel toLoginResponseModel(LoginResponseDto dto) {
    if (dto == null) {
        return null;
    }
    return new LoginResponseModel(
        dto.resourceId_User(),
        dto.userId(),
        dto.userName(),
        dto.email()
    );
}



}
