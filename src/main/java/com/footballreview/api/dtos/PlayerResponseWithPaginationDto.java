package com.footballreview.api.dtos;

import lombok.Data;
import java.util.List;

@Data
public class PlayerResponseWithPaginationDto {
    private List<PlayerResponseDto> content;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private long totalElements;
    private boolean last;
}
