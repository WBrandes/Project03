package me.willyb.sandwich;

public enum Bread implements Ingredient {
	
	WRAP, WHEAT_ROLL, RYE_ROLL, OAT_ROLL;
	
	public static Double[] costs = {0.5, 0.6, 0.7, 0.5};
	public static Integer[] times = {75, 60, 60, 60};

	@Override
	public double getCost() {
		
		return costs[this.ordinal()];
		
	}
	@Override
	public int getPrepareTime() {
		
		return times[this.ordinal()];
		
	}
	
}
