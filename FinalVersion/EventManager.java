package cs2030.simulator;

import java.util.PriorityQueue;

/**
 * EventManager handles events and their associated statistics.
 * Contains methods to add various events to eventQueue and remove events from eventQueue.
 * Upon calling allEvents(), all events will be printed and processed, 
 * A {@link Statistic} object will also be created to print the statistics of the simulation. 
 */
public class EventManager {
    private static PriorityQueue<Event> eventQueue = new PriorityQueue<>(new EventComparator());
    private static double totalWaitTime = 0;
    private static int doneCount = 0;
    private static int leaveCount = 0;
   
    /**
     * Prints and processes all events, then prints statistics.
     * 1) While eventQueue is not empty, call nextEvent <br>
     * 2) Print statistics.
     */
    public static void allEvents() {
        while (!eventQueue.isEmpty()) { 
            nextEvent();
        }
        Statistic stat = new Statistic(totalWaitTime, doneCount, leaveCount); 
        System.out.println(stat);
    }

    /**
     * Prints and processes the first event in eventQueue.
     * 1) Retrieves the first event in eventQueue, prints it <br>
     * 2) Processes the event.
     */
    public static void nextEvent() {
        Event e = eventQueue.peek();
        printEvent(e);
        e.process();
    }

    /**
     * Prints the event and associated information.
     * @param e event to be printed. 
     */
    public static void printEvent(Event e) {
        System.out.println(e); 
    }

    //event adding
    public static void addArriveEvent(double eventTime, Customer c) {
        Event e = new ArriveEvent(eventTime, c);
        eventQueue.add(e);
    }

    public static void addServeEvent(double eventTime, Customer c) {
        Event e = new ServeEvent(eventTime, c);
        eventQueue.add(e);
    }

    public static void addWaitEvent(double eventTime, Customer c) {
        Event e = new WaitEvent(eventTime, c);
        eventQueue.add(e);
    }

    public static void addDoneEvent(double eventTime, Customer c) {
        Event e = new DoneEvent(eventTime, c);
        eventQueue.add(e);
    }

    public static void addLeaveEvent(double eventTime, Customer c) {
        Event e = new LeaveEvent(eventTime, c);
        eventQueue.add(e);
    }

    public static void addServerRestEvent(double eventTime, Server s) {
        Event e = new ServerRestEvent(eventTime, s);
        eventQueue.add(e);
    }

    public static void addServerBackEvent(double eventTime, Server s) {
        Event e = new ServerBackEvent(eventTime, s);
        eventQueue.add(e);
    }
    
    /**
     * Removes the first event from eventQueue.
     * @return the removed event
     */
    public static Event dequeueEvent() {
        return eventQueue.remove();
    }

    //counting statistics
    public static void addDoneCount() {
        doneCount++;
    }

    public static void addLeaveCount() {
        leaveCount++;
    }

    public static void addWaitTime(double waitTime) {
        totalWaitTime += waitTime;
    }
} 
