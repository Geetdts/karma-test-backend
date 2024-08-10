package com.geeth.karma_test_backend.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

    private Long id;
    private String email;
    private String name;
    private String password;
    private Date CreatedAt;

}
