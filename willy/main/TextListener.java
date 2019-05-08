package me.willyb.main;

import java.util.ArrayList;

import me.willyb.neighborhood.Address;
import me.willyb.neighborhood.Order;
import me.willyb.sandwich.Sandwich;

public class TextListener extends Listener {

	@Override
	public void ordersUpdate(ArrayList<Order> orders, boolean delivered) {
		
		String messageOrder = "Orders";
		if(orders.size() == 1) {
			
			messageOrder = "Order";
			
		}
		
		String messageStart = "New ";
		String messageEnd = " Placed, ";
		
		if(delivered) {
			
			messageStart = "";
			messageEnd = " Delivered,";
			
		}
		
		String message = messageStart + messageOrder + messageEnd;
		
		System.out.println(message);
		
		for(int i=0; i<orders.size(); i++) {
			
			if(orders.size() > 1 && i == orders.size() - 1) {
				
				System.out.println("and at " + orders.get(i).destination.getStringFormat() + ", order:");
				
			} else {
			
				System.out.println("at " + orders.get(i).destination.getStringFormat() + ", order:");
				
			}
			
			for(Sandwich s : orders.get(i).sandwiches) {
				
				System.out.println(s.toString());
				
			}
			
		}
		
	}

	@Override
	public void truckUpdate(String update) {
		
		System.out.println(update);
		
	}

}
