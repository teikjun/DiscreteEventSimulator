package cs2030.simulator;

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
        String result = "[" + String.format("%.03f", this.avgWaitTime) + " " + this.doneCount + " " + this.leaveCount + "]";
        return result;
    }
}
