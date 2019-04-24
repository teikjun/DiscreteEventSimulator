package cs2030.simulator;

import java.util.Collections;

/**
 * Differs from a {@link TypicalCustomer} in that: If all servers are not idle,
 * the GreedyCustomer chooses a queue with the least number of customers in line. 
 */
public class GreedyCustomer extends Customer { 
    /**
     * Constructs a customer with the specified id and arrival time.
     * @param id customer id
     * @param arrivalTime customer arrival time
     */
    GreedyCustomer(int id, double arrivalTime) {
        super(id, arrivalTime);
    }
    
    /**
     * Customer chooses a server by scanning serverList for idle server, then server with 
     * the least number of customers in queue, customer leaves if unsuccessful. <br> 
     * Calls either serveNow, serveLater, or creates a leaveEvent. <br>
     * 1) If customer has no server, request each server in serverList to serve customer now <br>
     * 2) If customer has no server, request server with the least customer to serveLater <br>
     * 3) If the customer has no server after 1 and 2, the customer leaves, and exits the queue
     */
    public void chooseServer() {
        for (Server s: Simulator.getServerList()) {
            if (this.getMyServer() == null) {
                s.serveNow(this);
            }
        }
        if (this.getMyServer() == null) {
            Server s = Collections.min(Simulator.getServerList(), new ServerComparator());  
            s.serveLater(this);
        }
        if (this.getMyServer() == null) {
            EventManager.addLeaveEvent(this.getArrivalTime(), this);
        } 
    }

    @Override 
    public String toString() { 
        return this.getID() + "(greedy)";
    }
}
