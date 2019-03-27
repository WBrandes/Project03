/*
 * The Address class is a simple class that encapsulates a x and y value to represent a location within a neighborhood.
 * The static variable blocks tells us the number of blocks in the neighborhood.
 * Every 100 units is a new street and houses are multiples of 10 between streets
 */


package Simulation;


import java.util.Random;

public class Address
{
    private static int blocks;    // The number of blocks for the neighborhood
    private int x, y;

    // Creates a random address based on the number of blocks in the neighborhood
    protected Address()
    {
        // Make one of the values a street number (blocks) and the other a house number (blocks - 1)
        if ((new Random()).nextBoolean())
        {
            x = getRandomCoordinate(blocks);
            y = getRandomCoordinate(blocks - 1);
        }
        else
        {
            x = getRandomCoordinate(blocks - 1);
            y = getRandomCoordinate(blocks);
        }
    }

    protected Address(int x, int y) throws Exception
    {
        if((x >= 100 && x <= (blocks*100)) && (y >= 100 && y <= (blocks*100)))
        {
            this.x = x;
            this.y = y;
        }
        else
            throw new Exception();
    }

    private int getRandomCoordinate(int numBlocks)
    {
        Random rand = new Random();
        return (rand.nextInt(numBlocks) + 1)*100 + (rand.nextInt(9) + 1)*10;
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

    public char getDirection()
    {
        return (x % 100 != 0) ? 'E' : 'S';
    }

    public static int getBlocks()
    {
        return blocks;
    }

    public static void setBlocks(int blocks) throws Exception
    {
        if (blocks > 0)
            Address.blocks = blocks;
        else
            throw new Exception();
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    @Override
    public String toString()
    {
        return x + "," + y;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Address)
        {
            Address o = (Address) obj;
            return x == o.x && y == o.y;
        }
        return false;
    }
}
