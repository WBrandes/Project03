package me.willyb.sandwich;

public enum Side implements Ingredient {
	
	CHIPS, PRINGLES, CHEEZITS, FRUIT_SNACKS;
	
	public static Double[] costs = {1.25, 1.5, 1.5, 1.25};
	public static Integer[] times = {10, 10, 10, 10};
	
	@Override
	public double getCost() {
		
		return costs[this.ordinal()];
		
	}
	@Override
	public int getPrepareTime() {
		
		return times[this.ordinal()];
		
	}
}
