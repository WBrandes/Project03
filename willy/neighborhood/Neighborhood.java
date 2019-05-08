package me.willyb.neighborhood;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

import me.willyb.main.Main;

public class Neighborhood {
	
	public static final String FILE_NAME = "neighborhood.txt";
	
	//The distance, in miles, between two addresses
	public static final double addressDistance = 0.03;
	
	public static Neighborhood neighborhood;
	public int currentTime;
	
	public PriorityQueue<Order> waitingOrders;
//	public ArrayList<Address> completedOrders;
	
	public Address distributionCenter;
	
	/**
	 * Creates a new Neighborhood. Private so that only one Neighborhood can ever be in existence.
	 */
	private Neighborhood() {
		
		currentTime = 0;
		waitingOrders = new PriorityQueue<Order>();
//		completedOrders = new ArrayList<Address>();
		distributionCenter = new Address(500, 510);
		
	}
	
	/**
	 * Returns the neighborhood, creating it beforehand if it does not already exist.
	 * @return The neighborhood.
	 */
	public static Neighborhood getNeighborhood() {
		
		if(neighborhood == null) {
			
			neighborhood = new Neighborhood();
			
		}
		
		return neighborhood;
		
	}
	
	public Order requestTopOrder() {
		
		return waitingOrders.poll();
		
	}
	
//	/**
//	 * "Delivers" the order at the front of the PriorityQueue, removing it from the queue and adding it to the completedOrders ArrayList
//	 */
//	public void deliverTopOrder() {
//		
//		Order completedOrder = waitingOrders.poll();
//		if(completedOrder != null) {
//			
//			completedOrders.add(completedOrder.destination);
//			
//		}
//		
//	}
	
	/**
	 * Creates a text file containing some number of addresses. The file name is defined by the variable FILE_NAME in this class.
	 * @param amount The amount of addresses to generate and put in the file.
	 */
	public void generateAddressFile(int amount) {
		
		try {

			FileWriter writer = new FileWriter(new File(FILE_NAME));
			
			for(int i=0; i<amount; i++) {
				
				if(i != 0) {
					
					writer.write("\n");
					
				}
				
				writer.write((new Address(Main.getMapSize())).getStringFormat());
				
			}
			
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Populates the neighborhood's PriorityQueue with Orders generated using addresses from a text file. The file name is defined by the variable FILE_NAME in this class.
	 */
	public void loadOrdersFromFile() {
		
		try {
			
			Scanner scanner = new Scanner(new File(FILE_NAME));
			
			while(scanner.hasNextLine()) {
				
				Address newAddress = new Address(scanner.nextLine());
				waitingOrders.add(new Order(newAddress));
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Creates random Order objects and adds them to the neighborhood's PriorityQueue.
	 * @param amount The amount of Orders to create.
	 */
	public void populateOrders(int amount) {
		
		for(int i=0; i<amount; i++) {
			
			waitingOrders.add(new Order());
			
		}
		
	}
	
	/**
	 * Returns the current time, formatted into human-readable format.
	 * @return A String representing the current time.
	 */
	public String getFormattedTime() {
		
		int minutes = currentTime / 60;
		int hour = minutes / 60;
		
		return Integer.toString(((minutes % 60) % 12) + 1) + ":" + Integer.toString(minutes % 60) + ":" + Integer.toString(currentTime % 60) + " " + (hour < 12 ? "AM" : "PM");
		
	}
	
}
