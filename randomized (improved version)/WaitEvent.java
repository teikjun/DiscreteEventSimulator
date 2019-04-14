package cs2030.simulator;

public class WaitEvent extends Event { 
    WaitEvent(double eventTime, Customer c) {
        super(eventTime, c);
        c.setState(CustomerState.WAITS);
    }
    /**
     * Processes the waitEvent.
     * 1) adds currentWaitTime to the total wait time <br> 
     * 2) server now has customer waiting <br>
     * 3) removes the current waitEvent from eq and  
     * creates serveEvent at time when the customer gets served (when server is next idle) 
     */
    @Override
    public void process() {
        Server s = c.getMyServer();
        double customerWaitTime = s.getNextIdle() - c.getArrivalTime();
        EventManager.addWaitTime(customerWaitTime); 
        s.setIsCustomerWaiting(true);
        EventManager.getEQ().poll();
        EventManager.addServeEvent(s.getNextIdle(), c);
    }

    @Override 
    public String toString() {
        return String.format("%.3f", this.eventTime) + " " + c.getID() + " " + c.getState().getCustomerString() 
            + " to be served by " + c.getMyServer();
    }
}
