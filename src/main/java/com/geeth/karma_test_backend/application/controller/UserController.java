package com.geeth.karma_test_backend.application.controller;

import com.geeth.karma_test_backend.application.request.UserCreateRequest;
import com.geeth.karma_test_backend.application.request.UserUpdateRequest;
import com.geeth.karma_test_backend.application.response.ListResponse;
import com.geeth.karma_test_backend.application.response.ObjectResponse;
import com.geeth.karma_test_backend.application.util.SystemMessage;
import com.geeth.karma_test_backend.domain.dto.UserDto;
import com.geeth.karma_test_backend.domain.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Tag(name = "User")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @Operation(summary = "Get all active users (not deleted)")
    @GetMapping
    public ResponseEntity<ListResponse> getAllUsers() {
        List<UserDto> userDtoList = iUserService.getAllUsers();
        return new ResponseEntity<>(new ListResponse<>(userDtoList, HttpStatus.OK.value(), SystemMessage.SUCCESS),
                HttpStatus.OK);
    }

    @Operation(summary = "Get a specific user by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ObjectResponse> getUserById(@PathVariable Long id) {
        UserDto userDto = iUserService.getUserById(id);
        return new ResponseEntity<>(new ObjectResponse<>(userDto, HttpStatus.OK.value(), SystemMessage.SUCCESS),
                HttpStatus.OK);
    }

    @Operation(summary = "Create new User")
    @PostMapping
    public ResponseEntity<ObjectResponse> createUser(@RequestBody UserCreateRequest userCreateRequest) {
        UserDto userDto = iUserService.create(userCreateRequest);
        return new ResponseEntity<>(new ObjectResponse<>(userDto, HttpStatus.CREATED.value(), SystemMessage.CREATE),
                HttpStatus.CREATED);
    }

    @Operation(summary = "Update existing User")
    @PutMapping()
    public ResponseEntity<ObjectResponse> updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
        UserDto updatedUserDto = iUserService.update(userUpdateRequest);
        return new ResponseEntity<>(new ObjectResponse<>(updatedUserDto, HttpStatus.OK.value(), SystemMessage.UPDATE),
                HttpStatus.OK);
    }

    @Operation(summary = "Delete a User")
    @DeleteMapping("/{id}")
    public ResponseEntity<ObjectResponse> deleteUser(@PathVariable Long id) {
        iUserService.deleteUser(id);
        return new ResponseEntity<>(new ObjectResponse<>(null, HttpStatus.OK.value(), SystemMessage.DELETE),
                HttpStatus.OK);
    }
}
