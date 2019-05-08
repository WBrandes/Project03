
package Simulation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

public class RightTurnRoute implements RouteMethod
{
    private static Route route;
    private static Truck truck;

    @Override
    public Route calculateRoute(PriorityQueue<Order> orders, Position distrCenter)
    {
       
        if (orders.isEmpty())
        {
            System.out.println("Empty order exiting program");
            System.exit(1);
        }

        route = new Route(0, 0, new ArrayList<>());
        truck = new Truck(new Address((int) distrCenter.getX(), (int) distrCenter.getY()));

       
        if (orders.peek().getAddress().getY() < truck.getAddress().getY())
            truck.setDirection(Direction.NORTH);
        else
            truck.setDirection(Direction.SOUTH);

        Iterator<Order> iterator = orders.iterator();
        while (iterator.hasNext())
        {
            Order currentOrder = iterator.next();
            int curX = truck.getAddress().getX();
            int curY = truck.getAddress().getY();
            int destX = currentOrder.getAddress().getX();
            int destY = currentOrder.getAddress().getY();

            int vertDel = curY - destY;
            int horDel = curX - destX; 

           
            if (curY % 100 != 0)
            {
               
                if (truck.getDirection() == Direction.NORTH && vertDel >= 0)
                    OnlyRightTurnVertical(Direction.NORTH, horDel, destX, destY);
                else if (truck.getDirection() == Direction.SOUTH && vertDel < 0)
                    OnlyRightTurnVertical(Direction.SOUTH, horDel, destX, destY);
                   
                else
                    handleLeftTurn(truck.getDirection(), destX, destY);
            }
           
            else if (curX % 100 != 0)
            {
               
                if (truck.getDirection() == Direction.EAST && horDel <= 0)
                    OnlyRightTurnHorizontal(Direction.EAST, horDel, destX, destY);
                else if (truck.getDirection() == Direction.WEST && horDel > 0)
                    OnlyRightTurnHorizontal(Direction.WEST, horDel, destX, destY);
                   
                else
                    handleLeftTurn(truck.getDirection(), destX, destY);
            }
            else
            {
                System.out.println("Reached conner");
                System.exit(1);
            }

           
            route.addCommand(new Command(5, Direction.NONE));
        }

        return route;
    }



   
    private void OnlyRightTurnVertical(Direction direction, int horDel, int destX, int destY)
    {
       
        if (destY % 100 == 0 || (destY % 100 != 0 && horDel != 0)) {
            moveNearestBlockToDestination(direction, truck.getAddress().getX(), truck.getAddress().getY(), destX, destY);
           
            if (direction == Direction.SOUTH && horDel > 0){
                route.setTime(route.getTime() + 2);
                direction = Direction.WEST;
            }
            else if (direction == Direction.NORTH && horDel < 0){
                route.setTime(route.getTime() + 2);
                direction = Direction.EAST;

            }
        }
       
        if (destY % 100 != 0 && horDel != 0)
        {
            int curY = truck.getAddress().getY();
            if (curY < destY )
                moveNearestBlockToDestination(direction, truck.getAddress().getX(), curY, destX, destY);
           
            if (direction == Direction.WEST && curY < destY){
                route.setTime(route.getTime() + 2);
                direction = Direction.SOUTH;
            }
            if(direction == Direction.EAST && curY > destY){
                route.setTime(route.getTime() + 2);
                direction = Direction.NORTH;
            }
        }
       
       
        moveDestination(truck.getAddress().getX(), truck.getAddress().getY(), destX, destY);
    }

    private void OnlyRightTurnHorizontal(Direction direction, int vertDel, int destX, int destY)
    {
       
        if (destX % 100 == 0 || (destX % 100 != 0 && vertDel != 0))
        {
            moveNearestBlockToDestination(direction, truck.getAddress().getX(), truck.getAddress().getY(), destX, destY);
           
            if (direction == Direction.EAST && vertDel < 0)
            {
                route.setTime(route.getTime() + 2);
                direction = Direction.NORTH;
            }
            else if (direction == Direction.WEST && vertDel > 0)
            {
                route.setTime(route.getTime() + 2);
                direction = Direction.SOUTH;
            }
           
            if (destX % 100 != 0 && vertDel != 0) {
                int curX = truck.getAddress().getX();
                moveNearestBlockToDestination(direction, curX, truck.getAddress().getY(), destX, destY);
               
                if (direction == Direction.SOUTH && curX > destX){
                    route.setTime(route.getTime() + 2);
                    direction = Direction.WEST;
                }
                else if (direction == Direction.NORTH && curX < destX){
                    route.setTime(route.getTime() + 2);
                    direction = Direction.EAST;
                }
            }
           
           
            moveDestination(truck.getAddress().getX(), truck.getAddress().getY(), destX, destY);
        }
    }

