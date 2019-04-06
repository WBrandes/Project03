import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class NeighborhoodGUI extends Canvas
{
	//Scale of the GUI. This is multiplied by 100 to get the X & Y dimensions, on-screen, of the GUI.
	//Defaults to 1.0.
	private double scale;
	
	//The number of streets to display. Defaults to 20.
	private int streetCount;
	
	//The size of the GUI. Defaults to 100, and is otherwise equal to 100 * scale.
	private int guiSize;
	
	//The distance to move the roads away from the edges of the GUI when drawing them. This is done so that the top-left most roads can be seen properly.
	private int roadOffset;
	
	public NeighborhoodGUI() {
		
		this.scale = 1.0;
		this.streetCount = 20;
		this.guiSize = 100;
		this.roadOffset = 2;
		
	}
	
	public NeighborhoodGUI(double scale, int streetCount) {
		
		this.scale = scale;
		this.streetCount = streetCount;
		this.guiSize = (int) (streetCount * 100 * scale);
		this.roadOffset = (int) (scale*1000) / (streetCount*2);
		
	}
	
	public void paint(Graphics g) {
		
		g.setColor(Color.BLACK);
		
		for(int i=0; i<streetCount; i++) {
			
			int currentPosition = (int) (scale * i * 100) + roadOffset;
			
			g.drawLine(0, currentPosition, guiSize, currentPosition);
			g.drawLine(currentPosition, 0, currentPosition, guiSize);
			
		}
		
	}
    
    
}
