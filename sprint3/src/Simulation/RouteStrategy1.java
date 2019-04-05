
package Simulation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

public class RouteStrategy1 implements Route{

    @Override
    public ArrayList<Command> calculateRoute(PriorityQueue<Order> orders) {
        // create commands to simulate the truck
        ArrayList<Command> commands = new ArrayList<>();
        
        
        Iterator<Order> it = orders.iterator();
        while (it.hasNext()) {
            Order ord = (Order) it.next();
            Address destination = ord.getAddress();
            
            int houseNum = destination.getHouseNum();
            int streetNm = destination.getStreetNum();
            
            //truck location is at 510 East 5th Street 
            
            int diffHN = (houseNum - 510);
            int diffSN = (streetNm - 5);
            
            
            // how many blocks for directions X and Y
            int numHouseBlocks = diffHN/100;
            int numStreetBlocks = diffSN;
           
            // truck will move numHouseBlocks blocks left (or right)
            // then numStreetBlocks blocks again left (or right)
            
            for(int i = 0; i<numHouseBlocks; i++){
                Command c = new Command(ord.getTime(), Function.TURN_LEFT);
                if(diffHN>0)
                    c = new Command(ord.getTime(), Function.TURN_RIGHT);
                
                commands.add(c);
                
            }
            
            for(int i = 0; i<numStreetBlocks; i++){
                Command c = new Command(ord.getTime(), Function.TURN_LEFT);
                if(diffSN>0)
                    c = new Command(ord.getTime(), Function.TURN_RIGHT);
                
                commands.add(c);
                
            }
           
            
        }
        
        return commands;
        
    }
    
}
