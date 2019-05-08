package Simulation;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Main
{
    public static final String FILE = "AddressList100.txt";
    
    private static ArrayList<Command>   commands;
    
    public static void main(String[] args)
    {

        // Write 100 random addresses to a file
        //AddressIO.writeAddresses(FILE, 100);

        // Read the addresses from the file and place them in a PriorityQueue
        PriorityQueue<Address> addresses = AddressIO.readAddresses(FILE);
        
        
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
        
        
        Route route;
        
        System.out.println("Select Route Method:");
        System.out.println("1. Normal Route");
        System.out.println("2. Right Turn Route");
        System.out.print("Choice> ");
        
        Scanner scanner = new Scanner(System.in);
        
        String userChoice = scanner.nextLine();
        
        switch(userChoice){
            case "1":
                route = new NormalRoute();
                break;
            
            case "2":
                route = new RightTurn();
                break;
                
            default:
                route = new NormalRoute();
                break;
        }
        
        try
        {
            neighborhood.addRoute(addressList);
        }
        catch (UTurnException ute)
        {
            System.out.println("UTurnException occurred.");
        }
        
        
        System.out.println("ROUTE LENGTH: " + neighborhood.getRouteLength());
        System.out.println("------------------------------");
        
        Neighborhood.drawNeighborhood(addresses);
    }
    
    

}

