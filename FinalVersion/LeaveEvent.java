package cs2030.simulator;

/**
 * Event of customer leaving without getting served.
 */
public class LeaveEvent extends CustomerTypeEvent {
    /**
     * Constructs an LeaveEvent and sets the CustomerState to LEAVES.
     * @param eventTime time of event
     * @param c Customer who has left
     */
    LeaveEvent(double eventTime, Customer c) {
        super(eventTime, c);
        c.setState(CustomerState.LEAVES);
    }

    /** 
     * Processes the LeaveEvent.
     * Removes the LeaveEvent from eventQueue, add one to leaveCount (for statistics)
     */
    @Override
    public void process() {
        EventManager.dequeueEvent();
        EventManager.addLeaveCount(); 
    }
}
