package cs2030.simulator;

public class WaitEvent extends Event { 
    WaitEvent(double eventTime, Customer c) {
        super(eventTime, c);
        c.setState(CustomerState.WAITS);
    }

    @Override
    public void process() {
        Server myServer = c.getMyServer();
        double customerWaitTime = myServer.getNextWaitFree() - c.getArrivalTime();
        EventManager.addWaitTime(customerWaitTime); 
        //c.setEventTime(myServer.getNextWaitFree());
        //c.setState(CustomerState.SERVED);
        EventManager.getEQ().poll();
        EventManager.addServeEvent(c.getMyServer().getNextWaitFree(), c);
    }

    @Override 
    public String toString() {
        return String.format("%.3f", this.eventTime) + " " + c.getID() + " " + c.getState().getCustomerString() 
            + " to be served by " + c.getMyServer();
    }
}
