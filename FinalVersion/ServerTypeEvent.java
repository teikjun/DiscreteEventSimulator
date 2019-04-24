package cs2030.simulator;

/**
 * ServerTypeEvent is the abstract parent class of all events that holds a server reference.
 */
public abstract class ServerTypeEvent extends Event {
    protected Server s = null;

    /**
     * Constructor for ServerTypeEvent, used by events that extends ServerTypeEvent.
     * @param eventTime time associated with the event
     * @param s server associated with the event
     */
    ServerTypeEvent(double eventTime, Server s) {
        super(eventTime);
        this.s = s; 
    }
}
