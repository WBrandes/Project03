package me.willyb.main;

public class RightTurnMethod extends TurnMethod {

	@Override
	public void faceDirection(Router r, int newDirection) {
		
		int difference = r.direction - newDirection;
		
		//If turning to the new direction requires only one right turn, then just make one right turn.
		if(difference == -1 || difference == 3) {
			
			r.currentRouteCommands.add(new Command(-1000, true, true, Function.TURN_RIGHT));
			r.direction = newDirection;
			
		//If the new direction is directly behind our current one, make two right turns to get to it, driving part way around a block.
		} else if(Math.abs(difference) == 2) {
			
			r.direction++;
			r.direction = r.direction % 4; //Interestingly, although things would be strange, this line actually probably isn't needed... but, better to leave it in I think, juuuust in case, ya know.
			
			r.currentRouteCommands.add(new Command(-1000, true, true, Function.TURN_RIGHT));
			moveForwardOneBlock(r);
			r.currentRouteCommands.add(new Command(-1000, true, true, Function.TURN_RIGHT));
			
			r.direction++;
			r.direction = r.direction % 4;
			
		//If the new direction we want to face is to our left, then turn right 3 times, driving almost all the way around a block, to face the new direction.
		} else {
			
			r.direction++;
			r.direction = r.direction % 4; //Interestingly, although things would be strange, this line actually probably isn't needed... but, better to leave it in I think, juuuust in case, ya know.
			r.currentRouteCommands.add(new Command(-1000, true, true, Function.TURN_RIGHT));
			
			moveForwardOneBlock(r);
			
			r.direction++;
			r.direction = r.direction % 4;
			r.currentRouteCommands.add(new Command(-1000, true, true, Function.TURN_RIGHT));
			
			moveForwardOneBlock(r);
			
			r.direction++;
			r.direction = r.direction % 4;
			r.currentRouteCommands.add(new Command(-1000, true, true, Function.TURN_RIGHT));
			
		}
		
	}

}
