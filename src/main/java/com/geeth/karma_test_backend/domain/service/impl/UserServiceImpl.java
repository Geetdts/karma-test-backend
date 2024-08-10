package com.geeth.karma_test_backend.domain.service.impl;

import com.geeth.karma_test_backend.application.exception.AlreadyExistsException;
import com.geeth.karma_test_backend.application.request.UserCreateRequest;
import com.geeth.karma_test_backend.application.request.UserLoginRequest;
import com.geeth.karma_test_backend.application.response.LoginResponse;
import com.geeth.karma_test_backend.domain.dto.UserDto;
import com.geeth.karma_test_backend.domain.dto.convertor.UserMapper;
import com.geeth.karma_test_backend.domain.service.IUserService;
import com.geeth.karma_test_backend.external.entity.User;
import com.geeth.karma_test_backend.external.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service implementation class responsible for managing User-related operations.
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Create a new User.
     *
     * @param userCreateRequest - Contains the details of the user to be created.
     * @return - A UserDto representing the created user.
     * @throws AlreadyExistsException - If a user with the same email already exists.
     */
    @Override
    public UserDto create(UserCreateRequest userCreateRequest) {
        if (!userRepository.existsByEmail(userCreateRequest.getEmail())) {
            User user = userMapper.convertToEntity(userCreateRequest);
            User savedUser = userRepository.save(user);
            return userMapper.convertToDto(savedUser);
        } else {
            throw new AlreadyExistsException("User already exist!");
        }

    }

    /**
     * Authenticate a user based on email and password.
     *
     * @param userLoginRequest - Contains the user's login credentials.
     * @return - A LoginResponse indicating success or failure.
     */
    @Override
    public LoginResponse login(UserLoginRequest userLoginRequest) {
        User user = userRepository.findByEmail(userLoginRequest.getEmail());
        if (user != null) {
            String password = userLoginRequest.getPassword();
            String encodedPassword = user.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> userWithPassword = userRepository.findByEmailAndPassword(password, encodedPassword);
            if(userWithPassword.isPresent()){
                return new LoginResponse("Login Success!", true);
            }else{
                return new LoginResponse("Login Failed!", false);
            }
            } else {
                return new LoginResponse("Incorrect password!", false);
            }
        } else {
            return new LoginResponse("Email not exits!", false);
        }
    }
}
