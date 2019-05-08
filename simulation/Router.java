package simulation;


import java.util.PriorityQueue;


public interface Router {
   public double calculateRoute(Truck t, PriorityQueue<Order> orders);
}
