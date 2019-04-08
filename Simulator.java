package cs2030.simulator;
import java.util.ArrayList;

public class Simulator { 
    private static ArrayList<Server> serverList = new ArrayList<Server>();
    private static int numOfServers = 0;
      
    public static void addServers(int numOfServers) {
        Simulator.numOfServers = numOfServers;
        int n = numOfServers;
        for (int serverNum = 1; n > 0; serverNum++) {
            Server s = new Server(serverNum);
            serverList.add(s);
            n--;
        }
    }
        
    public static void addEvent(int id, double arrivalTime) {
        EventManager.addEvent(id, arrivalTime);
    }

    public static void simulate() {
        EventManager.allEvents();
    }

    public static void allocateServer(Customer c) {
        for (int i = 0; (c.getMyServer() == null) && (i < numOfServers); i++) {
            serverList.get(i).serveNow(c);
        }
        for (int j = 0; (c.getMyServer() == null) && (j < numOfServers); j++) {
            serverList.get(j).serveLater(c);
        } 
        if (c.getMyServer() == null) {
            EventManager.getEQ().poll();
            EventManager.addLeaveEvent(c.getArrivalTime(), c);
        } 
    }

}
