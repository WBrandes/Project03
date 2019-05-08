package Simulation;


import java.util.Random;

public class Address implements Comparable<Address>
{

    private boolean direction;
    private int houseNum;      // House numbers are multiples of 10: 10, 20,...,90, 110, 120,..., 190, 210,...., 1890
    private int streetNum;     // Street numbers are 0 - 19
    private int time;
    private static int lastTime = 0;
    public static final int MINIMUM_TIME_DIFFERENCE = 4;
    public static final int DISTRIBUTION_HOUSENUM = 510;
    public static final int DISTRIBUTION_STREETNUM = 5;
    public static final boolean EAST = true;
    public static final boolean SOUTH = false;
     public Order  foodOrder;
     public double       distanceBtwnHouses = .03;

    // Creates a random address; pass the number of random addresses that you will be creating to normalize the time.
    protected Address(int numAddresses)
    {
        direction = (new Random()).nextBoolean();
        houseNum = getRandomHouseNum();
        streetNum = getRandomStreetNum();
        time = getRandomTime(numAddresses, MINIMUM_TIME_DIFFERENCE);
        foodOrder = new Order();
        
    }

    protected Address(int houseNum, boolean direction, int streetNum, int time)
    {
        if(houseNum >= 0 && houseNum < 1000)
            this.houseNum = houseNum;
        else
            this.houseNum = getRandomHouseNum();

        if(streetNum >= 0 && streetNum < 10)
            this.streetNum = streetNum;
        else
            this.streetNum = getRandomStreetNum();

        this.direction = direction;
        this.time = time;
        
        foodOrder = new Order();
    }
    
     protected Address(int houseNum, boolean direction, int streetNum)
    {
        if(houseNum >= 0 && houseNum < 1000)
            this.houseNum = houseNum;
        else
            this.houseNum = getRandomHouseNum();

        if(streetNum >= 0 && streetNum < 10)
            this.streetNum = streetNum;
        else
            this.streetNum = getRandomStreetNum();

        this.direction = direction;
        
        foodOrder = new Order();
    }

    protected Address(int x, int y, int time)
    {
        if (x % 100 == 0)
        {
            direction = SOUTH;
            houseNum = y;
            streetNum = x / 100;
        }
        else
        {
            direction = EAST;
            houseNum = x;
            streetNum = y / 100;
        }
        this.time = time;
        
        foodOrder = new Order();
    }

    // Returns a random house number between 10 and 1990, where the values follow the pattern 10, 20,.., 90, 110, 120,.., 190, 210,....
    public int getRandomHouseNum()
    {
        Random rand = new Random();
        int minVal = 10;
        int maxVal = 990;
        //return (rand.nextInt(20) * 100) + ((rand.nextInt(9) + 1) * 10);
        return rand.nextInt(maxVal - minVal + 1) + minVal;
    }

    // Returns a random street number from 0 to 10
    public int getRandomStreetNum()
    {
        return (new Random()).nextInt(10);
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

    public boolean isDirection()
    {
        return direction;
    }

    public String directionToString()
    {
        if (!direction) return "South";
        return "East";
    }

    public int getHouseNum()
    {
        return houseNum;
    }

    public int getStreetNum()
    {
        return streetNum;
    }

    public int getTime()
    {
        return time;
    }

    // Distance to another address
    public double distanceTo(Address address)
    {
        if (direction)
            return Math.abs(address.getHouseNum() - houseNum) + Math.abs(address.getStreetNum() * 100);

        return Math.abs(address.getHouseNum() - (streetNum * 100)) + Math.abs((address.getStreetNum() * 100) - houseNum);
    }

    @Override
    public String toString()
    {
        
        return houseNum + " " + directionToString() + " " + streetNum + " " + time + "  " + foodOrder.toString();
    }

    @Override
    public int compareTo(Address o)
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
        Address o = (Address) obj;
        return houseNum == o.houseNum && streetNum == o.streetNum && direction == o.direction && time == o.time;
    }
}
