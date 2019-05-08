package me.willyb.sandwich;

public enum Vegetable implements Ingredient {
	LETTUCE, TOMATO, OLIVE, MUSHROOM, PEPPER, ONION;
	
	public static Double[] costs = {0.5, 0.75, 0.75, 0.75, 0.5, 0.5};
	public static Integer[] times = {20, 35, 30, 25, 20, 20};

	@Override
	public double getCost() {
		
		return costs[this.ordinal()];
		
	}
	@Override
	public int getPrepareTime() {
		
		return times[this.ordinal()];
		
	}
	
}
