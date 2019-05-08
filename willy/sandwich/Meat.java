package me.willyb.sandwich;

public enum Meat implements Ingredient {
	
	HAM, TURKEY, ROAST_BEEF, TUNA, CHICKEN;
	
	public static Double[] costs = {1.5, 1.25, 2.5, 1.15, 1.0};
	public static Integer[] times = {30, 30, 30, 60, 45};
	
	@Override
	public double getCost() {
		
		return costs[this.ordinal()];
		
	}
	@Override
	public int getPrepareTime() {
		
		return times[this.ordinal()];
		
	}
	
}
