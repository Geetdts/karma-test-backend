package com.geeth.karma_test_backend.domain.service.impl;

import com.geeth.karma_test_backend.application.request.UserCreateRequest;
import com.geeth.karma_test_backend.application.request.UserUpdateRequest;
import com.geeth.karma_test_backend.domain.dto.UserDto;
import com.geeth.karma_test_backend.domain.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }

    @Override
    public UserDto getUserById(Long id) {
        return null;
    }

    @Override
    public UserDto create(UserCreateRequest userCreateRequest) {
        return null;
    }

    @Override
    public UserDto update(UserUpdateRequest userUpdateRequest) {
        return null;
    }
}
