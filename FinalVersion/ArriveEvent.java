package cs2030.simulator;

/**
 * Event of customer arriving.
 */
public class ArriveEvent extends CustomerTypeEvent {
    /**
     * Constructs an ArriveEvent and sets the CustomerState to ARRIVES.
     * @param eventTime time of event
     * @param c Customer who has arrived
     */
    ArriveEvent(double eventTime, Customer c) {
        super(eventTime, c);
        c.setState(CustomerState.ARRIVES);
    }

    /**
     * Processes the ArriveEvent.
     * Removes the current ArriveEvent and customer chooses a server.
     * The customer is either: <br> 
     * (A) allocated a server who serves now: serveEvent is created <br>
     * (B) allocated a server who serves later: waitEvent is created <br>
     * (C) not allocated a server: leaveEvent is created
     */
    @Override
    public void process() {
        EventManager.dequeueEvent();
        c.chooseServer();
    }
}

