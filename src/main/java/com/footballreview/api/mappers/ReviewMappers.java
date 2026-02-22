package com.footballreview.api.mappers;

import com.footballreview.api.dtos.ReviewRequestDto;
import com.footballreview.api.dtos.ReviewResponseDto;
import com.footballreview.api.entities.Review;

public class ReviewMappers {

    // Map DB Entity --> to Domain Object
    public static ReviewResponseDto mapReviewToReviewResponseDto(Review review) {
        ReviewResponseDto reviewDto = new ReviewResponseDto();
        reviewDto.setReviewId(review.getReviewId());
        reviewDto.setAuthorName(review.getAuthorName());
        reviewDto.setContent(review.getContent());
        reviewDto.setStars(review.getStars());
        return reviewDto;
    }

    // Map Domain Object --> to DB Entity
    public static Review mapReviewRequestDtoToReview(ReviewRequestDto reviewDto) {
        Review review = new Review();
        review.setAuthorName(reviewDto.getAuthorName());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());
        return review;
    }
}
