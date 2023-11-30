package com.restapi.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class LoginRequest {

    @NotEmpty
    @Size(min = 2, message = "Username min length 2")
    private String username;

    @NotEmpty
    @Size(min = 2, message = "Password min length 2")
    private String password;
}
