package me.willyb.sandwich;

public class ChickenSandwich extends Sandwich {
	
	public ChickenSandwich() {
		super();
		
		sandwichIngredients.add(Meat.CHICKEN);
		sandwichIngredients.add(Bread.WRAP);
		sandwichIngredients.add(Condiment.MAYO);
		sandwichIngredients.add(Vegetable.TOMATO);
		sandwichIngredients.add(Vegetable.LETTUCE);
		
		prepareTime = calculatePrepareTime();
		cost = calculateCost();
		
	}

}
