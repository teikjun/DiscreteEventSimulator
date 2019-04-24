package cs2030.simulator;

/**
 * Event is the abstract parent class of all server type and customer type events. 
 * All concrete subclasses of Event must implement the abstract method process().
 */
public abstract class Event { 
    private double eventTime = 0.0;
    
    /**
     * Constructor for Event, to be used by classes that extends Event.
     * @param eventTime time associated with the event
     */
    Event(double eventTime) {
        this.eventTime = eventTime;
    }
    
    public abstract void process();
        
    public double getEventTime() {
        return eventTime;
    }
}
