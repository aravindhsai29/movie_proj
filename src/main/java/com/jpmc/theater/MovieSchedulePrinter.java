package com.jpmc.theater;

import java.util.Objects;

public class MovieSchedulePrinter {

    public String currentDate;
    public String sequnceOfTheDay;
    public String startTime;
    public String movieTitle;
    public String runningTime;
    public String movieFee;

   
    public MovieSchedulePrinter(String currentDate, String sequnceOfTheDay,String startTime,String movieTitle,String runningTime,String movieFee) {
        this.currentDate = currentDate; 
        this.sequnceOfTheDay = sequnceOfTheDay;
        this.startTime=startTime;
        this.movieTitle=movieTitle;
        this.runningTime = runningTime;
        this.movieFee = movieFee;

        }

   
}