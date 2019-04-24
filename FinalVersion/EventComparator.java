package cs2030.simulator;

import java.util.Comparator;

/**
 * EventComparator compares two events according to time, then event type, then customer id.
 * Used in the PriorityQueue eventQueue.
 */
public class EventComparator implements Comparator<Event> { 
    /**
     * Compares two events according to time, then event type, then customer id. <br>
     * 1) Compares event time of two events. <br>
     * 2) If event time is the same, ServerTypeEvent comes before CustomerTypeEvent. <br> 
     * This ensures that server can rest immediately, even if a customer arrives at the same time.
     * 3) If both are CustomerTypeEvent, compare the id of the customer in the event.
     */
    @Override
    public int compare(Event e1, Event e2) {
        int result = Double.compare(e1.getEventTime(), e2.getEventTime());
        if (result == 0) {
            if (e1 instanceof ServerTypeEvent) {
                return -1;
            } else if (e2 instanceof ServerTypeEvent) {
                return 1;
            } else {
                CustomerTypeEvent ce1 = (CustomerTypeEvent) e1;
                CustomerTypeEvent ce2 = (CustomerTypeEvent) e2;
                return Integer.compare(ce1.getCustomer().getID(), ce2.getCustomer().getID());
            }
        } else {
            return result;
        }
    }
}
