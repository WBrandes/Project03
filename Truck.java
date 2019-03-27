package Simulation;


public class Truck
{
    private double x, y;
    private boolean moving;
    private Direction direction;

    Truck(int x, int y, boolean moving,  Direction direction)
    {
        this.x = x;
        this.y = y;
        this.moving = moving;
        this.direction = direction;
    }

    Truck(int x, int y)
    {
        this(x, y, false, null);
    }

    Truck()
    {
       this(0, 0);
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public boolean isMoving()
    {
        return moving;
    }

    public Direction getDirection()
    {
        return direction;
    }

    public void setX(double x)
    {
        this.x = x;
    }


    public void setY(double y)
    {
        this.y = y;
    }

    public void setMoving(boolean moving)
    {
        this.moving = moving;
    }

    public void setDirection(Direction direction)

    {
        this.direction = direction;
    }
}

enum Direction
{
    NORTH, EAST, SOUTH, WEST
}
