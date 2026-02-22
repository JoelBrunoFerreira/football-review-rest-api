package com.footballreview.api.repositories;

import com.footballreview.api.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    
}
