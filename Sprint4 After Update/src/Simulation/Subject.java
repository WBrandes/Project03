package Simulation;

import java.io.IOException;

public interface Subject {

     void registerObserver(Observer display);
    void removeObserver(Observer display);


    void notifyObserver(Truck truckCurLocation) throws IOException;
}
