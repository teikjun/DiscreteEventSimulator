package cs2030.simulator;

public class ServeEvent extends Event { 
    ServeEvent(double eventTime, Customer c) {
        super(eventTime, c);
        c.setState(CustomerState.SERVED);
    }

    @Override
    public void process() {
        //c.setEventTime(c.getEventTime() + Server.SERVETIME);   
        //c.setState(CustomerState.DONE);
        EventManager.getEQ().poll();
        EventManager.addDoneEvent(this.eventTime + Server.SERVETIME, c);
    }

    @Override 
    public String toString() {
        return String.format("%.3f", this.eventTime) + " " + c.getID() + " " + c.getState().getCustomerString() 
            + " by " + c.getMyServer();
    }
}
