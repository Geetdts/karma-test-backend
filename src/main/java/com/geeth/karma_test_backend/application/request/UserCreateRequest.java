package com.geeth.karma_test_backend.application.request;

import lombok.Data;

import java.util.Date;

@Data
public class UserCreateRequest {

    private String email;
    private String name;
    private String role;
    private String password;

}
