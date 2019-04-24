package cs2030.simulator;

/**
 * Event of customer done with service.
 */
public class DoneEvent extends CustomerTypeEvent {     
    /**
     * Constructs an DoneEvent and sets the CustomerState to DONE.
     * @param eventTime time of event
     * @param c Customer who is done 
     */
    DoneEvent(double eventTime, Customer c) {
        super(eventTime, c);
        c.setState(CustomerState.DONE);
    }

    /**
     * Processes the DoneEvent. <br>
     * 1) DoneEvent is removed from eventQueue, add one to doneCount (for statistics) <br>
     * 2) If the server is resting, a serverRest event is created with the current eventTime <br>
     * 3) If server is not resting, then if the server still has customers in <br>
     * its customer queue, a serveEvent is created with the current eventTime 
     */
    @Override
    public void process() {
        EventManager.dequeueEvent();
        EventManager.addDoneCount();
        Server s = c.getMyServer();
        if (s.isServerResting()) {
            EventManager.addServerRestEvent(this.getEventTime(), s);
        } else if (!s.isCustomerQueueEmpty()) {
            Customer nextCustomer = s.dequeueCustomer(); 
            EventManager.addServeEvent(this.getEventTime(), nextCustomer);
        }
    }
        
    @Override
    public String toString() {
        return String.format("%.3f", this.getEventTime()) + " " + c + " " 
            + c.getState().getCustomerString() + " serving by " + c.getMyServer(); 
    }       
}
