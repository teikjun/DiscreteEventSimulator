package cs2030.simulator;

/**
 * CustomerTypeEvent is the abstract parent class of all events that holds a customer reference.
 */
public abstract class CustomerTypeEvent extends Event {
    protected Customer c = null;
    
    /**
     * Constructor for CustomerTypeEvent, used by events that extends CustomerTypeEvent.
     * @param eventTime time associated with the event
     * @param c Customer associated with the event
     */
    CustomerTypeEvent(double eventTime, Customer c) {
        super(eventTime);
        this.c = c; 
    }

    public Customer getCustomer() {
        return c;
    }

    /**
     * toString() method, inherited by ArriveEvent and LeaveEvent, 
     * other events override this method.
     */
    @Override 
    public String toString() {
        return String.format("%.3f", this.getEventTime()) + " " + c + " " 
            + c.getState().getCustomerString();
    }
}
