package com.geeth.karma_test_backend.application.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserLoginRequest {

    @Email
    @NotNull
    private String email;
    @NotNull
    private String password;
}
