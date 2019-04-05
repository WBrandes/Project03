package Simulation;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class NormalRouter implements Router{

	@Override
	public ArrayList<Command> calculateRoute(Truck t, PriorityQueue<Order> orders) {
		
		ArrayList<Command> commands = new ArrayList<Command>();
		PriorityQueue<Order> ordersCopy = new PriorityQueue<Order>();
		ordersCopy.addAll(orders);
		
		while(!ordersCopy.isEmpty()) {
			
			Order firstOrder = ordersCopy.poll();
			if(firstOrder != null) {
				
				int direction = 0;
				
				switch(t.getDirection()) {
					
					case EAST:
						direction = 1;
						break;
					case SOUTH:
						direction = 2;
						break;
					case WEST:
						direction = 3;
						break;
					default:
						break;
					
				}
				
				NormalMethod router = new NormalMethod(direction, t.getX(), t.getY());
				
				commands.addAll(router.findPath(firstOrder.getAddress().getHouseNum(), firstOrder.getAddress().getStreetNum()));
				Command deliverCommand = new Command(5, Function.STOP);
				deliverCommand.setIsOrderProcessced(true);
				commands.add(deliverCommand);
			}
			
		}
		
		return commands;
	}

}
