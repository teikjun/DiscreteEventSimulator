package cs2030.simulator;

public class ServeEvent extends Event { 
    ServeEvent(double eventTime, Customer c) {
        super(eventTime, c);
        c.setState(CustomerState.SERVED);

    }
    /**
     * Processes the serveEvent. 
     * 1) The server of the customer is nextIdle when the customer is done
     *    server now has no customer waiting <br>
     * 2) Current serveEvent is removed from eq, 
     * a doneEvent is created at time when customer is done (when server is next idle)
     */
    @Override
    public void process() {
        double serveTime = Simulator.getRG().genServiceTime();
        //System.out.println("serveTime: " + serveTime);
        Server s = c.getMyServer();
        s.setNextIdle(s.getNextIdle() + serveTime);
        s.setIsCustomerWaiting(false);
        EventManager.getEQ().poll();
        EventManager.addDoneEvent(s.getNextIdle(), c);
    }

    @Override 
    public String toString() {
        return String.format("%.3f", this.eventTime) + " " + c.getID() + " " + c.getState().getCustomerString() 
            + " by " + c.getMyServer();
    }
}
