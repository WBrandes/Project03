package me.willyb.sandwich;

public class TurkeySandwich extends Sandwich {
	
	public TurkeySandwich() {
		super();
		
		sandwichIngredients.add(Meat.TURKEY);
		sandwichIngredients.add(Bread.WRAP);
		sandwichIngredients.add(Condiment.MAYO);
		sandwichIngredients.add(Vegetable.LETTUCE);
		sandwichIngredients.add(Vegetable.TOMATO);
		
		prepareTime = calculatePrepareTime();
		cost = calculateCost();
		
	}

}
