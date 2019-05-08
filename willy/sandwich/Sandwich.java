package me.willyb.sandwich;

import java.util.ArrayList;

public abstract class Sandwich {
	
	public ArrayList<Ingredient> sandwichIngredients;
	public double cost;
	public int prepareTime;
	
	public Sandwich() {
		
		sandwichIngredients = new ArrayList<Ingredient>();
		
	}
	
	/**
	 * @return The amount of time it will take to make this sandwich, based on all ingredients.
	 */
	public int calculatePrepareTime() {
		
		int prepTime = 0;
		
		for(Ingredient i : sandwichIngredients) {
			
			prepTime += i.getPrepareTime();
			
		}
		
		//Sandwich paper cover & bagging time
		prepTime += 95;
		
		return prepTime;
		
	}
	
	/**
	 * @return The cost of this sandwich, based on all ingredients.
	 */
	public double calculateCost() {
		
		double cost = 0.0;
		
		for(Ingredient i : sandwichIngredients) {
			
			cost += i.getCost();
			
		}
		
		//Sandwich paper cover & bagging cost
		cost += 1.25;
		
		return cost;
		
	}
	
	@Override
	public String toString() {
		
		String output = "Sandwich with: ";
		
		for(Ingredient i : sandwichIngredients) {
			
			output +=  ", " + i.toString();
			
		}
		
		output = output.replaceFirst(", ", "");
		
		output += ". Cost: $" + Double.toString(cost) + ", Prep Time: " + Integer.toString(prepareTime/60) + " minutes, " + Integer.toString(prepareTime%60) + " seconds.";
		
		return output;
		
	}
	
}
