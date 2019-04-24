package cs2030.simulator;

/**
 * Event of customer getting served.
 */
public class ServeEvent extends CustomerTypeEvent { 
    /**
     * Constructs an ServeEvent and sets the CustomerState to SERVED.
     * @param eventTime time of event
     * @param c Customer who has been served
     */
    ServeEvent(double eventTime, Customer c) {
        super(eventTime, c);
        c.setState(CustomerState.SERVED);
    }

    /**
     * Processes the serveEvent. 
     * 1) Add the customer's wait time to total wait time (for statistics) <br>
     * 2) The server is nextIdle when the current customer is done <br>
     * 3) Current serveEvent is removed from eventQueue. A doneEvent is created:
     *    eventTime is when customer is done (when server is nextIdle)
    */
    @Override
    public void process() {
        this.addCustomerWaitTime();
        double serveTime = Simulator.genRandomServiceTime();
        Server s = c.getMyServer();
        s.setNextIdle(this.getEventTime() + serveTime);
        EventManager.dequeueEvent();
        EventManager.addDoneEvent(s.getNextIdle(), c);
    }

    /** 
     * Adds current customer's wait time to total wait time.
     * If the customer is served at time of arrival, 
     * serveEvent eventTime == customer's arrival time. Therefore, wait time is 0.0.
     * Otherwise, wait time is the difference between the 
     * serveEvent eventTime and customer's arrival time  
     */
    public void addCustomerWaitTime() {
        double customerWaitTime = this.getEventTime() - c.getArrivalTime();
        EventManager.addWaitTime(customerWaitTime);
    }

    @Override 
    public String toString() {
        return String.format("%.3f", this.getEventTime()) + " " + c + " " 
            + c.getState().getCustomerString() + " by " + c.getMyServer();
    }
}
