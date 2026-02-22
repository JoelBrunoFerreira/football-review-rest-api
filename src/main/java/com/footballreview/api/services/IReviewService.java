package com.footballreview.api.services;

import com.footballreview.api.dtos.ReviewRequestDto;
import com.footballreview.api.dtos.ReviewResponseDto;

import java.util.List;

public interface IReviewService {

    // GET all
    List<ReviewResponseDto> getAllReviewsByPlayerId(Integer playerId);

    // GET by id
    ReviewResponseDto getReviewById(Integer reviewId, Integer playerId);

    // POST
    ReviewResponseDto createReview(Integer playerId, ReviewRequestDto reviewDto);

    // PUT
    ReviewResponseDto updateReview(Integer playerId, Integer reviewId, ReviewRequestDto reviewDto);

    // DELETE
    void deleteReviewById(Integer playerId, Integer reviewId);
}
