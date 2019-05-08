package me.willyb.main;


public abstract class TurnMethod {
	
	public abstract void faceDirection(Router r, int newDirection);
	
	/**
	 * Moves a Router forward one block in the direction it's currently facing.
	 * @param r The Router to move.
	 */
	public void moveForwardOneBlock(Router r) {
		
		boolean xAxis = r.direction % 2 != 0;
		boolean above = xAxis ? r.direction == 1 : r.direction == 2;
		
		double newLocation = xAxis ? r.x : r.y;
		
		if(above) {
			
			newLocation += 100.0;
			
		} else {
			
			newLocation -= 100.0;
			
		}
					
		if(xAxis) {
			
			r.totalDistance += Math.abs(newLocation - r.x);
			r.x = newLocation;
			r.totalDistanceRecord.add(r.totalDistance);
			r.totalDistanceRecord.add(r.totalDistance);
			
		} else {
			
			r.totalDistance += Math.abs(newLocation - r.y);
			r.y = newLocation;
			r.totalDistanceRecord.add(r.totalDistance);
			r.totalDistanceRecord.add(r.totalDistance);
			
		}
		
		r.currentRouteCommands.add(new Command(-1000, true, true, Function.START));
		r.currentRouteCommands.add(new Command(newLocation, xAxis, above, Function.STOP));
		
	}
	
}
