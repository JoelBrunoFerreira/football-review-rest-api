package com.footballreview.api.dtos;

import lombok.Data;

@Data
public class PlayerRequestDto {
    private String name;
    private String position;
    private String club;
}
