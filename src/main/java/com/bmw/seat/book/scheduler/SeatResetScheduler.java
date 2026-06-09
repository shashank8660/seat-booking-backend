package com.bmw.seat.book.scheduler;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bmw.seat.book.repository.SeatRepository;

@Component
public class SeatResetScheduler {

    private final SeatRepository seatRepo;

    public SeatResetScheduler(SeatRepository seatRepo) {
        this.seatRepo = seatRepo;
    }

    //Runs every day at midnight
    @Scheduled(cron = "0 0 0 * * ?")
    public void resetAllSeats() {

        System.out.println("Resetting all seats at midnight...");

        seatRepo.findAll().forEach(seat -> {
            seat.setBooked(false);
            seat.setBookedBy(null);
        });

        seatRepo.saveAll(seatRepo.findAll());

        System.out.println("All seats cleared..");
    }
}

