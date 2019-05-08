package me.willyb.main;

public class NormalTurnMethod extends TurnMethod {

	@Override
	public void faceDirection(Router r, int newDirection) {
		
		int difference = r.direction - newDirection;
		
		//This will be true if the two directions are adjacent; that is, if they are 1 apart from eachother in the direction listing, or if they are North and West, the beginning and end of the listing, which wrap around and thus have a difference of 3 between them.
		if(Math.abs(difference) == 1 || Math.abs(difference) == 3) {
			
			//If our turn loops around the direction listing, we need to flip its negativity, as the case where we wrap around the list has the opposite negativity it "should" have, in relation to turning left or right
			if(Math.abs(difference) == 3) {
				
				difference *= -1;
				
			}
			
			//If our difference is positive, turn left; if it's negative, turn right.
			if(difference > 0) {
				
				r.currentRouteCommands.add(new Command(-1000, true, true, Function.TURN_LEFT));
				
			} else {
				
				r.currentRouteCommands.add(new Command(-1000, true, true, Function.TURN_RIGHT));
				
			}
			
			r.direction = newDirection;
			
		/*
		 * If the required direction is directly behind our current one, turn right, move one block forward,
		 * turn right again.
		 */
		} else {
			
			r.direction++;
			r.direction = r.direction % 4; //Interestingly, although things would be strange, this line actually probably isn't needed... but, better to leave it in I think, juuuust in case, ya know.
			
			r.currentRouteCommands.add(new Command(-1000, true, true, Function.TURN_RIGHT));
			moveForwardOneBlock(r);
			r.currentRouteCommands.add(new Command(-1000, true, true, Function.TURN_RIGHT));
			
			r.direction++;
			r.direction = r.direction % 4;
			
		}
		
	}

}
