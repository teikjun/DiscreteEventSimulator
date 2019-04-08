package cs2030.simulator;

public enum CustomerState {
    ARRIVES("arrives"),
    SERVED("served"),
    WAITS("waits"),
    LEAVES("leaves"),
    DONE("done");
    
    private String customerString = "";
    
    CustomerState(String customerString) { 
        this.customerString = customerString;
    } 
    
    public String getCustomerString() {
        return this.customerString;
    }
}
