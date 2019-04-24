package cs2030.simulator;

/**
 * Prints out statistics of the simulation. 
 * This includes: <br>
 * 1) the average waiting time for customers who have been served <br>
 * 2) the number of customers served <br>
 * 3) the number of customers who left without being served
 */             
public class Statistic {
    private double totalWaitTime = 0.0; 
    private double avgWaitTime = 0.0;
    private int doneCount = 0;
    private int leaveCount = 0;
    
    Statistic(double totalWaitTime, int doneCount, int leaveCount) {
        this.totalWaitTime = totalWaitTime;
        this.doneCount = doneCount;
        this.leaveCount = leaveCount;
        if (doneCount != 0) {
            this.avgWaitTime = totalWaitTime / (double) doneCount;
        } 
    }

    @Override 
    public String toString() {
        String result = "[" + String.format("%.03f", this.avgWaitTime) + " " + this.doneCount
                        + " " + this.leaveCount + "]";
        return result;
    }
}
