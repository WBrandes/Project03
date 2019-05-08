package simulation;


import java.util.ArrayList;
import java.util.PriorityQueue;


public class RouteContext {
    
    private Route route;
    
    public RouteContext(Route route){
      this.route = route;
   }
    
    public ArrayList<Command> executeCalculateRoute(PriorityQueue<Order> orders){
      return route.calculateRoute(orders);
   }
}
