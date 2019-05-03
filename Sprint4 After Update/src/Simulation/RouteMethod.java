
package Simulation;

import java.util.PriorityQueue;

public interface RouteMethod
{
    Route calculateRoute(PriorityQueue<Order> orders, Position distrCenter);
}