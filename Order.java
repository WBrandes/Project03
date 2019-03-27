/*
 * The Order class is has an address value, time, and list of items ordered. The static values addresses, minTimeDiff,
 * minTime, and maxTime are values set by our config file for different calculations. lastTime allows us to keep track
 * of the last time used for our next random time calculation.
 */


package Simulation;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

public class Order implements Comparable<Order>
{
    private ArrayList<Food> items; // The items ordered
    private Address address;       // Location where the order is to be delivered
    private int time;              // The time the order was ordered

    private static int addresses;    // The number of addresses being simulated
    private static int minTimeDiff;  // The minimum time difference between orders for the simulation
    private static int minTime;      // The minimum time the order can be ordered at
    private static int maxTime;      // The maximum time the order can be ordered at
    private static int lastTime = 0; // The time the last order was ordered at

    public Order(Address address, int time, ArrayList<Food> items)
    {
        this.address = address;
        this.time = time;
        this.items = items;
    }

    // Creates a random order
    protected Order(Address address)
    {
        this.address = address;
        time = getRandomTime(addresses, minTimeDiff);

        // Adds random items to the order
        Random rand = new Random();
        if (rand.nextBoolean())
            items.add(new Food("Beverage"));
        if (rand.nextBoolean())
            items.add(new Food("Sandwich"));
        if (rand.nextBoolean())
            items.add(new Food("Chips"));
        // Force the customer to buy a sandwich if they say they want nothing
        if (items.isEmpty())
            items.add(new Food("Sandwich"));
    }

    // Creates a random order
    protected Order()
    {
        this(new Address());
    }

    /*
     * Returns a random time in military form in the range 10 AM - 7 PM (1000-1900) which gives us 600 minutes to work with.
     * The paramater normalizer decides the increments of time. If you want up to 100 increments, pass 100 each time.
     * The paramater min sets the minimum value that the times will be offset by
     */
    public int getRandomTime(int normalizer, int min)
    {
        // Random time based off the last time used
        if (lastTime == 0)
        {
            lastTime = 1000;
            return lastTime;
        }
        int increment = (new Random()).nextInt((600 / normalizer) - min) + min; // Normalize the 600 minutes to the increment needed
        int time = (int)(lastTime / 100.0) * 60 + (lastTime % 100) + increment;    // Convert time sum to minutes
        lastTime = ((int)(time / 60.0) * 100) + time % 60;                         // Convert time to military time
        if (lastTime >= 1900)
        {
            lastTime = 1000;
            return 1900;
        }
        return lastTime;

        // Completely random time
        /*
        Random rand = new Random();
        int hours = (rand.nextInt(10) + 10) * 100; // 1000 - 1900
        int minutes = (hours == 19) ? 0 : rand.nextInt(60); // 0 - 59
        int totalTime = hours + minutes;
        return (totalTime >= 1900) ? 1900 : totalTime;
        */
    }

    // For normal looking time
    public String timeToString()
    {
        int hours = (int)(time / 100.0);
        int minutes = time % 100;
        return ((hours > 12) ? (hours - 12) : hours) + ":" + ((minutes < 10) ? "0" : "") + minutes + ((time > 1159) ? "PM" : "AM");
    }

    public Address getAddress()
    {
        return address;
    }

    public ArrayList<Food> getItems()
    {
        return items;
    }

    public int getTime()
    {
        return time;
    }

    // Returns the list of items as a csv style string
    public String getItemsAsString()
    {
        if (items.size() == 0)
            return "";
        String s = "";
        for (Food f : items)
            s += f + ",";
        return s.substring(0, s.length() - 1); // removes the extra comma
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public void setItems(ArrayList<Food> items)
    {
        this.items = items;
    }

    public void setTime(int time)
    {
        this.time = time;
    }

    @Override
    public String toString()
    {
        return address.getHouseNumber() + " " + address.getDirection() + " " + address.getStreetNumber() + " at " + time + ((items.size() > 0) ? ("; Items ordered: " + getItemsAsString()) : "");
    }

    public String toStringSimple()
    {
        return address + " " + time + " " + getItemsAsString();
    }

    @Override
    public int compareTo(Order o)
    {
        if (getTime() < o.getTime())
            return -1;

        if (getTime() > o.getTime())
            return 1;

        return 0;

        // Comparable by distance from distribution center
        /*
        Address distCenter = new Address(DISTRIBUTION_HOUSENUM, SOUTH, DISTRIBUTION_STREETNUM);
        double distanceToDC = distanceTo(distCenter);
        double distanceToDCfromO = o.distanceTo(distCenter);
        if (distanceToDC < distanceToDCfromO)
            return -1;

        if (distanceToDC > distanceToDCfromO)
            return 1;

        return 0;
         */
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Order)
        {
            Order o = (Order) obj;
            return address.equals(o.getAddress()) && items.equals(o.getItems()) && time == o.time;
        }
        return false;
    }

    public static PriorityQueue<Order> readOrders(String filename)
    {
        PriorityQueue<Order> orders = new PriorityQueue<>();
        try
        {
            Scanner scanner = new Scanner(new File(filename));
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String[] values = line.split(" ");
                String[] address = values[0].split(",");
                Address address2 = new Address(Integer.parseInt(address[0]), Integer.parseInt(address[1]));
                ArrayList<Food> items = new ArrayList<>();
                if (values.length > 2)
                {
                    for (String s : values[2].split(","))
                        items.add(new Food(s));
                }
                orders.add(new Order(address2, Integer.parseInt(values[1]), items));
            }
        }
        catch (IOException e)
        {
            System.out.println("IOException encountered: " + e);
        }
        catch (Exception e)
        {
            System.out.println("Error in creation of Address: " + e);
        }

        return orders;
    }

    public static void writeOrders(String filename, int numberAddresses)
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename)));
            for (int i = 0; i < numberAddresses; i++)
                writer.write((new Order()).toStringSimple() + "\n");
            writer.flush();
            writer.close();
        }
        catch (IOException e)
        {
            System.out.println("IOException encountered: " + e);
        }
    }
}
