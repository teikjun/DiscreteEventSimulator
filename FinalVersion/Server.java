package cs2030.simulator;

import java.util.LinkedList;

/**
 * Server class is the abstract parent class of {@link HumanServer} and {@link CounterServer}. 
 * The represent human servers and self-checkout counter servers respectively. 
 * The subclasses of Server must implement isServerResting(). 
 */
public abstract class Server {
    private double nextIdle = 0.0;
    private final int serverNum;
    private final int maxQueueLength;
    private final double restProbability;
    private LinkedList<Customer> customerQueue = new LinkedList<Customer>();

    /**
     * Constructs a Server with the specified number of servers, maximum queue length
     * and rest probability.
     * @param serverNum number of servers
     * @param maxQueueLength maximum queue length
     * @param restProbability server rest probability
     */
    Server(int serverNum, int maxQueueLength, double restProbability) {
        this.serverNum = serverNum;
        this.maxQueueLength = maxQueueLength;
        this.restProbability = restProbability;
    }
    
    /**
     * Checks if server is resting after serving the current customer.
     * @return true if server is resting, false otherwise.
     */
    public abstract boolean isServerResting();
    
    /** 
     * If server is idle, serve the customer.
     * Sets nextIdle to the arrival time of the customer,
     * to facilitate calculation of nextIdle in serveEvent.
     * Removes the arriveEvent and adds a serveEvent.
     * @param c Customer to be served now
     */
    public void serveNow(Customer c) {
        if (this.isServerIdle(c)) {
            c.setMyServer(this);
            EventManager.addServeEvent(c.getArrivalTime(), c);
        }
    }
    
    /**
     * If server has no waiting customer, customer waits in this server's queue.
     * Removes the arrive event, creates a wait event.
     * @param c Customer to be added in the queue and served later
     */
    public void serveLater(Customer c) {
        if (!this.isCustomerQueueFull()) {
            c.setMyServer(this);
            EventManager.addWaitEvent(c.getArrivalTime(), c);
        }
    }
    
    /**
     * Checks if the server is idle.
     * If customer's arrival time is after server's next idle time, the server is idle
     * @param c Customer who has just arrived and is looking to be allocated a server
     * @return true if server is idle, false otherwise
     */
    public boolean isServerIdle(Customer c) { 
        return this.nextIdle <= c.getArrivalTime();
    }
    
    /**
     * Gets the length of the customerQueue.
     * @return length of customerQueue
     */
    public int getCustomerQueueLength() {
        return this.customerQueue.size();
    }
    
    /**
     * Checks if customer queue is full.
     * If the customer queue has size equal to maximum queue capacity (maxQueueLength),
     * the queue is full
     * @return true if customer queue is full, false otherwise
     */
    public boolean isCustomerQueueFull() {
        return this.customerQueue.size() == maxQueueLength;
    }
    
    /**
     * Checks if customerQueue is empty. 
     * @return true if customer queue is empty, false otherwise
     */
    public boolean isCustomerQueueEmpty() {
        return this.customerQueue.isEmpty();
    }
    
    /** 
     * Removes the first customer from customerQueue.
     * @return the removed Customer
     */
    public Customer dequeueCustomer() {
        return customerQueue.remove();
    }
    
    /**
     * Adds customer to the end of customerQueue.
     * @param c Customer to be added 
     */
    public void enqueueCustomer(Customer c) {
        customerQueue.add(c);
    }
    
    //getters and setters
    public double getNextIdle() {
        return nextIdle;
    }

    public void setNextIdle(double nextIdle) {
        this.nextIdle = nextIdle;
    }

    public double getRestProbability() {
        return restProbability;
    }

    public int getServerNum() {
        return serverNum;
    }
}
