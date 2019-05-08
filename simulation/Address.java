package simulation;

import java.util.Random;

public class Address implements Comparable<Address>
{

    private boolean direction;
    private int X;     // houseNum 
    private int Y;     // streetNum
    private int time;
    private static int lastTime = 0;
    public static final int MINIMUM_TIME_DIFFERENCE = 4;
    public static final int DISTRIBUTION_X = 510;
    public static final int DISTRIBUTION_Y = 5;
    public static final boolean EAST = true;
    public static final boolean SOUTH = false;

    // Creates a random address; pass the number of random addresses that you will be creating to normalize the time.
    protected Address(int numAddresses)
    {
        direction = (new Random()).nextBoolean();
        X = getRandomX();
        Y = getRandomY();
        time = getRandomTime(numAddresses, MINIMUM_TIME_DIFFERENCE);
    }

    protected Address(int X, boolean direction, int Y, int time)
    {
        if(X >= 0 && X < 1000)
            this.X = X;
        else
            this.X = getRandomX();

        if(Y >= 0 && Y < 10)
            this.Y = Y;
        else
            this.Y = getRandomY();

        this.direction = direction;
        this.time = time;
    }

    protected Address(int x, int y, int time)
    {
        if (x % 100 == 0)
        {
            direction = SOUTH;
            X = y;
            Y = x / 100;
        }
        else
        {
            direction = EAST;
            X = x;
            Y = y / 100;
        }
        this.time = time;
    }

    // Returns a random house number between 10 and 1990, where the values follow the pattern 10, 20,.., 90, 110, 120,.., 190, 210,....
    public int getRandomX()
    {
        Random rand = new Random();
        int minVal = 10;
        int maxVal = 990;
        //return (rand.nextInt(20) * 100) + ((rand.nextInt(9) + 1) * 10);
        return rand.nextInt(maxVal - minVal + 1) + minVal;
    }

    // Returns a random street number from 0 to 10
    public int getRandomY()
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
        /*/ Random time based off the last time used
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
        */

        // Completely random time
        /*
        Random rand = new Random();
        int hours = (rand.nextInt(10) + 10) * 100; // 1000 - 1900
        int minutes = (hours == 19) ? 0 : rand.nextInt(60); // 0 - 59
        int totalTime = hours + minutes;
        return (totalTime >= 1900) ? 1900 : totalTime;
        */
        
        
        // we dont need normalizer or min 
        // to generate rondom Time will use the simple way,
        // gerenrate rondom time(hh:mm:ss) but in seconds, and then will parse it to hh:mm:ss
        
        // time betwenn 10am to 7pm => convert that to seconds 10 means 3600seconds
        // 7pm or 19h means 68400
        // so its easy now to generate rondom seconds betwenn these to numbers
        // then will parse seconds to hours minutes and seconds
        Random rand = new Random();
        int randomTimeInSeconds = rand.nextInt(68400 + 1 - 36000) + 36000;
        
        
        int seconds = randomTimeInSeconds;
        int sec = seconds % 60;
        int hrs = seconds / 60;
        int mn = hrs % 60;
        hrs = hrs / 60;
        
        
        lastTime = hrs*10000+ mn*100+sec;
        
        return lastTime;
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

    public int getX()
    {
        return X;
    }

    public int getY()
    {
        return Y;
    }

    public int getTime()
    {
        return time;
    }

    // Distance to another address
    public double distanceTo(Address address)
    {
        if (direction)
            return Math.abs(address.getX() - X) + Math.abs(address.getY() * 100);

        return Math.abs(address.getX() - (Y * 100)) + Math.abs((address.getY() * 100) - X);
    }

    @Override
    public String toString()
    {
        return X + " " + directionToString() + " " + Y + " " + time;
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
        simulation.Address distCenter = new simulation.Address(DISTRIBUTION_X, SOUTH, DISTRIBUTION_Y);
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
        return X == o.X && Y == o.Y && direction == o.direction && time == o.time;
    }
}
