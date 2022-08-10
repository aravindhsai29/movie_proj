package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Theater {

    LocalDateProvider provider;

    private List<Showing> schedule;

    public Theater(LocalDateProvider provider) {
        this.provider = provider;

        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
        schedule = List.of(
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
    }

    public String reserve(Customer customer, int sequence, int howManyTickets) {
        Showing showing;
        try {
            showing = schedule.get(sequence - 1);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            throw new IllegalStateException("not able to find any showing for given sequence " + sequence);
        }
        Reservation reserve =  new Reservation(customer, showing, howManyTickets);
        System.out.println(reserve.reserveConfirm(reserve));
        System.out.println("Total fee charged : $"+reserve.totalFee());
        return String.valueOf(reserve.totalFee());
        
    }

    public void printSchedule() {
    	System.out.println("text format");
        System.out.println(provider.currentDate());
        System.out.println("===================================================");
        schedule.forEach(s ->
                System.out.println(s.getSequenceOfTheDay() + ": " + s.getStartTime() + " " + s.getMovie().getTitle() + " " + humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getMovieFee())
        );
        System.out.println("===================================================");
        
        System.out.println("JSON format");
        System.out.println("===================================================");
       
        ObjectMapper mapper = new ObjectMapper();
        for(Showing s : schedule)
        {
        	String currentDate = String.valueOf(provider.currentDate());
        	String sequnceOfTheDay = String.valueOf(s.getSequenceOfTheDay());
        	String startTime = String.valueOf(s.getStartTime());
        	String getMovie = s.getMovie().getTitle();
        	String runningTime = humanReadableFormat(s.getMovie().getRunningTime());
        	String movieFee = "$"+String.valueOf(s.getMovieFee());
        
        	MovieSchedulePrinter MovieSchedulePrinter1 = new MovieSchedulePrinter(currentDate,sequnceOfTheDay,startTime,getMovie,runningTime,movieFee);
        	try {
				String jsonSchedule = mapper.writeValueAsString(MovieSchedulePrinter1);
				System.out.println(jsonSchedule);
				
				// example case for one person
				
				 double fee = s.calculateFee(1);
				 System.out.println("One person cost post discount");
		         System.out.println(fee);
		         System.out.println("===================================================");
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	
        	
        }
       
        
    }
    
    

    public String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    // (s) postfix should be added to handle plural correctly
    private String handlePlural(long value) {
        if (value == 1) {
            return "";
        }
        else {
            return "s";
        }
    }

    public static void main(String[] args) {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer customer1 = new Customer("Aravindh","29");
        theater.reserve(customer1,1,20);
        System.out.println("===================================================");
        theater.printSchedule();
    }
}
