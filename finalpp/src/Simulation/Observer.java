package Simulation;

import java.io.IOException;

public interface Observer {
    void update(Address truckCurLocation) throws IOException;
}
