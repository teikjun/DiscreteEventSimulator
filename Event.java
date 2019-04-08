package cs2030.simulator;

public abstract class Event { 
    protected double eventTime = 0.0;
    protected Customer c = null;

    Event(double eventTime, Customer c) {
        this.eventTime = eventTime;
        this.c = c;
    }
    
    public abstract void process();
        
    public double getEventTime() {
        return eventTime;
    }

    public Customer getCustomer() {
        return c;
    }
    
    @Override 
    public String toString() {
        return String.format("%.3f", this.eventTime) + " " + c.getID() + " " + c.getState().getCustomerString();
    }
}
