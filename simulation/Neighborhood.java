import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.TimerTask;
/*
 * Class assembled by Willy, although code in the constructor was not done by me; I simply took code
 * from Main and moved it here, so that things like the orders and truck are managed with use of the
 * Singleton pattern, rather than in Main.
 * The Neighbor class is an implementation of the Singleton design pattern, and hold everything
 * needed to represent the neighborhood; orders, addresses, and the truck.
 */
public class Neighborhood {
	
    public static final String FILE = "AddressList100.txt";
	
	private static Neighborhood neighborhood;
    public PriorityQueue<Order> orders;
    public Truck truck;
    public Iterator itr;
	
	private Neighborhood() {
		
        truck = new Truck(200.0, 200.0);
		
		// Write 100 random addresses to a file
        AddressIO.writeAddresses(FILE, 100);

        // Read the addresses from the file and place them in a PriorityQueue
        PriorityQueue<Address> addresses = AddressIO.readAddresses(FILE);
        
        // place orderes for each address
        orders = new PriorityQueue<>();
        itr = orders.iterator();
        
        Iterator it = addresses.iterator();
        while (it.hasNext()) {
            Address addr = (Address) it.next();
            
            Order newOrder = new Order(addr);
            orders.add(newOrder);
        }
        
        // Put the addresses into a list
        Iterator<Address> iterator = addresses.iterator();
        ArrayList<Address> addressList = new ArrayList<>();
        while (iterator.hasNext())
            addressList.add(iterator.next());
        // Add the distribution center as the final destination
        addressList.add(new Address(Address.DISTRIBUTION_X, Address.SOUTH, Address.DISTRIBUTION_Y, 1900));
		
	}
	
	//Returns a Neighborhood object, whilst ensuring that there can only ever be one Neighborhood object in existence.
	public static Neighborhood getNeighborhood() {
		
		if(neighborhood == null) {
			
			neighborhood = new Neighborhood();
			
		}
		
		return neighborhood;
		
	}
	
}
