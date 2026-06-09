package com.bmw.seat.book.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bmw.seat.book.entity.Seat;
import com.bmw.seat.book.services.SeatService;

@RestController
@RequestMapping("/api/seats")
@CrossOrigin("*")
public class SeatController {

	@Autowired
	private SeatService service;

	@GetMapping
	public List<Seat> getSeats() {
		return service.getAllSeats();
	}

	@PostMapping("/toggle/{id}")
	public Seat toggleSeat(@PathVariable Long id, @RequestParam String email) {

		return service.toggleSeat(id, email);
	}

}
