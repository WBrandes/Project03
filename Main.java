package Simulation;


import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Timer;

import javax.swing.JFrame;


public class Main
{
	
	public static int streetCount = 10;
    public static final String FILE = "AddressList100.txt";
    
    

    public static void main(String[] args)
    {
        
    	JFrame window = new JFrame();
    	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	double guiScale = 0.5;
    	
    	window.getContentPane().setPreferredSize(new Dimension((int) (guiScale * 100 * streetCount), (int) (guiScale * 100 * streetCount)));
    	window.add(new NeighborhoodGUI(guiScale, streetCount));
    	
    	window.pack();
    	window.setVisible(true);

        // Read the addresses from the file and place them in a PriorityQueue
        PriorityQueue<Address> addresses = AddressIO.readAddresses(FILE);
        
        Truck truck = new Truck(50, 50, false, Direction.NORTH);
        
        // place orderes for each address
        PriorityQueue<Order> orders = new PriorityQueue<Order>();

        Iterator<Address> it = addresses.iterator();
        while (it.hasNext()) {
            Address addr = (Address) it.next();
            
            Order newOrder = new Order(addr);
            orders.add(newOrder);
        }
        
        // list of truck commands to find
        ArrayList<Command> truckCommands = new ArrayList<>();
        
        NormalRouter router = new NormalRouter();
        //router.calculateRoute(truck, orders);
        
        int totalTime = 0;
        for(Command c : router.calculateRoute(truck, orders)) {
        	
        	System.out.println(c.toString());
        	totalTime += c.getTime();
        	
        }
        System.out.println("Total Units of Time: " + totalTime);
        
        
    }

}

