package com.geeth.karma_test_backend.application.controller;

import com.geeth.karma_test_backend.application.request.UserCreateRequest;
import com.geeth.karma_test_backend.application.request.UserLoginRequest;
import com.geeth.karma_test_backend.application.response.LoginResponse;
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

/**
 * Controller class responsible for managing User-related operations.
 */
@CrossOrigin
@Tag(name = "User", description = "User APIs.")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     * Create a new User / Register user.
     *
     * @param userCreateRequest - Contains the details of the user to be created.
     * @return - A ResponseEntity containing the created user's details.
     */
    @Operation(summary = "Create new User / Register user")
    @PostMapping
    public ResponseEntity<ObjectResponse> createUser(@RequestBody UserCreateRequest userCreateRequest) {
        UserDto userDto = iUserService.create(userCreateRequest);
        return new ResponseEntity<>(new ObjectResponse<>(userDto, HttpStatus.CREATED.value(), SystemMessage.CREATE),
                HttpStatus.CREATED);
    }

    /**
     * Login User.
     *
     * @param userLoginRequest - Contains the user's login credentials.
     * @return - A ResponseEntity containing the login response details with a success status.
     */
    @Operation(summary = "Login User")
    @PostMapping("/login")
    public ResponseEntity<ObjectResponse> loginUser(@RequestBody UserLoginRequest userLoginRequest) {
        LoginResponse loginResponse = iUserService.login(userLoginRequest);
        return new ResponseEntity<>(new ObjectResponse<>(loginResponse, HttpStatus.OK.value(), SystemMessage.SUCCESS),
                HttpStatus.OK);
    }


}
