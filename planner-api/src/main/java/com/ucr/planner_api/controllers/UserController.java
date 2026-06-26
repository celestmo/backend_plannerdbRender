package com.ucr.planner_api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ucr.planner_api.Dtos.LoginRequestDto;
import com.ucr.planner_api.Dtos.UserDto;
import com.ucr.planner_api.Facade.IUserFacade;
import com.ucr.planner_api.mappers.UserMapper;
import com.ucr.planner_api.models.UserRequestModel;
import com.ucr.planner_api.models.UserResponseModel;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserFacade userFacade;

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserResponseModel>> getAll() {
        var users = userFacade.getAll();
        var response = userMapper.toUserResponseModelList(users);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{resourceId}")
    public ResponseEntity<UserResponseModel> getByResourceId(@PathVariable UUID resourceId) {
        var userDto = userFacade.getByResourceId(resourceId);
        var response = userMapper.toUserResponseModel(userDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UserResponseModel> create(@Valid @RequestBody UserRequestModel request) {
        var dto = userMapper.toUserRequestDto(request);
        var created = userFacade.create(dto);
        var response = userMapper.toUserResponseModel(created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{resourceId}")
    public ResponseEntity<UserResponseModel> update(
            @PathVariable UUID resourceId,
            @Valid @RequestBody UserRequestModel request) {
        var dto = userMapper.toUserRequestDto(request);
        var updated = userFacade.updateUser(resourceId, dto);
        var response = userMapper.toUserResponseModel(updated);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/by-userid/{userId}")
    public ResponseEntity<UserResponseModel> updateByUserId(
            @PathVariable String userId,
            @Valid @RequestBody UserRequestModel request) {
        var dto = userMapper.toUserRequestDto(request);
        var updated = userFacade.updateUserByUserId(userId, dto);
        var response = userMapper.toUserResponseModel(updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{resourceId}")
    public ResponseEntity<Void> delete(@PathVariable UUID resourceId) {
        userFacade.removeUser(resourceId);
        return ResponseEntity.noContent().build();
    }

       @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequest) {
        var userDto = userFacade.login(loginRequest);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/by-userid/{userId}")
    public ResponseEntity<UserResponseModel> getByUserId(@PathVariable String userId) {
        var userDto = userFacade.findByUserId(userId);
        var response = userMapper.toUserResponseModel(userDto);
        return ResponseEntity.ok(response);
    }
}

