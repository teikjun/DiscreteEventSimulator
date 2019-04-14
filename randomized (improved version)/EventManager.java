package cs2030.simulator;
import java.util.PriorityQueue;

public class EventManager {
    /** 
     * eq is a priority queue of events
     */
    private static PriorityQueue<Event> eq = new PriorityQueue<>(new EventComparator());
    private static double totalWaitTime = 0;
    private static int doneCount = 0;
    private static int leaveCount = 0;
    /**
     * 1) While eq is not empty, call nextEvent <br>
     * 2) Print statistics <br>
     */
    public static void allEvents() {
        while (!eq.isEmpty()) { 
            nextEvent();
        }
        Statistic stat = new Statistic(totalWaitTime, doneCount, leaveCount); 
        System.out.println(stat);
    }
    /**
     * 1) Retrieves the first event, prints it <br>
     * 2) Processes the event <br>
     */
    public static void nextEvent() {
        Event e = eq.peek();
        printEvent(e);
        e.process();
    }
    public static void printEvent(Event e) {
        System.out.println(e); 
    }

    public static void addArriveEvent(double eventTime, Customer c) {
        Event e = new ArriveEvent(eventTime, c);
        eq.add(e);
    }
    public static void addServeEvent(double eventTime, Customer c) {
        Event e = new ServeEvent(eventTime, c);
        eq.add(e);
    }
    public static void addWaitEvent(double eventTime, Customer c) {
        Event e = new WaitEvent(eventTime, c);
        eq.add(e);
    }
    public static void addDoneEvent(double eventTime, Customer c) {
        Event e = new DoneEvent(eventTime, c);
        eq.add(e);
    }
    public static void addLeaveEvent(double eventTime, Customer c) {
        Event e = new LeaveEvent(eventTime, c);
        eq.add(e);
    }
    public static PriorityQueue<Event> getEQ() {
        return eq;
    }
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
