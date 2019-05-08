package me.willyb.sandwich;

public class RoastBeefSandwich extends Sandwich {
	
	public RoastBeefSandwich() {
		super();
		
		sandwichIngredients.add(Meat.ROAST_BEEF);
		sandwichIngredients.add(Bread.WHEAT_ROLL);
		sandwichIngredients.add(Condiment.PEPPER);
		sandwichIngredients.add(Condiment.CHEESE);
		sandwichIngredients.add(Vegetable.OLIVE);
		
		prepareTime = calculatePrepareTime();
		cost = calculateCost();
		
	}

}