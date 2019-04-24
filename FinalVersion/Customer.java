package cs2030.simulator;

/**
 * Customer class is the abstract parent class of {@link TypicalCustomer} and 
 * {@link GreedyCustomer}.
 * Subclasses of Customer must implement chooseServer().
 */
public abstract class Customer {
    private final int id;
    private final double arrivalTime;
    private CustomerState state = null;
    private Server myServer = null;

    /**
     * Constructs a customer with the specified id and arrival time.
     * @param id customer id
     * @param arrivalTime customer arrival time
     */
    Customer(int id, double arrivalTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        state = CustomerState.ARRIVES;
    }

    /**
     * Customer chooses a server to be served by, the customer leaves if unsuccessful.
     */
    public abstract void chooseServer();

    //getters and setters    
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
