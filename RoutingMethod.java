

import java.util.ArrayList;

public interface RoutingMethod {
    
    public ArrayList<Command> findPath(
            Direction truckFacingDirection, 
            double startLocationX, 
            double startLocationY, 
            double endLocationX, 
            double endLocationY);
}
