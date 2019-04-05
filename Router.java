package Simulation;

import java.util.ArrayList;
import java.util.PriorityQueue;

public interface Router {
	
	//Will be ArrayList<Command> and (Truck t, PriorityQueue<Order> orders) once other code is added.
	public ArrayList<Command> calculateRoute(Truck t, PriorityQueue<Order> orders);
	
}
