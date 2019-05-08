package me.willyb.sandwich;

public enum Drink implements Ingredient {
	
	COLA, ROOT_BEER, GINGER_ALE, APPLE_JUICE, WATER, GATORADE;
	
	public static Double[] costs = {2.5, 3.0, 2.25, 2.0, 0.5, 2.25};
	public static Integer[] times = {15, 15, 15, 15, 15, 15};
	
	@Override
	public double getCost() {
		
		return costs[this.ordinal()];
		
	}
	@Override
	public int getPrepareTime() {
		
		return times[this.ordinal()];
		
	}

}
