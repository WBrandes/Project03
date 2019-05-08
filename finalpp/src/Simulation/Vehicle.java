package Simulation;

import java.io.IOException;

public interface Vehicle {

    
    void registerObserver(Observer display);
    void removeObserver(Observer display);

    void notifyObserver(Address truckCurLocation) throws IOException;

}
