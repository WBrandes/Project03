package me.willyb.sandwich;

import java.util.ArrayList;

public class CustomSandwich extends Sandwich {
	
	public CustomSandwich(ArrayList<Ingredient> ingredients) {
		
		sandwichIngredients = ingredients;
		
		prepareTime = calculatePrepareTime();
		cost = calculateCost();
		
	}

}
