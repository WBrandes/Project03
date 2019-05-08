package Simulation;

import java.io.IOException;
import java.util.ArrayList;

public class Route implements Subject
{
    private int                distance;
    private int                time;
    private ArrayList<Command> commands;
    private ArrayList<Observer> observers;

    Route(int distance, int time, ArrayList<Command> commands)
    {
        this.distance = distance;
        this.time = time;
        this.commands = commands;
        this.observers = new ArrayList();

    }

    Route() {}

    public int getDistance()
    {
        return distance;
    }

    public int getTime()
    {
        return time;
    }

    public ArrayList<Command> getCommands()
    {
        return commands;
    }

    public void setDistance(int distance)
    {
        this.distance = distance;
    }

    public void setTime(int time)
    {
        this.time = time;
    }

    public void setCommands(ArrayList<Command> commands)
    {
        this.commands = commands;
    }

    public void addCommand(Command command)
    {
        commands.add(command);
    }



    @Override
    public void registerObserver(Observer display) {
        observers.add(display);
    }

    @Override
    public void removeObserver(Observer display) {
        observers.remove(display);
    }


    @Override
    public void notifyObserver(Truck currentLocation) throws IOException {
        for (Observer display : observers) {
            display.update(currentLocation);
        }
    }
}
