


package Simulation;

public class Command
{
    private int       length;
    private Direction direction;

   
    Command(int length, Direction direction)
    {
        this.length = length;
        this.direction = direction;
    }

   
    
    Command()
    {
        length = 0;
        direction = null;
    }

    public int getLength()
    {
        return length;
    }

    public Direction getDirection()
    {
        return direction;
    }

    public void setLength(int length)
    {
        this.length = length;
    }

    public void setDirection(Direction direction)
    {
        this.direction = direction;
    }

    @Override
    public String toString()
    {
        return "\t=> LENGTH: " + length + " DIRECTION: " + direction ;
    }
}