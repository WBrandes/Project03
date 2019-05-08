package me.willyb.neighborhood;

import java.util.ArrayList;

import me.willyb.main.Main;
import me.willyb.sandwich.Sandwich;
import me.willyb.sandwich.SandwichFactory;

public class Order implements Comparable<Object> {
	
	public static SandwichFactory sandwichFactory = new SandwichFactory();
	
	public Address destination;
	public ArrayList<Sandwich> sandwiches;
	public int time;
	public int prepareTime = 0;
	
	/**
	 * Creates a random Order object. One Sandwich will be randomly generated for the Order.
	 */
	public Order() {
		
		destination = new Address(Main.getMapSize());
		time = 36000 + (int) (Math.random() * 32400);
		
		sandwiches = new ArrayList<Sandwich>();
		sandwiches.add(sandwichFactory.generateRandomSandwich());
		
		calculatePrepareTime();
		
	}
	
	/**
	 * Creates a random Order object, with the given Address.
	 * @param destination The Address for this order to have.
	 */
	public Order(Address destination) {
		
		this.destination = destination;
		time = 36000 + (int) (Math.random() * 32400);
		
		sandwiches = new ArrayList<Sandwich>();
		sandwiches.add(sandwichFactory.generateRandomSandwich());
		
		calculatePrepareTime();
		
	}
	
	/**
	 * Creates an order from given parameters.
	 * @param destination The Address the order is to be delivered to.
	 * @param sandwiches The sandwiches that were ordered.
	 * @param time The time that the order was placed, in seconds from 1:00 AM.
	 */
	public Order(Address destination, ArrayList<Sandwich> sandwiches, int time) {
		
		this.destination = destination;
		this.sandwiches = sandwiches;
		this.time = time;
		
		calculatePrepareTime();
		
	}
	
	private void calculatePrepareTime() {
		
		prepareTime = 0;
		
		for(Sandwich s : sandwiches) {
			
			prepareTime += s.prepareTime;
			
		}
		
	}
	
	/**
	 * Returns the time this order was placed, formatted into human-readable format.
	 * @return A String representing the order time.
	 */
	public String getFormattedTime() {
		
		int minutes = time / 60;
		int hour = minutes / 60;
		
		return Integer.toString((minutes % 60) % 13) + ":" + Integer.toString(minutes % 60) + ":" + Integer.toString(time % 60) + " " + (hour < 12 ? "AM" : "PM");
		
	}

	@Override
	public int compareTo(Object o) {
		
		Order otherOrder = (Order) o;
		return Integer.compare(time, otherOrder.time);
		
	}
	
}
