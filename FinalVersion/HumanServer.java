package cs2030.simulator;

/**
 * HumanServers may rest after serving a customer.
 */
public class HumanServer extends Server {
    /**
     * Constructs a HumanServer with the specified number of servers, maximum queue length
     * and rest probability.
     * @param serverNum number of servers
     * @param maxQueueLength maximum queue length
     * @param restProbability server rest probability
     */
    HumanServer(int serverNum, int maxQueueLength, double restProbability) {
        super(serverNum, maxQueueLength, restProbability);
    }
    
    /**
     * Checks if server is resting after serving the current customer.
     * If the randomly generated rest number is less than server restProbability,
     * the server is resting. 
     * @return true if server is resting
     */
    @Override
    public boolean isServerResting() {
        double randomRestNumber = Simulator.genRandomRestNumber();
        return randomRestNumber < this.getRestProbability();
    }
    
    @Override
    public String toString() {
        return "server " + this.getServerNum();
    }
}
