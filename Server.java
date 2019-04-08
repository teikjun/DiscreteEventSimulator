package cs2030.simulator;

public class Server {
    static final double SERVETIME = 1.0;
    private boolean isIdle = true;
    private double nextIdle = 0.0;
    private boolean isCustomerWaiting = false;
    private double nextWaitFree = 0.0; 
    private int serverNum = 0; 

    Server(int serverNum) {
        this.serverNum = serverNum;
    }
   
    public void serveNow(Customer c) {
        if (this.isIdle = this.checkIdle(c)) {
            this.isIdle = false; 
            this.nextIdle = c.getArrivalTime() + Server.SERVETIME;
            //c.setState(CustomerState.SERVED);
            c.setMyServer(this);
            EventManager.getEQ().poll();
            EventManager.addServeEvent(c.getArrivalTime(), c);
        }
    }
    
    public void serveLater(Customer c) {
        if (!(this.isCustomerWaiting = this.checkIfCustomerWaiting(c))) {
            this.isCustomerWaiting = true;
            this.nextWaitFree = this.nextIdle;
            this.nextIdle += Server.SERVETIME;
            //c.setState(CustomerState.WAITS);
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
    public double getNextWaitFree() {
        return nextWaitFree;
    }
    public boolean checkIdle(Customer c) { 
        return nextIdle <= c.getArrivalTime();
    }
    public boolean checkIfCustomerWaiting(Customer c) {
        return c.getArrivalTime() < nextWaitFree;
    }
     
    @Override
    public String toString() {
        return "" + serverNum;
    }
    
    
    /*public void serve(Customer c) {
        if (this.isIdle = this.checkIdle(c)) {
            this.isIdle = false; 
            this.nextIdle = c.getArrivalTime() + Server.SERVETIME;
            c.setState(CustomerState.SERVED);
            c.setMyServer(this);
        } else if (!(this.isCustomerWaiting = this.checkIfCustomerWaiting(c))) {
            this.isCustomerWaiting = true;
            this.nextWaitFree = this.nextIdle;
            this.nextIdle += Server.SERVETIME;
            c.setState(CustomerState.WAITS);
            c.setMyServer(this);
        }
    }*/
}
