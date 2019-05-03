


package Simulation;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

public class  Order implements Comparable<Order>
{
    private ArrayList<Food> items;          
    private Address         address;       
    private boolean         orderDelivered; 
    private int             time;  


    public static int numAddresses = Address.numAddresses; 
    public static int minTimeDiff = Order.minTimeDiff  ;  
    public static int minTime = Address.minTime;     
    public static int maxTime = Address.maxTime;


    private static int lastTime = 0; 

    public Order(Address address, int time, ArrayList<Food> items)
    {
        this.address = address;
        this.time = time;
        this.items = items;
        orderDelivered = false;
    }



   
    
    protected Order(Address address)
    {
        this.address = address;
        time = getRandomTime();
        items = new ArrayList<Food>();
        orderDelivered = false;

       
        Random rand = new Random();
        if (rand.nextBoolean())
            items.add(new Food("Pizza" + (rand.nextInt(3) + 1)));
        if (rand.nextBoolean())
            items.add(new Food("Sandwich" + (rand.nextInt(3) + 1)));
        if (rand.nextBoolean())
            items.add(new Food("Chips" + (rand.nextInt(3) + 1)));
       
        if (items.isEmpty())
            items.add(new Food("Sandwich" + (rand.nextInt(3) + 1)));
    }

   
    protected Order()
    {
        this(new Address());
    }

    
    public int getRandomTime()
    {
        if (lastTime == 0)
        {
            lastTime = minTime;
            return lastTime;
        }
        int timeDelta = (int)(maxTime / 100.0) * 60 + (maxTime % 100) - ((int)(minTime / 100.0) * 60 + (minTime % 100));
        int increment = (new Random()).nextInt((timeDelta / numAddresses) - minTimeDiff) + minTimeDiff;
        int time = (int)(lastTime / 100.0) * 60 + (lastTime % 100) + increment;   
        lastTime = ((int)(time / 60.0) * 100) + time % 60;                        
        if (lastTime >= maxTime)
        {
            lastTime = 1000;
            return 1900;
        }
        return lastTime;
    }

   
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

   
    public String getItemsAsString()
    {
        if (items.size() == 0)
            return "";
        String s = "";
        for (Food f : items)
            s += f + ",";
        return s.substring(0, s.length() - 1);
    }

    public boolean wasOrderDelivered()
    {
        return orderDelivered;
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

    public void setOrderDelivered(boolean orderDelivered)
    {
        this.orderDelivered = orderDelivered;
    }

    public static void setNumAddresses(int numAddresses)
    {
        if (numAddresses > 0)
            Order.numAddresses = numAddresses;
    }

    public static void setMinTimeDiff(int minTimeDiff)
    {
        if (numAddresses >= 0)
            Order.minTimeDiff = minTimeDiff;
    }

    public static void setMinTime(int minTime)
    {
        if (minTime > 0)
            Order.minTime = minTime;
    }

    public static void setMaxTime(int maxTime)
    {
        if (maxTime > minTime && maxTime > 0)
            Order.maxTime = maxTime;
    }

    @Override
    public String toString()
    {
        return address.getHouseNumber() + " " + address.getDirection() + " " + address.getStreetNumber() + " at " + timeToString() + ((items.size() > 0) ? ("; Items ordered: " + getItemsAsString()) : "");
    }

    public String toStringSimple()
    {
        return address + " " + time + " " + ((items.size() > 0) ? getItemsAsString() : "");
    }

    @Override
    public int compareTo(Order o)
    {
        if (getTime() < o.getTime())
            return -1;

        if (getTime() > o.getTime())
            return 1;

        return 0;

    }



    public static PriorityQueue<Order> readOrdersWithAddresses(String filename)
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

    public static void writeOrdersWithAddresses(String filename, int numberAddresses)
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
