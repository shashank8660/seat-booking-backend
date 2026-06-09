package com.bmw.seat.book.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.bmw.seat.book.entity.Seat;
import com.bmw.seat.book.repository.SeatRepository;

@Service
public class SeatService {

	@Autowired
	private SeatRepository seatRepo;

	public List<Seat> getAllSeats() {
		return seatRepo.findAll();
	}

	@CacheEvict(value = "seats", allEntries = true)
	public synchronized Seat toggleSeat(Long id, String userEmail) {

	    Seat seat = seatRepo.findById(id).orElseThrow();

	    String seatUser = seat.getBookedBy() == null ? "" : seat.getBookedBy().trim().toLowerCase();
	    String currentUser = userEmail == null ? "" : userEmail.trim().toLowerCase();

	   
	    if (!seatUser.isEmpty() && seatUser.equals(currentUser)) {

	        seat.setBooked(false);
	        seat.setBookedBy(null);

	        return seatRepo.save(seat);
	    }

	    //Check if user already booked another seat
	    Optional<Seat> existingSeat = seatRepo.findByBookedBy(userEmail);

	    if (existingSeat.isPresent()) {
	        throw new RuntimeException("You already booked a seat");
	    }

	    if (seat.isBooked()) {
	        throw new RuntimeException("Seat already taken");
	    }

	    seat.setBooked(true);
	    seat.setBookedBy(currentUser); //always save normalized

	    return seatRepo.save(seat);
	}

	
}
