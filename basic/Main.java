import cs2030.simulator.Simulator;
import java.util.Scanner;

class Main {
    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in); 
        int id = 0;
        int numOfServers = sc.nextInt();
        Simulator.addServers(numOfServers);
        while (sc.hasNextDouble()) {
            Double arrivalTime = sc.nextDouble();
            id++; 
            Simulator.addEvent(id, arrivalTime);
        }
        Simulator.simulate();
   }
}
