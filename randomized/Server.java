package cs2030.simulator;

public class Server {
    /**
     * to check if the server is idle, isIdle must be assigned the return value of the checkIdle method
     */
    private boolean isIdle = true;
    private double nextIdle = 0.0;
    private boolean isCustomerWaiting = false;
    //private double nextWaitFree = 0.0; 
    private int serverNum = 0; 

    Server(int serverNum) {
        this.serverNum = serverNum;
    }
    /** 
     * If server is idle, serve the customer.
     * Sets nextIdle to the arrival time of the customer, to facilitate calculation of nextIdle in serveEvent
     * removes the arriveEvent and adds a serveEvent
     */
    public void serveNow(Customer c) {
        if (this.isIdle = this.checkIdle(c)) {
            this.nextIdle = c.getArrivalTime();
            c.setMyServer(this);
            EventManager.getEQ().poll();
            EventManager.addServeEvent(c.getArrivalTime(), c);
        }
    }
    /**
     * If server has no waiting customer, customer waits in this server's queue.
     * Removes the arrive event, creates a wait event
     */
    public void serveLater(Customer c) {
        if (!(this.isCustomerWaiting)) {
            c.setMyServer(this);
            EventManager.getEQ().poll();
            EventManager.addWaitEvent(c.getArrivalTime(), c);
        }
    }

    public boolean getIdle() {
        return isIdle;
    }   
    public double getNextIdle() {
        return nextIdle;
    }
    public void setNextIdle(double nextIdle) {
        this.nextIdle = nextIdle;
    }
    public void setIsCustomerWaiting(boolean isCustomerWaiting) {
        this.isCustomerWaiting = isCustomerWaiting;
    }
    /**
     * Determines if the server is idle
     */
    public boolean checkIdle(Customer c) { 
        return nextIdle <= c.getArrivalTime();
    }
     
    @Override
    public String toString() {
        return "" + serverNum;
    }
}
