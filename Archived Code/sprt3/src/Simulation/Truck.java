package Simulation;

import java.util.Random;

enum Direction
{
    NORTH, 
    EAST, 
    SOUTH, 
    WEST
}


public class Truck
{
    private double x, y;
    private boolean moving;
    private Direction direction;

    Truck(double x, double y, boolean moving,  Direction direction)
    {
        this.x = x;
        this.y = y;
        this.moving = moving;
        this.direction = direction;
    }

    Truck(double x, double y)
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
    
    public void isMoving(boolean m)
    {
        moving = m;
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

    void proccessCommand(Command command) {
        switch (command.getOrder()) {
            case START:
                moving = true;
                break;
            case STOP:
                moving = false;
                break;
            case TURN_LEFT:
                
                switch (direction) {
                    case SOUTH:
                        direction = Direction.EAST;
                        break;
                    case NORTH:
                        direction = Direction.WEST;
                        break;
                    case EAST:
                        direction = Direction.NORTH;
                        break;
                    case WEST:
                        direction = Direction.SOUTH;
                        break;
                }
                
                
                break;
            case TURN_RIGHT:

                switch (direction) {
                    case SOUTH:
                        direction = Direction.WEST;
                        break;
                    case NORTH:
                        direction = Direction.EAST;
                        break;
                    case EAST:
                        direction = Direction.SOUTH;
                        break;
                    case WEST:
                        direction = Direction.NORTH;
                        break;
                }
                
                
                
                break;
            default:
                break;
        }

    }
}

