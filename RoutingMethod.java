import java.util.ArrayList;

public interface RoutingMethod {
	
	public static Direction[] directions = new Direction[] {
			Direction.NORTH, 
			Direction.EAST, 
			Direction.SOUTH, 
			Direction.WEST};
	
	//0 = N, 1 = E, 2 = S, 3 = W
	public ArrayList<Command> findPath(double endLocationX, double endLocationY);
	
}
