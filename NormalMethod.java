package Simulation;

import java.util.ArrayList;

public class NormalMethod implements RoutingMethod {
	
	private double currentRouteLocationX;
	private double currentRouteLocationY;
	private int currentDirection;
	private static Direction[] directions = new Direction[] {
			Direction.NORTH, 
			Direction.EAST, 
			Direction.SOUTH, 
			Direction.WEST};
	
	public NormalMethod(int truckFacingDirection, double startLocationX, double startLocationY) {
		
		currentDirection = truckFacingDirection;
		currentRouteLocationX = startLocationX;
		currentRouteLocationY = startLocationY;
		
	}
	
	@Override
	public ArrayList<Command> findPath(double targetLocationX, double targetLocationY) {
				
		ArrayList<Command> commands = new ArrayList<Command>();
		
		ArrayList<Integer> targetList = getDifference(targetLocationX, targetLocationY);
		
		int xDifference = targetList.get(0);
		int yDifference = targetList.get(1);
		
		int xDirectionRequired = targetList.get(2);
		int yDirectionRequired = targetList.get(3);
		
		if(xDifference != 0) {

			if(xDirectionRequired == currentDirection) {
				
				commands.add(new Command(Math.abs(xDifference) / 10, Function.START));
				currentRouteLocationX += xDifference;

				
			} else {
				
				commands.addAll(faceDirection(xDirectionRequired));

				targetList = getDifference(targetLocationX, targetLocationY);
				xDifference = targetList.get(0);
				yDifference = targetList.get(1);
				xDirectionRequired = targetList.get(2);
				yDirectionRequired = targetList.get(3);
				
				if(xDifference != 0) {
					commands.add(new Command(Math.abs(xDifference) / 10, Function.START));
					currentRouteLocationX += xDifference;

				}
				
				targetList = getDifference(targetLocationX, targetLocationY);
				xDifference = targetList.get(0);
				yDifference = targetList.get(1);
				xDirectionRequired = targetList.get(2);
				yDirectionRequired = targetList.get(3);
				
				commands.addAll(faceDirection(yDirectionRequired));
				
				if(yDifference != 0) {
					commands.add(new Command(Math.abs(yDifference) / 10, Function.START));
					currentRouteLocationY += yDifference;

				}
				
			}
						
		} else if(yDifference != 0) {
			
			
			if(yDirectionRequired == currentDirection) {
				
				commands.add(new Command(Math.abs(yDifference) / 10, Function.START));
				currentRouteLocationY += yDifference;
				
			} else {
				
				commands.addAll(faceDirection(yDirectionRequired));

				targetList = getDifference(targetLocationX, targetLocationY);
				xDifference = targetList.get(0);
				yDifference = targetList.get(1);
				xDirectionRequired = targetList.get(2);
				yDirectionRequired = targetList.get(3);
				
				if(yDifference != 0) {
					
					commands.add(new Command(Math.abs(yDifference) / 10, Function.START));
					currentRouteLocationY += yDifference;

				}

				commands.addAll(faceDirection(yDirectionRequired));
				
				targetList = getDifference(targetLocationX, targetLocationY);
				xDifference = targetList.get(0);
				yDifference = targetList.get(1);
				xDirectionRequired = targetList.get(2);
				yDirectionRequired = targetList.get(3);
				
				if(xDifference != 0) {
					commands.add(new Command(Math.abs(xDifference) / 10, Function.START));
					currentRouteLocationX += xDifference;

				}
				
			}
						
		}
			
//		commands.add("Stop");
				
		return commands;
		
	}
	
	/**
	 * Generates commands for getting the truck to face another direction. Assumes the truck is at an intersection
	 * @param truckFacingDirection
	 * @param newDirection
	 * @param startLocationX
	 * @param startLocationY
	 * @param endLocationX
	 * @param endLocationY
	 * @return
	 */
	public ArrayList<Command> faceDirection(int newDirection) {
		
		ArrayList<Command> commands = new ArrayList<Command>();
		/*
		 * N, E, S, W
		 * 0, 1, 2, 3
		 */
		int directionCalculation = ((currentDirection - newDirection) % 2);

		boolean isRightTurn = directionCalculation <= 0;
		if(currentDirection == 3 && newDirection == 0) {
			
			isRightTurn = true;
			
		} else if(currentDirection == 0 && newDirection == 3) {
			
			isRightTurn = false;
			
		}
		
		Function turn = Function.TURN_LEFT;
		int turnTime = 4;
		
		if(isRightTurn) {
			
			turn = Function.TURN_RIGHT;
			turnTime = 2;
			
		}
		commands.add(new Command(turnTime, turn));
		
		int currentDirectionSave = currentDirection;
		currentDirection = calculateTurn(currentDirection, isRightTurn);

		
		//If both directions have the same parity, the new direction is directly behind our current one, and we need to turn again.
		if(currentDirectionSave % 2 == newDirection % 2) {

			commands.add(new Command(10, Function.START));
			
			
			int xMovement = ((currentDirection - 2) * -1) % 2;
			int yMovement = (((currentDirection - 1))) % 2;
			
			currentRouteLocationX += xMovement*100;
			currentRouteLocationY += yMovement*100;
			
			
			commands.add(new Command(turnTime, turn));

			currentDirection = calculateTurn(currentDirection, isRightTurn);
						
		}
		
		return commands;
		
	}
	
	public int calculateTurn(int initial, boolean rightTurn) {
		
		if(rightTurn) {
			
			initial = (initial + 1) % 4;
			
		} else {
			
			initial = (initial - 1);
			if(initial < 0) {
				
				initial = 3;
				
			}
			
		}
		
		return initial;
		
	}
	
	public ArrayList<Integer> getDifference(double targetLocationX, double targetLocationY) {
				
		ArrayList<Integer> results = new ArrayList<Integer>();
		
		double xDifference = targetLocationX - currentRouteLocationX;
		double yDifference =  targetLocationY - currentRouteLocationY;
		
		int xDirectionRequired = 1;
		int yDirectionRequired = 0;
		
		if(xDifference < 0) {
			
			xDirectionRequired = 3;
			
		} else if(yDifference > 0) {
			
			yDirectionRequired = 2;
			
		}
		
		results.add((int) xDifference);
		results.add((int) yDifference);
		results.add(xDirectionRequired);
		results.add(yDirectionRequired);
		
		return results;
		
	}
	
}
