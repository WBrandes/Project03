package Simulation;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Timer;
import java.util.TimerTask;


public class Main extends TimerTask
{
    public static final String FILE = "AddressList100.txt";
    
    static int commandTime = 0;
    static int tick = 0;
    static int orderTime = 0;
    static final int timeInMiliSeconds = 10;
    
    static int current = 0;
    static Truck newTruck ;
    static ArrayList<Command> truckCommands ;
    static PriorityQueue<Order> orders;
    static Iterator itr ;

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
        newTruck = new Truck(200.0, 200.0);
        
                
        // Write 100 random addresses to a file
        //AddressIO.writeAddresses(FILE, 100);

        // Read the addresses from the file and place them in a PriorityQueue
        PriorityQueue<Address> addresses = AddressIO.readAddresses(FILE);
        
        
        // place orderes for each address
        orders = new PriorityQueue<>();
        itr = orders.iterator();
        
        Iterator it = addresses.iterator();
        while (it.hasNext()) {
            Address addr = (Address) it.next();
            
            Order newOrder = new Order(addr);
            orders.add(newOrder);
        }
        
        // list of truck commands to find
        ArrayList<Command> truckCommands = new ArrayList<>();
        

        // Draw the neighborhood with the addresses and distribution center shown
        Neighborhood neighborhood = new Neighborhood();
        neighborhood.generateNeighborhood(addresses);
        // neighborhood.printNeighborhood();

        // Put the addresses into a list
        Iterator<Address> iterator = addresses.iterator();
        ArrayList<Address> addressList = new ArrayList<>();
        while (iterator.hasNext())
            addressList.add(iterator.next());
        // Add the distribution center as the final destination
        addressList.add(new Address(Address.DISTRIBUTION_X, Address.SOUTH, Address.DISTRIBUTION_Y, 1900));

        // Create the route the truck will follow and calculate the length of the trip
        
        try
        {
            neighborhood.addRoute(addressList);
        }
        catch (UTurnException ute)
        {
            System.out.println("UTurnException occurred.");
        }
        System.out.println("Route length: " + neighborhood.getRouteLength());
        
        Neighborhood.drawNeighborhood(addresses);
    }

    @Override
    public void run() {
        
      
        Command command;
        Order order = null;
        
        newTruck.isMoving(true);
        
        tick++;
        
        
        if(newTruck.isMoving()){
           
            if(tick == 10){
                tick = 0;
          
            if(commandTime==0){
               command  = truckCommands.get(current);
               
             
             newTruck.proccessCommand(command);
             commandTime = command.getTime();
             
             if(command.isIsProcessed()){
                 orderTime = 5;
             }
            
             
             if(orderTime >= 0)
                 orderTime--;
             
             
             if(orderTime == 0){
                 // get first order that has not procssed yet: processed is false
                 
                 while(itr.hasNext()){
                      order = (Order) itr.next();
                     if(!order.isProcessed()) break;
                 }
                 order.setProcessed(true);
                 
                 if(command.isDelivered())
                     order.setIsDelivered(true);
             }
             
            }
             

            }
        
        
        
        
            Direction direction = newTruck.getDirection();
            
            switch (direction) {
                case NORTH:
                    newTruck.setY(newTruck.getY() - 1);
                    break;
                case EAST:
                    newTruck.setX(newTruck.getX() + 1);
                    break;
                case SOUTH:
                    newTruck.setY(newTruck.getY() + 1);
                    break;
                case WEST:
                    newTruck.setX(newTruck.getX() - 1);
                    break;
                default:
                    break;
            }
        }
        
        
        
    }

}

