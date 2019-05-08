package me.willyb.sandwich;

import java.util.ArrayList;

public class SandwichFactory {
	
	public static String[] sandwichTypes = {"ham", "roast_beef", "turkey", "tuna", "chicken"};
	
	/**
	 * Creates a sandwich, based on the specified type.
	 * @param sandwichType The type of sandwich to return. Valid options are: "ham", "roast_beef", "turkey", "tuna", and "chicken". Any other value will generate a randomized sandwich.
	 * @return A Sandwich object.
	 */
	public Sandwich createSandwich(String sandwichType) {
		
		switch(sandwichType) {
			
			case "ham":
				return new HamSandwich();

			case "roast_beef":
				return new RoastBeefSandwich();

			case "turkey":
				return new TurkeySandwich();

			case "tuna":
				return new TunaSandwich();

			case "chicken":
				return new ChickenSandwich();

			default:
				return createRandomCustomSandwich();
			
		}
		
	}
	
	/**
	 * Generates a random sandwich object, for use in generating random orders. The sandwich has a 50% chance of being one of the 'standard' options, and a 50% chance of being a custom sandwich.
	 * @return
	 */
	public Sandwich generateRandomSandwich() {
		
		if(Math.random() < 0.5) {
			
			return createSandwich(sandwichTypes[(int) (Math.random() * 5)]);
			
		}
		
		return createRandomCustomSandwich();
		
	}
	
	/**
	 * Creates a CustomSandwich object based on the given ingredients.
	 * @param ingredients Ingredients the sandwich will be composed of.
	 * @return The sandwich made from the given ingredients.
	 */
	public Sandwich createCustomSandwich(ArrayList<Ingredient> ingredients) {
		
		return new CustomSandwich(ingredients);
		
	}
	
	/**
	 * Generates a randomized CustomSandwich object. Meat and bread will be chosen randomly, and up to two condiments, two vegetables, one side, and one drink will be added to the sandwich. All ingredient types, aside from bread and meat, have a 1/(possible types) chance of not being added to the sandwich
	 * @return The random sandwich generated.
	 */
	public Sandwich createRandomCustomSandwich() {
		
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		
		int meat = (int) (Math.random() * Meat.values().length);
		int bread = (int) (Math.random() * Bread.values().length);
		
		ingredients.add(Meat.values()[meat]);
		ingredients.add(Bread.values()[bread]);
		
		//Possibly adding two condiments
		tryAddIngredient(ingredients, 0);
		tryAddIngredient(ingredients, 0);
		
		//Possibly adding two vegetables
		tryAddIngredient(ingredients, 1);
		tryAddIngredient(ingredients, 1);
		
		//Possibly adding a side and a drink
		tryAddIngredient(ingredients, 2);
		tryAddIngredient(ingredients, 3);
		
		return new CustomSandwich(ingredients);
		
		
	}
	
	/**
	 * Will try to add a random ingredient of a specified type to some given list of ingredients. Any ingredient has a 1/(possible types) chance of not being added.
	 * @param ingredients The list of ingredients to add a random ingredient to.
	 * @param ingredientType The type of ingredient to add. 0 = Condiment, 1 = Vegetable, 2 = Side, other = Drink
	 */
	public void tryAddIngredient(ArrayList<Ingredient> ingredients, int ingredientType) {
		
		//List of enums to randomly choose from
		Ingredient[] ingredientValues;
		
		//Choosing which enum we want to add
		switch(ingredientType) {
			
			case 0:
				ingredientValues = Condiment.values();
				break;
			case 1:
				ingredientValues = Vegetable.values();
				break;
			case 2:
				ingredientValues = Side.values();
				break;
			default:
				ingredientValues = Drink.values();
				break;
				
		}
		
		/* Randomly generating an index for the ingredientValues array.
		 * The range the random int can be is one larger than the indexable values of the array; if we go out of bounds, no ingredient is added. This gives us a 1/ingredientsList.length chance of not adding an ingredient.
		 */
		int ingredient = (int) (Math.random() * (ingredientValues.length + 1));
		if(ingredient > (ingredientValues.length - 1)) {
			
			return;
			
		}
		
		ingredients.add(ingredientValues[ingredient]);
		
	}
	
}
