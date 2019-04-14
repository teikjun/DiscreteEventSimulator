package cs2030.simulator;
import java.util.ArrayList;

public class Simulator { 
    private static ArrayList<Server> serverList = new ArrayList<Server>();
    private static int numOfServers = 0;
    private static double timeNow = 0.0;
    private static RandomGenerator rg = null;
    /**
     * Create a list of (numOfServers) servers
     * @param numOfServers number of servers 
     */ 
    public static void addServers(int numOfServers) {
        Simulator.numOfServers = numOfServers;
        int n = numOfServers;
        for (int serverNum = 1; n > 0; serverNum++) {
            Server s = new Server(serverNum);
            serverList.add(s);
            n--;
        }
    }
    /**
     * Generates a queue of all arriveEvents.
     * Creates a RandomGenerator, then while there are remaining customer(s): 
     * 1) Create a customer with id and timeNow
     * 2) Add an ArriveEvent with timeNow and the customer
     * 3) Update timeNow to timeNow + interArrivalTime 
     */
    public static void generateEvents(int numOfCustomers, int seed, double arrivalRate, double serviceRate) {
        rg = new RandomGenerator(seed, arrivalRate, serviceRate);
        int id = 1;
        while (numOfCustomers != 0) {
            Customer c = new Customer(id, timeNow);
            EventManager.addArriveEvent(timeNow, c);
            id++;
            numOfCustomers--;
            timeNow = timeNow + rg.genInterArrivalTime();
        }
    }

    public static void simulate() {
        EventManager.allEvents();
    }

    /**
     * Calls either serveNow, serveLater, or removes the current arriveEvent and creates a leaveEvent.
     * 1) While the customer has no server, requests for each server in serverList to serve customer now <br>
     * 2) While the customer has no server, requests for each server in serverList to serve customer later <br>
     * 3) If the customer has no server after 1 and 2, the customer leaves, and exits the queue <br>
     */
    public static void allocateServer(Customer c) {
        for (int i = 0; (c.getMyServer() == null) && (i < numOfServers); i++) {
            serverList.get(i).serveNow(c);
        }
        for (int j = 0; (c.getMyServer() == null) && (j < numOfServers); j++) {
            serverList.get(j).serveLater(c);
        } 
        if (c.getMyServer() == null) {
            EventManager.getEQ().poll();
            EventManager.addLeaveEvent(c.getArrivalTime(), c);
        } 
    }

    public static RandomGenerator getRG() {
        return rg;
    }
}
