


package Simulation;


import java.util.Random;

public class Address
{
    
    public static int blocks = 10;
    public static Position distributionCenter = new Position(500, 510);
    public static int size = 600;
    public static int numAddresses = 10;
    public static int minTime = 1000;
    public static int maxTime = 1900;
    public static int min_time_difference = 4;


   
    private int x, y;
    
    private static int lastTime = 0;

   
    protected Address()
    {
       
        if ((new Random()).nextBoolean())
        {
            x = 100*(getRandomAddress(blocks)/100);
            y = getRandomAddress(blocks - 1);
        }
        else
        {
            x = getRandomAddress(blocks - 1);
            y = 100*(getRandomAddress(blocks)/100);
        }
    }

    protected Address(int x, int y)
    {
        if((x >= 100 && x <= (blocks*100)) && (y >= 100 && y <= (blocks*100)))
        {
            this.x = x;
            this.y = y;
        }
    }

    private int getRandomAddress(int nb)
    {
        Random rand = new Random();
        return (rand.nextInt(nb) + 1)*100 + (rand.nextInt(9) + 1)*10;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getHouseNumber()
    {
        return (x % 100 != 0) ? x : y;
    }

    public int getStreetNumber()
    {
        return ((x % 100 != 0) ? y : x) / 100;
    }

    public String getDirection()
    {
        return (x % 100 != 0) ? "EAST" : "WEST";
    }

    public static void setBlocks(int blocks)
    {
        if (blocks > 0)
            Address.blocks = blocks;
    }

    public void setX(int x)
    {
       
        if(x<0) x = 0;
        if(x>1100) x = 1100; 
        this.x = x;
    }

    public void setY(int y)
    {
        
        if(y<0) y = 0;
        if(y>1100) y = 1100; 
        this.y = y;
    }

    @Override
    public String toString()
    {
        return x + "," + y;
    }
    
    
    public int getRandomTime(int normalizer, int min)
    {
        
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

    


}
