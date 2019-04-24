package cs2030.simulator;

/**
 * A CountServer differs from {@link HumanServer} in that it does not need to rest.
 */
public class CounterServer extends Server { 
    /**
     * Constructs a CounterServer with the specified number of servers, maximum queue length
     * and rest probability.
     * @param serverNum number of servers
     * @param maxQueueLength maximum queue length
     * @param restProbability server rest probability
     */
    CounterServer(int serverNum, int maxQueueLength, double restProbability) {
        super(serverNum, maxQueueLength, restProbability);
    }

    /**
     * Checks if server is resting after serving the current customer.
     * Always returns false because Counter Servers do not need to rest.
     * @return false 
     */
    @Override
    public boolean isServerResting() {
        return false;
    }

    @Override
    public String toString() {
        return "self-check " + this.getServerNum();
    }
}
