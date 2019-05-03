package Simulation;

public class GUIObserver implements Observer {

    private  NeighborhoodGUI neighborhood;



    public GUIObserver(NeighborhoodGUI neighborhood){
        this.neighborhood = neighborhood;

    }

    @Override
    public void update(Truck truck) {

      
        neighborhood.setVisible(true);


    }
}
