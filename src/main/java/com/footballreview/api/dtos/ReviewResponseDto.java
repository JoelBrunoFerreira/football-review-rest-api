package com.footballreview.api.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ReviewResponseDto {
    private Integer reviewId;
    private String authorName;
    private String content;
    @Min(1)
    @Max(5)
    private int stars;
}
