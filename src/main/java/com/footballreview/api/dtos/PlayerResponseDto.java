package com.footballreview.api.dtos;

import lombok.Data;

@Data
public class PlayerResponseDto {
    private Integer id;
    private String name;
    private String position;
    private String club;
}
