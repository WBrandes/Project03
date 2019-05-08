package Simulation;

import java.io.IOException;


public interface Observer {


        void update(Truck truckCurLocation) throws IOException;
    }

