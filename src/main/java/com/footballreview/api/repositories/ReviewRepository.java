package com.footballreview.api.repositories;

import com.footballreview.api.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findByPlayer_Id(Integer playerId);
}
