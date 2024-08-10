package com.geeth.karma_test_backend.domain.service;

import com.geeth.karma_test_backend.application.request.UserCreateRequest;
import com.geeth.karma_test_backend.application.request.UserUpdateRequest;
import com.geeth.karma_test_backend.domain.dto.UserDto;

import java.util.List;

public interface IUserService {

    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    UserDto create(UserCreateRequest userCreateRequest);

    UserDto update(UserUpdateRequest userUpdateRequest);

    void deleteUser(Long id);

}
