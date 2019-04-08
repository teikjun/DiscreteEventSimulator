package cs2030.simulator;

public class DoneEvent extends Event {     
    DoneEvent(double eventTime, Customer c) {
        super(eventTime, c);
        c.setState(CustomerState.DONE);
    } 

    @Override
    public void process() {
        EventManager.getEQ().poll();
        EventManager.addDoneCount(); 
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.eventTime) + " " + c.getID() + " " + c.getState().getCustomerString() 
            + " serving by " + c.getMyServer(); 
    }       
}
