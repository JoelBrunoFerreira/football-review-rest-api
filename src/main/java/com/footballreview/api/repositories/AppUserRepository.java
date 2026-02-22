package com.footballreview.api.repositories;

import com.footballreview.api.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findByUsername(String username);
    Boolean existsByUsername(String username);
}
