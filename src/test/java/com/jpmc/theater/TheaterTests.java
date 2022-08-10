package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class TheaterTests {
    @Test
    void totalFeeForCustomer() {
    	LocalDateProvider provider = new LocalDateProvider();
    	 Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
         Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
         Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
    	 @SuppressWarnings("null")
		List<Showing> schedule = List.of(
                new Showing(turningRed, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0))),
                new Showing(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0))),
                new Showing(theBatMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50))),
                new Showing(turningRed, 4, LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30))),
                new Showing(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10))),
                new Showing(theBatMan, 6, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50))),
                new Showing(turningRed, 7, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30))),
                new Showing(spiderMan, 8, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10))),
                new Showing(theBatMan, 9, LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0)))
            );
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("Aravindh", "29");
        String reservation = theater.reserve(john, 1, 20);
        Showing showing = schedule.get(0);
        Reservation reserve =  new Reservation(john, showing, 20);
//        System.out.println("You have to pay " + reservation.getTotalFee());
        assertEquals(reserve.totalFee(),220d);
    }

    @Test
    void printMovieSchedule() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printSchedule();
    }
}
