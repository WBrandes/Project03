
package Simulation;

import java.util.ArrayList;
import java.util.PriorityQueue;

public interface Route
{
    
    public void handleUTurn(Address truckLocation, Address houseLocation, String directionOfTravel, boolean sameDirection);
    

}
