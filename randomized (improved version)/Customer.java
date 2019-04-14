package cs2030.simulator;

public class Customer {
    private final int id;
    private final double arrivalTime;
    private CustomerState state = null;
    private Server myServer = null;

    Customer(int id, double arrivalTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        state = CustomerState.ARRIVES;
    }
    
    public int getID() {
        return this.id;
    }
    public double getArrivalTime() {
        return this.arrivalTime;
    }
    public CustomerState getState() {
        return this.state;
    }
    public void setState(CustomerState state) {
        this.state = state;
    }
    public Server getMyServer() {
        return myServer;
    }
    public void setMyServer(Server myServer) {
        this.myServer = myServer;
    }
    
}
