package me.willyb.main;

public class Command {
	
	//The threshold for executing this command; this is a value that corresponds to a position in the neighborhood.
	public double whereToExecute;
	
	//Whether or not the above threshold is on the x axis or y axis
	public boolean xAxis;
	
	//Whether or not to execute the command when we are greater than or equal to the threshold, or less than or equal to the threshold
	public boolean above;
	
	//The function to execute when the threshold is reached.
	public Function function;
	
	public Command(double whereToExecute, boolean xAxis, boolean above, Function function) {
		
		this.whereToExecute = whereToExecute;
		this.xAxis = xAxis;
		this.above = above;
		this.function = function;
		
	}
	
	@Override
	public String toString() {
		
		return "Execute " + (above ? "above or at " : "below or at ") + Double.toString(whereToExecute) + (xAxis ? "x" : "y") + ", Command: " + function.toString();
		
	}
	
	
}
