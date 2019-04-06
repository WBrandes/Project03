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
    private boolean isOrderProcessced;

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

    public boolean isIsOrderProcessced() {
        return isOrderProcessced;
    }

    public void setIsOrderProcessced(boolean isOrderProcessced) {
        this.isOrderProcessced = isOrderProcessced;
    }
    
    @Override
    public String toString() {
    	
    	return "Command: " + order + ", time: " + time + (isOrderProcessced ? ". An order is being delivered." : ""); 
    	
    }
    
    
    
}
