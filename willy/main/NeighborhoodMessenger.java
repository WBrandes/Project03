package me.willyb.main;

import java.util.ArrayList;

import me.willyb.neighborhood.Address;
import me.willyb.neighborhood.Order;

public class NeighborhoodMessenger implements Subject {
	
	ArrayList<Listener> listeners = new ArrayList<Listener>();
	
	@Override
	public void addListener(Listener listener) {
		
		listeners.add(listener);
		
	}

	@Override
	public void removeListener(Listener listener) {
		
		listeners.remove(listener);
		
	}

	@Override
	public void notifyListenersOrders(ArrayList<Order> orders, boolean delivered) {
		
		for(Listener listener : listeners) {
			
			listener.ordersUpdate(orders, delivered);
			
		}
		
	}

	@Override
	public void notifyListenersTruck(String message) {
		
		
		for(Listener listener : listeners) {
			
			listener.truckUpdate(message);
			
		}
	}
	
}
