import cs2030.simulator.Simulator;
import java.util.Scanner;

/**
 * Main is the driver class for Discrete Event Simulator.
 * Handles only input and output, the input is passed to {@link Simulator} class.
 */
class Main {
    /**
     * The main method for Discrete Event Simulator, handles input and output.
     * Input given as seed value, number of human servers, 
     * number of self-checkout counter servers, maximum queue length
     * number of customers, arrival rate (lambda), service rate (mu),
     * server resting rate (for random generator to determine rest period),
     * server rest probability, greedy customer probability
     * @param args argument for main
     */
    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in); 
        int seed = sc.nextInt();
        int numOfHumanServers = sc.nextInt();
        int numOfCounterServers = sc.nextInt();
        int maxQueueLength = sc.nextInt();
        int numOfCustomers = sc.nextInt();
        double arrivalRate = sc.nextDouble();
        double serviceRate = sc.nextDouble();
        double restingRate = sc.nextDouble();
        double restProbability = sc.nextDouble();
        double greedyProbability = sc.nextDouble();
        Simulator.simulate(seed, numOfHumanServers, numOfCounterServers, maxQueueLength, 
                numOfCustomers, arrivalRate, serviceRate, restingRate, restProbability,
                greedyProbability);
        sc.close();
    }
}

