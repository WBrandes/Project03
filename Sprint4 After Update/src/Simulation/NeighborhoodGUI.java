package Simulation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

public class NeighborhoodGUI extends JFrame {

    public Truck truck;
    public static int size;
    public double ratio;
    public static int blocks;
    public static Position distCenter;

    public double gridSize;
    public double width_block;
    public double shift;

    NeighborhoodGUI(int size, int blocks, Position dc) {
        this.size = size;
        this.blocks = blocks;
        this.distCenter = dc;

        truck = new Truck(new Address((int) distCenter.getX(), (int) distCenter.getY()));
        width_block = 1.0 * size / (blocks - 1);
        
        if (width_block < 1) {
            width_block = 1;
        }
        gridSize = width_block / 10.0;
        if (gridSize < 1) {
            gridSize = 1;
        }
        ratio = size / ((blocks - 1) * 100.0);
        shift = gridSize / 2;

    }

    public void update(PriorityQueue<Order> orders, ArrayList<Order> deliveredOrders) {
        JPanel canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.setColor(Color.BLUE);

                for (int x = 0; x < blocks; x++) {
                    for (int y = 0; y < blocks; y++) {
                        g.drawRect((int) Math.round(width_block * x + shift), (int) Math.round(width_block * y + shift), (int) Math.round(width_block), (int) Math.round(width_block));
                    }
                }

                g.setColor(Color.GREEN);
                double x = distCenter.getX() - 100;
                double y = distCenter.getY() - 100;
                g.fillRect(getCoordinate(x), getCoordinate(y), (int) Math.round(gridSize), (int) Math.round(gridSize));

                g.setColor(Color.RED);
                Iterator<Order> iterator = orders.iterator();

                while (iterator.hasNext()) {
                    Address locationOfOrder = iterator.next().getAddress();
                    x = locationOfOrder.getX() - 100;
                    y = locationOfOrder.getY() - 100;
                    g.fillOval(getCoordinate(x), getCoordinate(y), (int) Math.round(gridSize), (int) Math.round(gridSize));
                }
                g.setColor(Color.ORANGE);
                iterator = deliveredOrders.iterator();
                while (iterator.hasNext()) {
                    Address locationOfOrder = iterator.next().getAddress();
                    x = locationOfOrder.getX() - 100;
                    y = locationOfOrder.getY() - 100;
                    g.fillOval(getCoordinate(x), getCoordinate(y), (int) Math.round(gridSize), (int) Math.round(gridSize));
                }

                g.setColor(Color.BLUE);
                x = truck.getAddress().getX() - 100;
                y = truck.getAddress().getY() - 100;
                
  
                g.fillRect(getCoordinate(x), getCoordinate(y), (int) Math.round(gridSize), (int) Math.round(gridSize));
            }
        };

        getContentPane().add(canvas);
        repaint();
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }
    
    
    

    private int getCoordinate(double val) {
        return (int) (ratio * (val + shift - gridSize / 2));
    }

    
}
