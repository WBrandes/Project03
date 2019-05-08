package me.willyb.main;

import java.util.ArrayList;

import me.willyb.neighborhood.Order;

public interface Subject {
	
	public void notifyListenersOrders(ArrayList<Order> orders, boolean delivered);
	public void notifyListenersTruck(String message);
	public void addListener(Listener listener);
	public void removeListener(Listener listener);
	
}
