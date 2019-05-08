
package Simulation;

import java.util.*;


public interface Routex {
    public ArrayList<Command> calculateRoute(PriorityQueue<Order> orders);
}
