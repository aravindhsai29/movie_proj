package com.jpmc.theater;

public class Reservation {
    private Customer customer;
    private Showing showing;
    private int audienceCount;

    public Reservation(Customer customer, Showing showing, int audienceCount) {
        this.customer = customer;
        this.showing = showing;
        this.audienceCount = audienceCount;
    }
    
    public Customer getCustomer()
    {
    	return customer;
    }
    
    public Showing getShowing()
    {
    	return showing;
    }
    
    public int getaudienceCount()
    {
    	return audienceCount;
    }

    public double totalFee() {
        return showing.getMovieFee() * audienceCount;
    }
    
    public String reserveConfirm(Reservation reservation)
    {
    	String reservation1 = "Dear " + reservation.customer.getname()+" your show for " + reservation.showing.getMovie().getTitle()+" has been reserved for "+reservation.audienceCount+" people for a total of "+ "$"+reservation.totalFee();
    	return reservation1;
    }
}