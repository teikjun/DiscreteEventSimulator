package cs2030.simulator;
import java.util.PriorityQueue;

public class EventManager {
    private static PriorityQueue<Event> eq = new PriorityQueue<>(new EventComparator());
    private static double totalWaitTime = 0;
    private static int doneCount = 0;
    private static int leaveCount = 0;
   
    public static void addEvent(int id, double arrivalTime) {
        Customer c = new Customer(id, arrivalTime);
        addArriveEvent(arrivalTime, c);
    }
    public static void allEvents() {
        while (!eq.isEmpty()) { 
            nextEvent();
        }
        Statistic stat = new Statistic(totalWaitTime, doneCount, leaveCount); 
        System.out.println(stat);
    }
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
    /*public static void nextEvent() {
        Event e = eq.peek();
        printEvent(e);
        switch (c.getState()) {
            case ARRIVES:
                allocateServer(c);
                break;
            case SERVED:
                c.setEventTime(c.getEventTime() + Server.SERVETIME);   
                c.setState(CustomerState.DONE);
                eq.add(eq.poll());
                break;
            case WAITS: 
                Server myServer = c.getMyServer();
                double customerWaitTime = myServer.getNextWaitFree() - c.getArrivalTime();
                totalWaitTime += customerWaitTime;
                c.setEventTime(myServer.getNextWaitFree());
                c.setState(CustomerState.SERVED);
                eq.add(eq.poll());
                break;
            case LEAVES:
                eq.remove(c);
                leaveCount++;
                break;
            case DONE:
                eq.remove(c);
                doneCount++;
                break;
            default: 
                break;
        }
    }*/
  
} 
