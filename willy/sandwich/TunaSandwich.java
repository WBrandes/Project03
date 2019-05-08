package me.willyb.sandwich;

public class TunaSandwich extends Sandwich {
	
	public TunaSandwich() {
		super();
		
		sandwichIngredients.add(Meat.TUNA);
		sandwichIngredients.add(Bread.WHEAT_ROLL);
		sandwichIngredients.add(Condiment.MAYO);
		sandwichIngredients.add(Vegetable.LETTUCE);
		
		prepareTime = calculatePrepareTime();
		cost = calculateCost();
		
	}

}
