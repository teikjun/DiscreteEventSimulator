package cs2030.simulator;

/**
 * TypicalCustomer class looks for an idle server, then for the first non-full queue to join.
 * If all customer queues are full, the typical customer leaves.
 */
public class TypicalCustomer extends Customer { 
    /**
     * Constructs a customer with the specified id and arrival time.
     * @param id customer id
     * @param arrivalTime customer arrival time
     */
    TypicalCustomer(int id, double arrivalTime) {
        super(id, arrivalTime);
    }
    
    /**
     * Customer chooses a server by scanning serverList for idle server, 
     * then server with queue, customer leaves if unsuccessful. 
     * Calls either serveNow, serveLater, or creates a leaveEvent. <br>
     * 1) If customer has no server, request each server in serverList to serve customer now <br>
     * 2) If customer has no server, request each server in serverList to serve customer later <br>
     * 3) If the customer has no server after 1 and 2, the customer leaves, and exits the queue
     */
    public void chooseServer() {
        for (Server s: Simulator.getServerList()) {
            if (this.getMyServer() == null) {
                s.serveNow(this);
            }
        }
        for (Server s: Simulator.getServerList()) {
            if (this.getMyServer() == null) {
                s.serveLater(this);
            }
        }
        if (this.getMyServer() == null) {
            EventManager.addLeaveEvent(this.getArrivalTime(), this);
        } 
    }
    
    @Override 
    public String toString() {
        return "" + this.getID();
    }
}
