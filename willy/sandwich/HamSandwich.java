package me.willyb.sandwich;

import java.util.ArrayList;

public class HamSandwich extends Sandwich {
	
	public HamSandwich() {
		super();
		
		sandwichIngredients.add(Meat.HAM);
		sandwichIngredients.add(Bread.WHEAT_ROLL);
		sandwichIngredients.add(Condiment.MUSTARD);
		sandwichIngredients.add(Condiment.CHEESE);
		
		prepareTime = calculatePrepareTime();
		cost = calculateCost();
		
	}

}
