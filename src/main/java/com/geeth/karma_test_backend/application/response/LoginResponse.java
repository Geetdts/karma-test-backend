package com.geeth.karma_test_backend.application.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@AllArgsConstructor
@Data
public class LoginResponse {
    String message;
    Boolean status;
}
