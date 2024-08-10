package com.geeth.karma_test_backend.domain.service;

import com.geeth.karma_test_backend.application.request.UserCreateRequest;
import com.geeth.karma_test_backend.application.request.UserLoginRequest;
import com.geeth.karma_test_backend.application.response.LoginResponse;
import com.geeth.karma_test_backend.domain.dto.UserDto;

import java.util.List;

public interface IUserService {

    UserDto create(UserCreateRequest userCreateRequest);

    LoginResponse login(UserLoginRequest userLoginRequest);
}
