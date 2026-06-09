package com.bmw.seat.book.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmw.seat.book.entity.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long> {
	

Optional<Seat> findByBookedBy(String bookedBy);


}
