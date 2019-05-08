package me.willyb.main;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

import me.willyb.neighborhood.Address;
import me.willyb.neighborhood.Order;

public class GraphicsListener extends Listener {
	
	public NeighborhoodGUI gui;
	public JFrame window;
	
	public static double guiScale = 0.5;
	
	public GraphicsListener() {
		
		gui = new NeighborhoodGUI(guiScale, Main.streetCount);
		
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		int windowDimension = (int) (guiScale * 100 * Main.streetCount);
		window.getContentPane().setPreferredSize(new Dimension(windowDimension, windowDimension));
		window.getContentPane().add(gui);
		
		window.setTitle("Food Truck Simulator");
		
		window.pack();
		window.setVisible(true);
		
	}
	
	@Override
	public void ordersUpdate(ArrayList<Order> orders, boolean delivered) {
		
		for(Order o : orders) {
			
			gui.addresses.put(o.destination, delivered);
			
		}
		
		window.repaint();
		
	}

	@Override
	public void truckUpdate(String update) {}

}
