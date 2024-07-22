package com.cs348.backendservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Request object for account creation")
public class CreateAccountRequest {
    @Schema(description = "username", example = "username1")
    private String username;

    @Schema(description = "password", example = "password1")
    private String password;

    @Schema(description = "email", example = "123@gmail.com")
    private String email;

    @Schema(description = "phone", example = "1234567890")
    private String phone;

    @Schema(description = "identity", example = "student")
    private String identity;

    @Schema(description = "is accessible", example = "false")
    private boolean is_accessible;
}
