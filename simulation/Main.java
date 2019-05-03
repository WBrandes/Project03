


import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Timer;
import java.util.TimerTask;


public class Main extends TimerTask
{
    
    static int commandTime = 0;
    static int tick = 0;
    static int orderTime = 0;
    static final int timeInMiliSeconds = 10;
    public ArrayList<Command> truckCommands ;
    
    static int current = 0;

    public static void main(String[] args)
    {
            	
        /*
        * the timer here is from java util and NOT from swing library,
        * the timer used in neiberhood is when listening to an action listener, but the action is not being used
        * it is just runing a block of code for certain time,
        * swing time used when listening to an action
        * in here we are using timer 
        */
        // run method runs each timeInMiliSeconds
        Timer timer = new Timer(); 
        timer.schedule(new Main(), 0,timeInMiliSeconds);
        
        
        // list of truck commands to find
        ArrayList<Command> truckCommands = new ArrayList<>();
        

        // Draw the neighborhood with the addresses and distribution center shown
        // neighborhood.printNeighborhood();

        
        Trucks trucks = new Trucks();
        Orders orders = new Orders();
        
        // attach  all trucks used
//        trucks.register(neighborhood);
//        orders.register(neighborhood);
        
        trucks.notifyObservers(new Message("Truck started"));

        
    }

    @Override
    public void run() {
        
    	Neighborhood neighborhood = Neighborhood.getNeighborhood();
    	Truck truck = neighborhood.truck;
      
        Command command;
        Order order = null;
        
        truck.isMoving(true);
        
        tick++;
        
        
        if(truck.isMoving()){
           
            if(tick == 10){
                tick = 0;
          
            if(commandTime==0){
               command  = truckCommands.get(current);
               
             
               truck.proccessCommand(command);
             commandTime = command.getTime();
             
             if(command.isIsProcessed()){
                 orderTime = 5;
             }
            
             
             if(orderTime >= 0)
                 orderTime--;
             
             
             if(orderTime == 0){
                 // get first order that has not procssed yet: processed is false
                 
                 while(neighborhood.itr.hasNext()){
                      order = (Order) neighborhood.itr.next();
                     if(!order.isProcessed()) break;
                 }
                 order.setProcessed(true);
                 
                 if(command.isDelivered())
                     order.setIsDelivered(true);
             }
             
            }
             

            }
        
        
        
        
            Direction direction = truck.getDirection();
            
            switch (direction) {
                case NORTH:
                	truck.setY(truck.getY() - 1);
                    break;
                case EAST:
                	truck.setX(truck.getX() + 1);
                    break;
                case SOUTH:
                	truck.setY(truck.getY() + 1);
                    break;
                case WEST:
                	truck.setX(truck.getX() - 1);
                    break;
                default:
                    break;
            }
        }
        
        
        
    }

}

