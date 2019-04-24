package cs2030.simulator;

/**
 * Event of server coming back from resting.
 */
public class ServerBackEvent extends ServerTypeEvent { 
    /**
     * Constructs a ServerBackEvent.
     * @param eventTime time associated with the event
     * @param s server associated with the event
     */ 
    ServerBackEvent(double eventTime, Server s) {
        super(eventTime, s);
    }
   
    /** 
     * Processes the ServerBackEvent. <br>
     * 1) The ServerBackEvent is removed. <br> 
     * 2) If the Server has customers in its queue, 
     * create a serveEvent with the nextCustomer and currentTime 
     */
    @Override 
    public void process() {
        EventManager.dequeueEvent();
        if (!s.isCustomerQueueEmpty()) {
            Customer nextCustomer = s.dequeueCustomer(); 
            EventManager.addServeEvent(this.getEventTime(), nextCustomer);
        }
    } 
    
    @Override 
    public String toString() {
        return String.format("%.3f", this.getEventTime()) + " " + s + " back";
    }
}

