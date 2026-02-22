package com.footballreview.api.repositories;

import com.footballreview.api.entities.Role;
import com.footballreview.api.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRole(RoleName role);
}
