package me.willyb.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import me.willyb.neighborhood.Address;
import me.willyb.neighborhood.Neighborhood;
import me.willyb.neighborhood.Order;
import me.willyb.neighborhood.Truck;
import me.willyb.sandwich.HamSandwich;
import me.willyb.sandwich.Sandwich;

public class Main {

	public static final int ordersToStartWith = 3;
	public static int streetCount = 10;
	public static NeighborhoodMessenger messenger;
	public static Truck truck;
	public static GraphicsListener graphicsListener;

	public static void main(String[] args) {
		
		String[] options = {"Right-Turn-Only Router", "Normal Router"};
		int choice = JOptionPane.showOptionDialog(new JFrame(), "What routing algorithm would you like to use?", "Food Truck Simulator", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
		
		truck = new Truck(choice == 0 ? new RightTurnMethod() : new NormalTurnMethod());
		graphicsListener = new GraphicsListener();

		Neighborhood neighborhood = Neighborhood.getNeighborhood();
		messenger = new NeighborhoodMessenger();

		messenger.addListener(new TextListener());
		messenger.addListener(graphicsListener);

//		neighborhood.generateAddressFile(100);
//		neighborhood.loadOrdersFromFile();

		neighborhood.populateOrders(ordersToStartWith);

		ArrayList<Order> newOrders = new ArrayList<Order>();
		newOrders.addAll(neighborhood.waitingOrders);

		messenger.notifyListenersOrders(newOrders, false);

		Timer mainRunTimer = new Timer(1, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				Main.truck.run();
				Main.graphicsListener.window.repaint();

			}
			
		});

		mainRunTimer.start();

	}

	public static int getMapSize() {

		return (streetCount - 1) * 100;

	}

}
