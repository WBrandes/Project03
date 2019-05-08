package me.willyb.main;

import java.util.ArrayList;
import java.util.UUID;

import me.willyb.neighborhood.Address;
import me.willyb.neighborhood.Order;

public abstract class Listener {
	
	UUID uniqueID = UUID.randomUUID();
	
	public abstract void ordersUpdate(ArrayList<Order> orders, boolean delivered);
	public abstract void truckUpdate(String update);
	
	@Override
	public boolean equals(Object o) {
		
		Listener otherListener = (Listener) o;
		return uniqueID.equals(otherListener.uniqueID);
		
	}
	
}