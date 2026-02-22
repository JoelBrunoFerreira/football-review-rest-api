package com.footballreview.api.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
}
