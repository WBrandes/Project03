
package Simulation;

enum Function
{
    START, 
    STOP, 
    TURN_LEFT, 
    TURN_RIGHT
}



public class Command {
     
    private int time;
    private Function order;
    private boolean isProcessed;
    private boolean delivered;

    public Command(int time, Function order) {
        this.time = time;
        this.order = order;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Function getOrder() {
        return order;
    }

    public void setOrder(Function order) {
        this.order = order;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public boolean isIsProcessed() {
        return isProcessed;
    }

    public void setIsProcessed(boolean isProcessed) {
        this.isProcessed = isProcessed;
    }
    
    
   
    
    
    
}