    private void handleLeftTurn(Direction direction, int destX, int destY)
    {
       
        if (direction != null)
        {
           
            moveToEndOfBlock(direction, truck.getAddress().getX(), truck.getAddress().getY());
            moveToEndOfBlock(direction, truck.getAddress().getX(), truck.getAddress().getY());
            moveRightOneBlock(direction, truck.getAddress().getX(), truck.getAddress().getY());
            moveRightOneBlock(direction, truck.getAddress().getX(), truck.getAddress().getY());
            moveRightOneBlock(direction, truck.getAddress().getX(), truck.getAddress().getY());
        }
        else
        {
            System.out.println("Null direction");
            System.exit(1);
        }
       
        if (direction == Direction.NORTH || direction == Direction.SOUTH)
            OnlyRightTurnVertical(truck.getDirection(), truck.getAddress().getX() - destX, destX, destY);
        else
            OnlyRightTurnHorizontal(truck.getDirection(), truck.getAddress().getY() - destY, destX, destY);
    }



   
    private void moveDestination(int curX, int curY, int destX, int destY)
    {
        Direction direction = null;
        int       distance  = Math.abs(curX - destX) + Math.abs(curY - destY);

       
        if (destX % 100 == 0)
        {
           
            if ((curY - destY) > 0)
                direction = Direction.NORTH;
           
            else
                direction = Direction.SOUTH;
        }
       
        else if (destY % 100 == 0)
        {
           
            if ((curX - destX) > 0)
                direction = Direction.WEST;
           
            else
                direction = Direction.EAST;
        }
       
        else{
            System.out.println("Reached conner");
            System.exit(1);
        }
       
        updateTruckAndRoute(direction, new Address(destX, destY), distance / 10, 0);
    }

   
    private void moveNearestBlockToDestination(Direction direction, int curX, int curY, int destX, int destY)
    {
        int distance = 0;

        if (direction == Direction.NORTH || direction == Direction.SOUTH)
        {
            destY = 100 * (destY / 100) + ((destY % 100 >= 50) ? 100 : 0);
            distance = Math.abs(curY - destY);
           
            destX = curX;
        }
        else if (direction == Direction.EAST || direction == Direction.WEST)
        {
            destX = 100 * (destX / 100) + ((destX % 100 >= 50) ? 100 : 0);
            distance = Math.abs(curX - destX);
           
            destY = curY;
        }

       
        updateTruckAndRoute(direction, new Address(destX, destY), distance / 10, 0);
    }

    private void moveToEndOfBlock(Direction direction, int curX, int curY)
    {
        int distance = 0;
        int destX    = 0;
        int destY    = 0;

        if (direction == Direction.NORTH || direction == Direction.SOUTH)
        {
            destY = 100 * (curY / 100) + ((direction == Direction.SOUTH) ? 100 : 0);
            distance = Math.abs(curY - destY);
           
            destX = curX;
        }
        else if (direction == Direction.EAST || direction == Direction.WEST)
        {
            destX = 100 * (curX / 100) + ((direction == Direction.EAST) ? 100 : 0);
            distance = Math.abs(curX - destX);
           
            destY = curY;
        }

       
        updateTruckAndRoute(direction, new Address(destX, destY), distance / 10, 0);
    }

    private void moveRightOneBlock(Direction direction, int curX, int curY)
    {
       
        direction = getDirectionToRight(direction);
        if (direction == Direction.NORTH)
            curY -= 100;
        else if (direction == Direction.SOUTH)
            curY += 100;
        else if (direction == Direction.EAST)
            curX += 100;
        else
            curX -= 100;

       
        updateTruckAndRoute(direction, new Address(curX, curY), 10, 2);
    }



   
    private static void updateTruckAndRoute(Direction direction, Address destination, int distance, int time)
    {
       
        truck.setDirection(direction);
        truck.setAddress(destination);
        route.addCommand(new Command(distance, direction));
        route.setDistance(route.getDistance() + distance);
        route.setTime(route.getTime() + distance + time);
    }

    private static Direction getOppositeDirection(Direction direction)
    {
        if (direction == Direction.NORTH)
            return Direction.SOUTH;
        else if (direction == Direction.SOUTH)
            return Direction.NORTH;
        else if (direction == Direction.EAST)
            return Direction.WEST;
        return Direction.EAST;
    }

    private static Direction getDirectionToRight(Direction direction)
    {
        if (direction == Direction.NORTH)
            return Direction.EAST;
        else if (direction == Direction.SOUTH)
            return Direction.WEST;
        else if (direction == Direction.EAST)
            return Direction.SOUTH;
        return Direction.NORTH;
    }

    
}
