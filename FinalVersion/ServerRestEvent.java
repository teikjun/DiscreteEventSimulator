package cs2030.simulator;

/**
 * Event of server resting after serving a customer.
 */
public class ServerRestEvent extends ServerTypeEvent { 
    /**
     * Constructs a ServerRestEvent.
     * @param eventTime time associated with the event
     * @param s server associated with the event
     */
    ServerRestEvent(double eventTime, Server s) {
        super(eventTime, s);
    }

    /**
     * Processes the ServerRestEvent. <br>
     * 1) Server is nextIdle at eventTime + restingPeriod <br> 
     * 2) Removes the current ServerRestEvent,
     * a ServerBackEvent is created at eventTime + restingPeriod. <br>
     */
    @Override 
    public void process() {
        double restingPeriod = Simulator.genRandomRestPeriod();
        s.setNextIdle(this.getEventTime() + restingPeriod);
        EventManager.dequeueEvent();
        EventManager.addServerBackEvent(this.getEventTime() + restingPeriod, s);
    } 
        
    @Override
    public String toString() {
        return String.format("%.3f", this.getEventTime()) + " " + s + " rest";
    }
}
