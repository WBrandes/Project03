package me.willyb.sandwich;

public enum Condiment implements Ingredient {
	
	MAYO, MUSTARD, CHEESE, SAUERKRAUT, PEPPER;
	
	public static Double[] costs = {0.25, 0.25, 0.75, 1.0, 0.5};
	public static Integer[] times = {30, 30, 40, 45, 30};

	@Override
	public double getCost() {
		
		return costs[this.ordinal()];
		
	}
	@Override
	public int getPrepareTime() {
		
		return times[this.ordinal()];
		
	}
	
}
