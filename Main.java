


import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Timer;


public class Main
{
    public static final String FILE = "AddressList100.txt";



    public static void main(String[] args)
    {

        Timer timer = new Timer();

        // Write 100 random addresses to a file
        //AddressIO.writeAddresses(FILE, 100);

        // Read the addresses from the file and place them in a PriorityQueue
        PriorityQueue<Address> addresses = AddressIO.readAddresses(FILE);


        Truck truck = new Truck();


        // place orderes for each address
        PriorityQueue<Order> orders = new PriorityQueue<>();

        Iterator<Address> it = addresses.iterator();
        while (it.hasNext()) {
            Address addr = (Address) it.next();

            Order newOrder = new Order(addr);
            orders.add(newOrder);
        }

        // list of truck commands to find
        ArrayList<Command> truckCommands = new ArrayList<>();


        // Draw the neighborhood with the addresses and distribution center shown
        Neighborhood neighborhood = new Neighborhood();
        neighborhood.generateNeighborhood(addresses);
        // neighborhood.printNeighborhood();

        // Put the addresses into a list
        Iterator<Address> iterator = addresses.iterator();
        ArrayList<Address> addressList = new ArrayList<>();
        while (iterator.hasNext())
            addressList.add(iterator.next());
        // Add the distribution center as the final destination
        addressList.add(new Address(Address.DISTRIBUTION_HOUSENUM, Address.SOUTH, Address.DISTRIBUTION_STREETNUM, 1900));

        // Create the route the truck will follow and calculate the length of the trip

        try
        {
            neighborhood.addRoute(addressList);
        }
        catch (UTurnException ute)
        {
            System.out.println("UTurnException occurred.");
        }
        System.out.println("Route length: " + neighborhood.getRouteLength());

        Neighborhood.drawNeighborhood(addresses);
    }

}

