package me.willyb.neighborhood;

import java.util.ArrayList;

import me.willyb.main.Command;
import me.willyb.main.Function;
import me.willyb.main.Main;
import me.willyb.main.NormalTurnMethod;
import me.willyb.main.Router;
import me.willyb.main.TurnMethod;

public class Truck {
	
	//Truck's speed, in miles per hour
	public double mph = 30.0;
	//Whether to move up, down, or not all for given direction. Each inner-list represents X and Y, and the main list is indexed with direction numbers
	public static int[][] movementMatrix = {{0,-1}, {1,0}, {0,1}, {-1,0}};
	
	public double x;
	public double y;
	
	public boolean hasLeftDistributionCenter = true;
	
	public boolean moving = false;
	
	//0 = North, 1 = East, 2 = South, 3 = West
	public int direction;
	
	//The speed, in addresses per second, of the truck. 
	public double addressSpeed;
	
	public int pause = 0;
	
	public static final int LEFT_TURN_TIME = 240;
	public static final int RIGHT_TURN_TIME = 120;
	public static final int DELIVERY_TIME = 300;
	
	public Order currentOrder;
	public int prepareCount = 0;
	
	//Whether or not the truck is currently preparing, has prepared, or is delivering an order
	public boolean preparing = false;
	public boolean prepared = false;
	public boolean delivering = false;
	
	public double totalDistanceTravelled = 0.0;
	
	public ArrayList<Command> commands = new ArrayList<Command>();
	
	public TurnMethod turnMethod;
	
	public Truck() {
		
		x = 500;
		y = 510;
		direction = 0;
		
		addressSpeed = (mph / 3600) / Neighborhood.addressDistance;
		
		turnMethod = new NormalTurnMethod();
		
	}
	
	public Truck(TurnMethod method) {
		
		x = 500;
		y = 510;
		direction = 0;
		
		addressSpeed = (mph / 3600) / Neighborhood.addressDistance;
		
		turnMethod = method;
		
	}
	
	public Truck(double x, double y, int direction, TurnMethod method) {
		
		this.x = x;
		this.y = y;
		this.direction = direction;
		
		addressSpeed = (mph / 3600) / Neighborhood.addressDistance;
		
		turnMethod = method;
		
	}
	
	/**
	 * Main method for the simulation. When run() is called, the truck will move if it should,
	 * execute a command if it has any, work on preparing or delivering an order, and ask for a new
	 * order if it's delivered its current one. If it has no order, it will route itself to the distribution center.
	 */
	public void run() {
		
		Neighborhood neighborhood = Neighborhood.getNeighborhood();
		
		//Only get a new order / commands if we're not currently delivering an order
		if(!delivering) {
			
			//Get an order if we don't have one
			if(currentOrder == null) {
				
				currentOrder = neighborhood.requestTopOrder();
				
			}
		
			//If we have no commands currently, generate new ones.
			if(commands.isEmpty()) {
				
				//If we don't have an order...
				if(currentOrder == null) {
					
					double xDif = x - neighborhood.distributionCenter.x;
					double yDif = y - neighborhood.distributionCenter.y;
					
					boolean xAligned = Math.abs(xDif) < 0.5;
					boolean yAligned = Math.abs(yDif) < 0.5;
					//... and aren't at the distribution center already...
					if(!xAligned || !yAligned) {
						
						//... then generate commands to route to the distribution center
						findRouteTo(neighborhood.distributionCenter);
						
					} else if (hasLeftDistributionCenter) {
						
						//If we're at the distribution center, broadcast a message saying so. hasLeftDistributionCenter exists so that this is only broadcast once per-return.
						hasLeftDistributionCenter = false;
						Main.messenger.notifyListenersTruck("The Truck has returned to the Distribution Center. Distance travelled so far today: " + Double.toString(totalDistanceTravelled) + " miles.");
						
					}
				
				//If we do have an order, then generate commands to get to it.
				} else {
					
					findRouteTo(currentOrder.destination);
					
					if(!hasLeftDistributionCenter) {
						
						hasLeftDistributionCenter = true;
						
					}
					
				}
				
			}
			
			//If we have any commands to process, try processing one.
			if(!commands.isEmpty()) {
				
				tryProcessCurrentCommand();
			
			}
			
		}
		
		//If we're paused (due to delay time of turning or delivering) do nothing, but decrease pause timer.
		if(pause > 0) {
			
			pause--;
			return;
			
		}
		
		//If we're preparing an order, decrease the countdown for that order being prepared.
		if(preparing) {
			
			prepareCount++;
			if(prepareCount >= currentOrder.prepareTime) {
				
				preparing = false;
				prepareCount = 0;
				prepared = true;
				
			}
			
		}
		
		//If we're delivering, and the order is prepared, deliver. Update the listeners that the new address has been delivered to, and set state to be not delivering or preparing
		//Also, set order to null so that on the next run we'll get a new one if there are any.
		if(delivering) {
			
			if(prepared) {
				
				preparing = false;
				delivering = false;
				prepared = false;
				ArrayList<Order> completedOrders = new ArrayList<Order>();
				completedOrders.add(currentOrder);
				Main.messenger.notifyListenersOrders(completedOrders, true);
				currentOrder = null;
				
			}
			
		}
		
		//Move if we should be.
		if(moving) {
			
			x += addressSpeed * movementMatrix[direction][0];
			y += addressSpeed * movementMatrix[direction][1];
			
		}
		
	}
	
