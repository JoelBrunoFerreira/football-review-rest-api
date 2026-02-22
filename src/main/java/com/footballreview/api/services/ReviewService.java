package com.footballreview.api.services;

import com.footballreview.api.dtos.ReviewRequestDto;
import com.footballreview.api.dtos.ReviewResponseDto;
import com.footballreview.api.entities.Player;
import com.footballreview.api.entities.Review;
import com.footballreview.api.exceptions.PlayerNotFoundException;
import com.footballreview.api.exceptions.ReviewNotFoundException;
import com.footballreview.api.mappers.ReviewMappers;
import com.footballreview.api.repositories.PlayerRepository;
import com.footballreview.api.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService implements IReviewService {

    private final ReviewRepository reviewRepository;
    private final PlayerRepository playerRepository;

    public ReviewService(ReviewRepository reviewRepository, PlayerRepository playerRepository) {
        this.reviewRepository = reviewRepository;
        this.playerRepository = playerRepository;
    }

    // GET all
    @Override
    public List<ReviewResponseDto> getAllReviewsByPlayerId(Integer playerId) {
        List<Review> reviews = reviewRepository.findByPlayer_Id(playerId);
        return reviews.stream().map(ReviewMappers::mapReviewToReviewResponseDto)
                .collect(Collectors.toList());
    }

    // GET by id
    @Override
    public ReviewResponseDto getReviewById(Integer reviewId, Integer playerId) {
        Player player = playerRepository.findById(playerId).orElseThrow(()
                -> new PlayerNotFoundException("Player with the associated review not found"));
        Review review = reviewRepository.findById(reviewId).orElseThrow(()
                -> new ReviewNotFoundException("Review with id " + reviewId + " not found"));

        if(!review.getPlayer().getId().equals(player.getId())) {
            throw new ReviewNotFoundException("The review with id " + reviewId + " does not belong to this player");
        }
        return ReviewMappers.mapReviewToReviewResponseDto(review);
    }

    // POST -> Create e new review
    @Override
    public ReviewResponseDto createReview(Integer playerId, ReviewRequestDto reviewDto) {
        Review review = ReviewMappers.mapReviewRequestDtoToReview(reviewDto);
        Player player = playerRepository.findById(playerId).orElseThrow(()
                -> new PlayerNotFoundException("Player with the associated review not found"));
        review.setPlayer(player);
        Review newReview = reviewRepository.save(review);
        return ReviewMappers.mapReviewToReviewResponseDto(newReview);
    }

    // PUT -> Update a review
    @Override
    public ReviewResponseDto updateReview(Integer playerId, Integer reviewId, ReviewRequestDto reviewDto) {
        Player player = playerRepository.findById(playerId).orElseThrow(()
                -> new PlayerNotFoundException("Player with the associated review not found"));
        Review review = reviewRepository.findById(reviewId).orElseThrow(()
                -> new ReviewNotFoundException("Review with id " + reviewId + " not found"));

        if(!review.getPlayer().getId().equals(player.getId())) {
            throw new ReviewNotFoundException("The review with id " + reviewId + " does not belong to this player");
        }

        review.setAuthorName(reviewDto.getAuthorName());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());
        Review updatedReview = reviewRepository.save(review);
        return ReviewMappers.mapReviewToReviewResponseDto(updatedReview);
    }

    // DELETE
    @Override
    public void deleteReviewById(Integer playerId, Integer reviewId) {
        Player player = playerRepository.findById(playerId).orElseThrow(()
                -> new PlayerNotFoundException("Player with the associated review not found"));
        Review review = reviewRepository.findById(reviewId).orElseThrow(()
                -> new ReviewNotFoundException("Review with id " + reviewId + " not found"));

        if(!review.getPlayer().getId().equals(player.getId())) {
            throw new ReviewNotFoundException("The review with id " + reviewId + " does not belong to this player");
        }
        reviewRepository.delete(review);
    }
}
