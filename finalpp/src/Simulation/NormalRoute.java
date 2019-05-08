/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & Elijah Ives

Extends Route class and solves for a simple route the Truck follows.
*/

package Simulation;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class NormalRoute implements Route
{

    public ArrayList<Address> listOfTruckLocations = new ArrayList<>();
    public int                routeLength          = 0;
    public String             directionOfTravel    = "";
    public int                speedOfTruck         = 30;
    public double             distanceBtwnHouses   = .03;


     public void handleUTurn(Address truckLocation, Address houseLocation, String directionOfTravel, boolean sameDirection) {
        Address currentLocation = truckLocation;
        int tempStreetNum = truckLocation.getStreetNum();
        int tempHouseNum = truckLocation.getHouseNum();

        if (directionOfTravel.equals("up") || directionOfTravel.equals("left")) {
            while (tempHouseNum % 100 != 0) {
                tempHouseNum -= 10;
                listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.isDirection(), tempStreetNum));
            }

            // switch direction
            String switchDirection;
            if (truckLocation.isDirection())
                switchDirection = "East";
            else
                switchDirection = "South";
            
            boolean isDirection = ("EAST".equals(switchDirection) );
            
            int directionChangeBlock = tempStreetNum * 100;
            int directionChangeStreet = tempHouseNum / 100;

            tempHouseNum = directionChangeBlock;
            tempStreetNum = directionChangeStreet;
            
            
            listOfTruckLocations.add(new Address(tempHouseNum, isDirection, tempStreetNum));

            if (sameDirection) {
                if (tempStreetNum < houseLocation.getStreetNum()) {
                    tempHouseNum += 10;
                    while (tempHouseNum % 100 != 0) {
                        tempHouseNum += 10;
                        currentLocation = new Address(tempHouseNum, isDirection, tempStreetNum);
                        listOfTruckLocations.add(currentLocation);
                    }
                }
                else {
                    tempHouseNum -= 10;
                    while (tempHouseNum % 100 != 0) {
                        tempHouseNum -= 10;
                        currentLocation = new Address(tempHouseNum, isDirection, tempStreetNum);
                        listOfTruckLocations.add(currentLocation);
                        currentLocation = new Address(tempHouseNum, isDirection, tempStreetNum);
                        listOfTruckLocations.add(currentLocation);
                    }
                }

            }

            else {
                if (tempHouseNum < houseLocation.getHouseNum()) {
                    tempHouseNum += 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, isDirection, tempStreetNum));
                    while (tempHouseNum % 100 != 0) {
                        tempHouseNum += 10;
                        currentLocation = new Address(tempHouseNum, isDirection, tempStreetNum);
                        listOfTruckLocations.add(currentLocation);                    }
                } else {
                    tempHouseNum -= 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, isDirection, tempStreetNum));
                    while (tempHouseNum % 100 != 0) {
                        tempHouseNum -= 10;
                        currentLocation = new Address(tempHouseNum, isDirection, tempStreetNum);
                        listOfTruckLocations.add(currentLocation);
                    }
                }
            }

            directionChangeBlock = tempStreetNum * 100;
            directionChangeStreet = tempHouseNum / 100;

            tempHouseNum = directionChangeBlock;
            tempStreetNum = directionChangeStreet;

            currentLocation = new Address(tempHouseNum, truckLocation.isDirection(), tempStreetNum);
            listOfTruckLocations.add(currentLocation);

            //go back down
            tempHouseNum += 10;
            listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.isDirection(), tempStreetNum));
            while (tempHouseNum % 100 != 0) {
                tempHouseNum += 10;
                currentLocation = new Address(tempHouseNum, truckLocation.isDirection(), tempStreetNum);
                listOfTruckLocations.add(currentLocation);

            }
        }

        else if(directionOfTravel.equals("down") || directionOfTravel.equals("right")) {
            while (tempHouseNum % 100 != 0) {
                tempHouseNum += 10;
                listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.isDirection(), tempStreetNum));
            }

            // switch direction
            String switchDirection;
            if (!truckLocation.isDirection())
                switchDirection = "East";
            else
                switchDirection = "South";
            
            boolean isDirection = !("EAST".equals(switchDirection) );

            int directionChangeBlock = tempStreetNum * 100;
            int directionChangeStreet = tempHouseNum / 100;

            tempHouseNum = directionChangeBlock;
            tempStreetNum = directionChangeStreet;

            listOfTruckLocations.add(new Address(tempHouseNum, isDirection, tempStreetNum));

            if (sameDirection){
                if (tempStreetNum < houseLocation.getStreetNum()){
                    tempHouseNum += 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, isDirection, tempStreetNum));
                    while (tempHouseNum % 100 != 0){
                        tempHouseNum += 10;
                        listOfTruckLocations.add(new Address(tempHouseNum, isDirection, tempStreetNum));
                    }
                }
                else{
                    tempHouseNum -= 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, isDirection, tempStreetNum));
                    while (tempHouseNum % 100 != 0){
                        tempHouseNum -= 10;
                        listOfTruckLocations.add(new Address(tempHouseNum, isDirection, tempStreetNum));
                    }
                }
            }

            else{
                if (tempHouseNum < houseLocation.getHouseNum()){
                    tempHouseNum += 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, isDirection, tempStreetNum));
                    while (tempHouseNum % 100 != 0){
                        tempHouseNum += 10;
                        listOfTruckLocations.add(new Address(tempHouseNum, isDirection, tempStreetNum));
                    }
                }
                else{
                    tempHouseNum -= 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, isDirection, tempStreetNum));
                    while (tempHouseNum % 100 != 0){
                        tempHouseNum -= 10;
                        listOfTruckLocations.add(new Address(tempHouseNum, isDirection, tempStreetNum));
                    }
                }
            }

            directionChangeBlock = tempStreetNum * 100;
            directionChangeStreet = tempHouseNum / 100;

            tempHouseNum = directionChangeBlock;
            tempStreetNum = directionChangeStreet;


            // move back up or back left
            tempHouseNum -= 10;
            listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.isDirection(), tempStreetNum));

            while (tempHouseNum % 100 != 0){
                tempHouseNum -= 10;
                currentLocation = new Address(tempHouseNum, truckLocation.isDirection(), tempStreetNum);
                listOfTruckLocations.add(currentLocation);
            }
        }
       
    }

   
}
