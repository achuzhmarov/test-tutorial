package com.github.achuzhmarov.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    public Optional<AppUser> findByLogin(String login);
}
