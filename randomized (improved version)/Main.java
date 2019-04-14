import cs2030.simulator.Simulator;
import java.util.Scanner;

class Main {
    /**
     * Input given as seed value, number of servers, number of customers,
     * arrival rate (lambda), service rate (mu)
     */
    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in); 
        int id = 0;
        int seed = sc.nextInt();
        int numOfServers = sc.nextInt();
        int numOfCustomers = sc.nextInt();
        double arrivalRate = sc.nextDouble();
        double serviceRate = sc.nextDouble();
        Simulator.addServers(numOfServers);
        Simulator.generateEvents(numOfCustomers, seed, arrivalRate, serviceRate);
        Simulator.simulate();
   }
}

