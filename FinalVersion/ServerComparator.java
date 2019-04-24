package cs2030.simulator;

import java.util.Comparator;

/**
 * ServerComparator compares the length of the customer queue of the servers.
 * Used by {@link GreedyCustomer} to look for the shortest queue.
 */
public class ServerComparator implements Comparator<Server> {
    /**
     * Compares the length of the customer queue of the servers.
     * @param s1 first server
     * @param s2 second server
     */
    @Override
    public int compare(Server s1, Server s2) { 
        return Integer.compare(s1.getCustomerQueueLength(), s2.getCustomerQueueLength());
    }
}
