package com.bmw.seat.book.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bmw.seat.book.entity.Seat;
import com.bmw.seat.book.repository.SeatRepository;

@Configuration
public class DataLoader {

	@Bean
	CommandLineRunner initDatabase(SeatRepository repo) {
		return args -> {
			for (int i = 1; i <= 50; i++) {
				Seat seat = new Seat();
				seat.setSeatNumber("S" + i);
				seat.setBooked(false);
				repo.save(seat);
			}
		};
	}

}
