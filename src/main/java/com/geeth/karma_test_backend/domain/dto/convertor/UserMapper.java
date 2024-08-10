package com.geeth.karma_test_backend.domain.dto.convertor;

import com.geeth.karma_test_backend.application.request.UserCreateRequest;
import com.geeth.karma_test_backend.domain.dto.UserDto;
import com.geeth.karma_test_backend.external.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserMapper {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Converts a UserCreateRequest object to a User entity.
     *
     * @param userCreateRequest the UserCreateRequest object containing user details.
     * @return the User entity created from the request object, with an encoded password and creation date set.
     */
    public User convertToEntity(UserCreateRequest userCreateRequest) {
        User user = modelMapper.map(userCreateRequest, User.class);
        user.setPassword(passwordEncoder.encode(userCreateRequest.getPassword()));
        user.setCreatedAt(new Date());
        return user;
    }

    /**
     * Converts a User entity to a UserDto.
     *
     * @param user the User entity to convert.
     * @return the UserDto object created from the User entity.
     */
    public UserDto convertToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }
}