	/**
	 * Tries to process the first command in this Truck's commands ArrayList.
	 */
	public void tryProcessCurrentCommand() {
		
		Command currentCommand = commands.get(0);
		
		//Getting which dimension we should be checking against for this command. Commands are executed if the Truck crosses a threshold in the x or y dimensions.
		double dimensionToThreshold = x;
		if(!currentCommand.xAxis) {
			
			dimensionToThreshold = y;
			
		}
		
		//Whether or not we should execute the command, based on whether or not we've crossed the execution threshold of the command.
		boolean executeCommand = currentCommand.above == (dimensionToThreshold >= currentCommand.whereToExecute);
		
		//Executing command if we should.
		if(executeCommand) {
			
			switch(currentCommand.function) {
				
				case STOP:
					Main.messenger.notifyListenersTruck("Truck Stopped Moving.");
					moving = false;
					break;
				case START:
					Main.messenger.notifyListenersTruck("Truck Started Moving.");
					moving = true;
					break;
				case TURN_LEFT:
					Main.messenger.notifyListenersTruck("Truck Turned Left.");
					pause = LEFT_TURN_TIME;
					turn(true);
					break;
				case TURN_RIGHT:
					Main.messenger.notifyListenersTruck("Truck Turned Right.");
					pause = RIGHT_TURN_TIME;
					turn(false);
					break;
				case PREPARE:
					Main.messenger.notifyListenersTruck("Truck Began Preparing an Order.");
					preparing = true;
					break;
				case DELIVER:
					Main.messenger.notifyListenersTruck("Truck Is Delivering An Order.");
					pause = DELIVERY_TIME;
					moving = false;
					delivering = true;
					preparing = true;
					break;
				default:
					break;
					
			}
			
			commands.remove(0);
			
		}
		
	}
	
	/**
	 * Adds commands to this Truck's commands ArrayList, which when carried out will bring it to the given address, and deliver its current order there.
	 * @param a The address to generate route to.
	 */
	public void findRouteTo(Address a) {
		
		Router router = new Router(this.x, this.y, this.direction, this.turnMethod);
		
		//Generating a route.
		commands.addAll(router.findRouteToAddress(a));
		
		/* If we have an order, go backwards through the list of distances the router keeps.
		 * The router stores the total distance the truck will travel, remembering what distance the truck will need to travel to reach certain commands.
		 * Going backward through these stored distances, we find the index where the remaining distance needed to finish the route is equal to or greater than the distance the truck will travel in the amount of time it takes to prepare its current order.
		 * Once we find this index, add a command to start preparing the order there.
		 * This should line up the time that the order finishes fairly closely with when it is delivered.
		 */
		if(currentOrder != null) {
			
			for(int i = (router.totalDistanceRecord.size() - 1); i>=0; i--) {
				
				double distance = router.totalDistanceRecord.get(i);
				
				//If the distance left in the route is greater than or equal to the approximate distance we'll cover in the meal prep time (prep time multiplied by miles per second of truck), then start preparing the order, so that it will be done close to when the truck will reach the destination.
				//Or if we get through the entire list, start preparing it
				if(((Math.abs(router.totalDistance - distance) / 10) * Neighborhood.addressDistance >= (currentOrder.prepareTime * (mph / 3600.0))) || (i == 0)) {
					
					commands.add(i*2, new Command(-1000, true, true, Function.PREPARE));
					break;
					
				}
				
			}
			
			commands.add(new Command(-1000, true, true, Function.DELIVER));
			
		}
		
		//Adding route length to total distance travelled, after converting to miles.
		totalDistanceTravelled += (router.totalDistance / 10) * Neighborhood.addressDistance;
		
		//Debug code.
/*		System.out.println("Final route calculated:");
		
		for(Command c : commands) {
			
			System.out.println(c.toString());
			
		}
		System.out.println();*/
		
	}
	
	/**
	 * Turns the truck right or left.
	 * @param left Whether to turn left. If true, will turn the truck left; if false, will turn the truck right.
	 */
	public void turn(boolean left) {
		
		if(left) {
			
			direction--;
			if(direction < 0) {
				
				direction = 3;
				
			}
			
		} else {
			
			direction++;
			direction = direction % 4;
			
		}
		
	}
	
}
