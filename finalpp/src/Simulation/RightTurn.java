package Simulation;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class RightTurn implements Route {

    public ArrayList<Address> listOfTruckLocations = new ArrayList<>();
    public int routeLength = 0;
    public int speedOfTruck = 30;
    public double distanceBtwnHouses = .03;


    @Override
     public void handleUTurn(Address truckLocation, Address houseLocation, String directionOfTravel, boolean sameDirection) {
    
        int streetNum = truckLocation.getStreetNum();
        int tempHouseNum = truckLocation.getHouseNum();

        if (directionOfTravel.equals("up")) {
            while (tempHouseNum % 100 != 0) {
                tempHouseNum -= 10;
                listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.isDirection(), streetNum));
            }


            // switch direction
            String switchDirection;
            if (truckLocation.isDirection())
                switchDirection = "East";
            else
                switchDirection = "South";
            
            boolean isDirection = "East".equals(switchDirection);
            
            if (streetNum < houseLocation.getStreetNum()) {
                tempHouseNum -= 10;
                listOfTruckLocations.add(new Address(tempHouseNum, isDirection, streetNum));
                while (tempHouseNum % 100 != 0) {
                    tempHouseNum -= 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, isDirection, streetNum));
                }
            } else {
                tempHouseNum += 10;
                listOfTruckLocations.add(new Address(tempHouseNum, isDirection, streetNum));
                while (tempHouseNum % 100 != 0) {
                    tempHouseNum += 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, isDirection, streetNum));
                }
            }

            //go back down
            tempHouseNum += 10;
            listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.isDirection(), streetNum));
            while (tempHouseNum % 100 != 0) {
                tempHouseNum += 10;
                listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.isDirection(), streetNum));
            }


        } else if (directionOfTravel.equals("down")) {
        } else if (directionOfTravel.equals("right")) {
        } else {
        }
    }



    
}
