package com.ucr.planner_api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucr.planner_api.Dtos.LoginRequestDto;
import com.ucr.planner_api.Dtos.UserRequestDto;
import com.ucr.planner_api.entities.User;
import com.ucr.planner_api.exceptions.DuplicateUserDataException;
import com.ucr.planner_api.exceptions.InvalidUserDataException;
import com.ucr.planner_api.exceptions.UserNotFoundException;
import com.ucr.planner_api.repositories.UserRepository;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    //Aquí CREATE
    @Override
    public User create(User user) {

        if(user.getUserId().length() < 6 || user.getUserId().length() > 6) {
            throw new InvalidUserDataException("El ID de usuario debe tener exactamente 6 caracteres");
        }

        if (userRepository.existsByUserName(user.getUserName())) {
            throw new DuplicateUserDataException("El nombre de usuario ya existe: " + user.getUserName());
        }
        return userRepository.add(user);
    }

    @Override
    public User getById(String userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con ID: " + userId));
    }

//Update ->

 @Override
public User updateUser(UUID resourceId_User, UserRequestDto userDto) {
    User user = userRepository.findByResourceIdUser(resourceId_User)
        .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

    user.setUserId(userDto.getUserId());
    user.setUserName(userDto.getUserName());
    user.setPassword(userDto.getPassword());
    user.setEmail(userDto.getEmail());
    user.setAvatarUrl(userDto.getAvatarUrl());

    return userRepository.updateUser(user);
}

 @Override
 public User updateUserByUserId(String userId, UserRequestDto userDto) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

    user.setUserName(userDto.getUserName());
    user.setPassword(userDto.getPassword());
    user.setEmail(userDto.getEmail());
    user.setAvatarUrl(userDto.getAvatarUrl());

    return userRepository.updateUser(user);
}

 @Override
    public void removeUser(UUID resourceId_User) {
       var user = userRepository.findByResourceIdUser(resourceId_User).orElseThrow(() -> new UserNotFoundException("Usuario no registrado"));

       userRepository.delete(user);
    }

@Override
public User findByUserId(String userId) {
    User user = userRepository.getById(userId);
    if (user == null) {
        throw new UserNotFoundException("Usuario no encontrado");
    }
    return user;
}
 @Override
    public User getByResourceId(UUID resourceId_User) {
        return userRepository.findByResourceIdUser(resourceId_User)
        .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con resourceId: " + resourceId_User));
    }

@Override
public User login(LoginRequestDto loginRequest) {
    User user = userRepository.getById(loginRequest.userId()); 
    if (user == null) {
        throw new UserNotFoundException("Usuario no encontrado");
    }
    return user; // 
}




}
 




