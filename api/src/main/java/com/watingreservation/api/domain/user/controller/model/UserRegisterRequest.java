package com.watingreservation.api.domain.user.controller.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
    @NotBlank
    public String id;
    @NotBlank
    public String password;
    @NotBlank
    public String name;
    @NotBlank
    public String phone_number;
}
