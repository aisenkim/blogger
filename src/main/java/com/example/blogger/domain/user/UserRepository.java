package com.example.blogger.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Identify if user is registered or not
     */
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    User findByUsername(String username);
}
