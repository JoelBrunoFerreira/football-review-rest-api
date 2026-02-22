package com.footballreview.api.controllers;

import com.footballreview.api.dtos.ReviewRequestDto;
import com.footballreview.api.dtos.ReviewResponseDto;
import com.footballreview.api.services.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // GET all reviews request
    @GetMapping("/players/{playerId}/reviews")
    public ResponseEntity<List<ReviewResponseDto>> getAllReviewsByPlayerId(@PathVariable("playerId") Integer playerId) {
        return new ResponseEntity<>(reviewService.getAllReviewsByPlayerId(playerId), HttpStatus.OK);
    }

    // GET review by id request
    @GetMapping("/players/{playerId}/reviews/{id}")
    public ResponseEntity<ReviewResponseDto> getReviewById(@PathVariable("playerId") Integer playerId, @PathVariable("id") Integer id) {
        ReviewResponseDto reviewResponseDto = reviewService.getReviewById(playerId, id);
        return new ResponseEntity<>(reviewResponseDto, HttpStatus.OK);
    }

    // POST -> Create new review
    @PostMapping("/players/{playerId}/reviews")
    public ResponseEntity<ReviewResponseDto> createReview(@PathVariable("playerId") Integer playerId,
                                                          @Valid @RequestBody ReviewRequestDto reviewRequestDto) {
        return new ResponseEntity<>(reviewService.createReview(playerId, reviewRequestDto), HttpStatus.CREATED);
    }

    // PUT -> Update a review
    @PutMapping("/players/{playerId}/reviews/{id}")
    public ResponseEntity<ReviewResponseDto> updateReview(@PathVariable("playerId") Integer playerId, @PathVariable("id") Integer id,
                                                          @Valid @RequestBody ReviewRequestDto reviewRequestDto) {
        ReviewResponseDto response = reviewService.updateReview(playerId, id, reviewRequestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/players/{playerId}/reviews/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable("playerId") Integer playerId, @PathVariable("id") Integer id) {
        reviewService.deleteReviewById(playerId, id);
        return ResponseEntity.noContent().build();
    }
}
