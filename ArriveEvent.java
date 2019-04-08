package cs2030.simulator;

public class ArriveEvent extends Event {
    ArriveEvent(double eventTime, Customer c) {
        super(eventTime, c);
        c.setState(CustomerState.ARRIVES);
    }

    @Override
    public void process() {
        Simulator.allocateServer(c);
    }
}
