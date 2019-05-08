
package Simulation;

import java.util.ArrayList;
import java.util.PriorityQueue;


public class RouteContext {
    
    private Routex route;
    
    public RouteContext(Routex route){
      this.route = route;
   }
    
    public ArrayList<Command> executeCalculateRoute(PriorityQueue<Order> orders){
      return route.calculateRoute(orders);
   }
}
