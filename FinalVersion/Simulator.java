package cs2030.simulator;

import java.util.ArrayList;

/**
 * Simulator takes input from {@link Main} class to start the simulation.
 * Upon calling the simulate method, the Simulator class creates a randomGenerator and 
 * contains methods to access the randomGenerator. It also creates an ArrayList of servers, 
 * and adds all initial arriveEvents to eventQueue. Lastly, it calls EventManager class to 
 * start printing and processing all events.
 * <p> Note that all methods in Simulator are static,
 * no instances of Simulator should be created because there should only be a single simulation.
 * </p>
 */
public class Simulator { 
    private static ArrayList<Server> serverList = new ArrayList<Server>();
    private static int numOfServers = 0;
    private static RandomGenerator randomGenerator = null;
    
    /**
     * Starts the simulation with all the input parameters. <br>
     * Tasks are delegated to other methods: <br>
     * 1) Creates a randomGenerator <br>
     * 2) adds the servers to serverList <br>
     * 3) adds arriveEvents to eventQueue, <br>
     * 4) starts printing and processing all events.
     * @param seed seed for RandomGenerator 
     * @param numOfHumanServers number of human servers
     * @param numOfCounterServers number of self-checkout counter servers
     * @param maxQueueLength maximum queue length
     * @param numOfCustomers number of customers
     * @param arrivalRate arrival rate for RandomGenerator
     * @param serviceRate service rate for RandomGenerator
     * @param restingRate resting rate for RandomGenerator
     * @param restProbability server rest probability
     * @param greedyProbability greedy customer probability
     */
    public static void simulate(int seed, int numOfHumanServers, int numOfCounterServers, 
            int maxQueueLength, int numOfCustomers, double arrivalRate, double serviceRate, 
            double restingRate, double restProbability, double greedyProbability) {
        Simulator.randomGenerator = new RandomGenerator(seed, arrivalRate, serviceRate, 
                                                        restingRate);
        Simulator.addServers(numOfHumanServers, numOfCounterServers, 
                             maxQueueLength, restProbability);
        Simulator.generateEvents(numOfCustomers, greedyProbability);
        EventManager.allEvents();
    }
    
    /**
     * Create a list of (numOfServers) servers.
     * @param numOfHumanServers number of human servers
     * @param numOfCounterServers number of self-checkout counter servers
     * @param maxQueueLength maximum queue length
     * @param restProbability probability that server will rest
     */ 
    public static void addServers(int numOfHumanServers, int numOfCounterServers, 
                                  int maxQueueLength, double restProbability) {
        Simulator.numOfServers = numOfHumanServers + numOfCounterServers;
        int serverNum = 1;
        for (int i = numOfHumanServers; i > 0; i--) {
            Server s = new HumanServer(serverNum, maxQueueLength, restProbability);
            serverList.add(s);
            serverNum++;
        }
        for (int j = numOfCounterServers; j > 0; j--) {
            Server s = new CounterServer(serverNum, maxQueueLength, 0);
            serverList.add(s);
            serverNum++;
        }
    }
    
    /**
     * Adds all arriveEvents to the eventQueue. <br>
     * Creates a RandomGenerator, then while there are remaining customer(s): <br>
     * 1) Create a customer with id and scheduledArrivalTime <br>
     * 2) Add an ArriveEvent with scheduledArrivalTime and the customer <br>
     * 3) Update scheduledArrivalTime to scheduledArrivalTime + interArrivalTime 
     * @param numOfCustomers number of customers
     * @param greedyProbability greedy customer probability
     */
    public static void generateEvents(int numOfCustomers, double greedyProbability) {
        double scheduledArrivalTime = 0.0;
        int id = 1;
        for (int n = numOfCustomers; n != 0; n--) {
            Customer c = null; 
            if (!isCustomerGreedy(greedyProbability)) {
                c = new TypicalCustomer(id, scheduledArrivalTime);
            } else {
                c = new GreedyCustomer(id, scheduledArrivalTime);
            }
            EventManager.addArriveEvent(scheduledArrivalTime, c);
            id++;
            scheduledArrivalTime += randomGenerator.genInterArrivalTime();
        }
    }
    
    /** 
     * Checks if the arriving customer is greedy, 
     * returns true if number generated is less than greedy probability.
     * @param greedyProbability greedy customer probability
     * @return true if customer is greedy, false otherwise
     */ 
    public static boolean isCustomerGreedy(double greedyProbability) {
        double customerTypeNumber = randomGenerator.genCustomerType();
        return customerTypeNumber < greedyProbability;
    }
    
    /**
     * Generates random service time for serveEvents.
     * @return random service time of type double
     */
    public static double genRandomServiceTime() {
        return Simulator.randomGenerator.genServiceTime();
    }
    
    /**
     * Generates a random rest number, to be compared with server's restProbability.
     * @return random rest number
     */
    public static double genRandomRestNumber() {
        return Simulator.randomGenerator.genRandomRest();
    }
    
    /**
     * Generates a random rest period for serverRestEvents.
     * @return random rest period
     */
    public static double genRandomRestPeriod() {
        return Simulator.randomGenerator.genRestPeriod();
    }

    //getters
    public static int getNumOfServers() {
        return numOfServers;
    }

    public static ArrayList<Server> getServerList() {
        return serverList;
    }
}

