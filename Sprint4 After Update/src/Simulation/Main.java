

package Simulation;


import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TimerTask;
import javax.swing.JFrame;




public class Main extends TimerTask
{
   
    private static final String FILE = "AddressList100.txt";


    private static final int timeInMiliSeconds = 100;
    private static NeighborhoodGUI neighborhoodGUI;
    private static ArrayList<Order>     listOrders  = new ArrayList<>();
    private static PriorityQueue<Order> orders;
    private static RouteMethod          routeMethod;
    private static ArrayList<Command>   listCommands;
    private static Command              executedCommand;

  

    
    @Override
    public void run() {
        
        if (executedCommand != null)
                {
                   
                    if (listCommands.isEmpty() && executedCommand.getLength() < 1)
                        return;

                   
                    if (executedCommand.getLength() < 1)
                    {
                        executedCommand = listCommands.remove(0);
                        System.out.println(executedCommand);
                        return;
                    }
                    else
                        executedCommand.setLength(executedCommand.getLength() - 1);

                   
                    if (executedCommand.getDirection() == Direction.NONE)
                    {
                       
                        if (executedCommand.getLength() == 4)
                            listOrders.add(orders.poll());
                        return;
                    }

                   
                    Truck truck = neighborhoodGUI.getTruck();
                    
                    truck.proccessCommand(executedCommand);
                    
                    neighborhoodGUI.setTruck(truck);

                    neighborhoodGUI.update(orders, listOrders);
                }
    }
    
    
    
    
    public static void main(String[] args)
    {

       
        
       Route route;
       Scanner scanner = new Scanner(System.in);
       
        Order.writeOrdersWithAddresses(FILE, Address.numAddresses);
       
        orders = Order.readOrdersWithAddresses(FILE);
        
        
        System.out.println("Select Route Method:");
        System.out.println("1. Normal Route");
        System.out.println("2. Right Turn Route");
        System.out.print("Choice> ");
        
        String userChoice = scanner.nextLine();
        
        switch(userChoice){
            case "1":
                route = updateRoute(new NormalRoute());
                break;
            
            case "2":
                route = updateRoute(new RightTurnRoute());
                break;
                
            default:
                route = updateRoute(new NormalRoute());
                break;
        }

        
        neighborhoodGUI = new NeighborhoodGUI(Address.size, Address.blocks, Address.distributionCenter);
        
        GUIObserver guiObserver = new GUIObserver(neighborhoodGUI);
        
        
        
        neighborhoodGUI.setTitle("TRUCK ORDERS");
        neighborhoodGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        neighborhoodGUI.setSize(
                neighborhoodGUI.size + (int) Math.round(neighborhoodGUI.shift + neighborhoodGUI.gridSize/2) + 82, 
                neighborhoodGUI.size + (int) Math.round(neighborhoodGUI.shift + neighborhoodGUI.gridSize/2) + 105
        );
        neighborhoodGUI.setResizable(true);
        neighborhoodGUI.setLocationRelativeTo(null);
        
        System.out.println("Route Distance: " + route.getDistance());


       
        int[] routeTime = convertTime(route.getTime());
        
        
        displayDistance(routeTime);
       
        neighborhoodGUI.update(orders, listOrders);
        neighborhoodGUI.setVisible(true);

       
        if (!listCommands.isEmpty())
            executedCommand = listCommands.remove(0);
        else
        {
            System.out.println("No commands, exit program");
            System.exit(1);
        }
        
        
        java.util.Timer timer = new java.util.Timer(); 
        timer.schedule(new Main(), 0,timeInMiliSeconds);
       
      
    }
    
    
    
    
    
    
    public static void displayDistance(int[] routeTime){
         if(routeTime[0] != 0) {
           
            if(routeTime[0] == 1){
                System.out.println("Route Time = " + routeTime[0] + " Hour(s) " + routeTime[1] + " Minute(s) " + routeTime[2] + " Second(s)");

            }
           
            else {
                System.out.println("Route Time = " + routeTime[0] + " Hour(s) " + routeTime[1] + " Minute(s) " + routeTime[2] + " Second(s)");
            }
        }
       
        else if(routeTime[2] <= 9){
            System.out.println("Route Time = " + routeTime[1] + " Minute(s) " + routeTime[2] + " Second(s)");
        }
        else{
            System.out.println("Route time = " + routeTime[1] + " Minute(s) " + routeTime[2] + " Second(s)");
        }
    }
    
    public static int[] convertTime(int time){
        
        int hrs;
        int mn = time / 60;
        if(mn >= 60){
            hrs = mn / 60;
        }
        else {
            hrs = 0;
        }
        
        int sec = time % 60;
        
        return new int[]{hrs, mn, sec};
    }
    
    public static Route updateRoute(RouteMethod rm)
    {
        routeMethod = rm;
        Route route = routeMethod.calculateRoute(orders, Address.distributionCenter);
        listCommands = route.getCommands();
        return route;
    }
}

