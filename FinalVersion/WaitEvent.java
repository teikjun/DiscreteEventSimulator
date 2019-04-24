package cs2030.simulator;

/**
 * Event of customer waiting.
 */
public class WaitEvent extends CustomerTypeEvent { 
    /**
     * Constructs an WaitEvent and sets the CustomerState to WAITS.
     * @param eventTime time of event
     * @param c Customer who is waiting
     */
    WaitEvent(double eventTime, Customer c) {
        super(eventTime, c);
        c.setState(CustomerState.WAITS);
    }

    /**
     * Processes the waitEvent. <br>
     * 1) The waiting customer joins the customerQueue from the back <br> 
     * 2) Current waitEvent is removed from eventQueue
     */
    @Override
    public void process() {
        Server s = c.getMyServer();
        s.enqueueCustomer(c);
        EventManager.dequeueEvent();
    }

    @Override 
    public String toString() {
        return String.format("%.3f", this.getEventTime()) + " " + c + " " 
            + c.getState().getCustomerString() + " to be served by " + c.getMyServer();
    }
}
