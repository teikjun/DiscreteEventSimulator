package cs2030.simulator;

/**
 * Various states that the customer can hold throughout the simulation.
 */
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

    /**
     * Getter method for customerString. 
     * @return customerString, a string associated with the customer state, used for print output 
     */
    public String getCustomerString() {
        return this.customerString;
    }
}
