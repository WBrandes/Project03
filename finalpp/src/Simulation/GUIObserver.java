package Simulation;

// Kylie wrote this class.

import javax.swing.JFrame;


public class GUIObserver extends JFrame implements Observer {

    private Neighborhood grid;

   
    public GUIObserver(Neighborhood grid){
        this.grid = grid;
    }

   
    @Override
    public void update(Address currentLocation) {

        revalidate();
        repaint();

        try {
            Thread.sleep(100);
        } catch (Exception ex) {}
    }
}
