package me.willyb.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import javax.swing.JPanel;

import me.willyb.neighborhood.Address;
import me.willyb.neighborhood.Neighborhood;

public class NeighborhoodGUI extends JPanel {
	
	public HashMap<Address, Boolean> addresses = new HashMap<Address, Boolean>();
	public Address distributionCenter;
	
	//Scale of the GUI. This is multiplied by 100 to get the X & Y dimensions, on-screen, of the GUI.
	//Defaults to 1.0.
	private double scale;
	
	//The number of streets to display. Defaults to 20.
	private int streetCount;
	
	//The size of the GUI. Defaults to 100, and is otherwise equal to 100 * scale.
	private int guiSize;
	
	//The distance to move the roads away from the edges of the GUI when drawing them. This is done so that the top-left most roads can be seen properly.
	private int roadOffset;
	
	public static Color truckColor = new Color(121,147,158,255);
	
	public NeighborhoodGUI() {
		
		this.scale = 1.0;
		this.streetCount = 20;
		this.guiSize = 100;
		this.roadOffset = 2;
		distributionCenter = Neighborhood.getNeighborhood().distributionCenter;
		
	}
	
	public NeighborhoodGUI(double scale, int streetCount) {
		
		this.scale = scale;
		this.streetCount = streetCount;
		this.guiSize = (int) (streetCount * 100 * scale);
		this.roadOffset = (int) (scale*1000) / (streetCount*2);
		distributionCenter = Neighborhood.getNeighborhood().distributionCenter;
		
	}
	
	public void paintComponent(Graphics g) {
		
		g.setColor(Color.BLACK);
		
		for(int i=0; i<streetCount; i++) {
			
			int currentPosition = (int) (scale * i * 100) + roadOffset;
			
			g.drawLine(0, currentPosition, guiSize, currentPosition);
			g.drawLine(currentPosition, 0, currentPosition, guiSize);
			
		}
		
		for(Address a : addresses.keySet()) {
			
			if(addresses.get(a)) {
				
				g.setColor(Color.GREEN);
				
			} else {
				
				g.setColor(Color.RED);
				
			}
			
			g.fillOval((int) (scale * a.x) + roadOffset, (int) (scale * a.y) + roadOffset, (int) (10*scale), (int) (10*scale));
			
		}
		
		g.setColor(Color.BLUE);
		g.fillRect((int) (scale * distributionCenter.x) + roadOffset, (int) (scale * distributionCenter.y) + roadOffset, (int) (10*scale), (int) (10*scale));
		
		g.setColor(truckColor);
		int xEnlargement = Main.truck.direction % 2 == 0 ? 0 : 5;
		int yEnlargement = Math.abs(xEnlargement - 5);
		g.fillRect((int) (scale * Main.truck.x) + roadOffset - (int) (5 * scale), (int) (scale * Main.truck.y) + roadOffset - (int) (5 * scale), (int) ((10 + xEnlargement)*scale), (int) ((10 + yEnlargement)*scale));
		
		
		
	}
    
    
}
