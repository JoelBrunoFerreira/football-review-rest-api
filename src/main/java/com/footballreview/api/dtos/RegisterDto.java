package com.footballreview.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDto {
    @NotBlank
    @Size(min = 3, max = 20)
    private String userName;

    @NotBlank
    @Size(min = 6)
    private String password;
}
