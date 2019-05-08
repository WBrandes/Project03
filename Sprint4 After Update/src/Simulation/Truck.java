

package Simulation;


public class Truck
{
    private Address   address;
    private double    x, y;
    private Direction direction;

    Truck(double x, double y,  Direction direction)
    {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    Truck(Address address, boolean moving, Direction direction)
    {
        this.address = address;
        this.direction = direction;
    }

    Truck(double x, double y)
    {
        this(x, y, null);
    }

    Truck(Address address)
    {
        this(address, false, null);
    }

    Truck()
    {
       this(0, 0);
    }

    public Address getAddress()
    {
        return address;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public Direction getDirection()
    {
        return direction;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public void setX(double x)
    {
        this.x = x;
    }


    public void setY(double y)
    {
        this.y = y;
    }


    public void setDirection(Direction direction)

    {
        this.direction = direction;
    }
    
    
    void proccessCommand(Command command){
        Address truckLocation = getAddress();

        if (null == command.getDirection()) {
            truckLocation.setX(truckLocation.getX() - 10);
        } else {
            switch (command.getDirection()) {
                case NORTH:
                    truckLocation.setY(truckLocation.getY() - 10);
                    break;
                case SOUTH:
                    truckLocation.setY(truckLocation.getY() + 10);
                    break;
                case EAST:
                    truckLocation.setX(truckLocation.getX() + 10);
                    break;
                default:
                    truckLocation.setX(truckLocation.getX() - 10);
                    break;
            }
        }
        
        
        setAddress(truckLocation);
        
    }



}

