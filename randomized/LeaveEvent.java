package cs2030.simulator;

public class LeaveEvent extends Event {
    LeaveEvent(double eventTime, Customer c) {
        super(eventTime, c);
        c.setState(CustomerState.LEAVES);
    }
    /** 
     * Removes the leaveEvent from eq
     */
    @Override
    public void process() {
        EventManager.getEQ().poll();
        EventManager.addLeaveCount(); 
    }
}
