package com.bmw.seat.book.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmw.seat.book.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
