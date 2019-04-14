package cs2030.simulator;

public class ArriveEvent extends Event {
    ArriveEvent(double eventTime, Customer c) {
        super(eventTime, c);
        c.setState(CustomerState.ARRIVES);
    }
    /**
     * Calls allocateServer with the customer.
     * The customer is either: <br> 
     * (A) allocated a server who serves now: serveEvent <br>
     * (B) allocated a server who serves later: waitEvent <br>
     * (C) not allocated a server: leaveEvent <br>
     */
    @Override
    public void process() {
        Simulator.allocateServer(c);
    }
}
