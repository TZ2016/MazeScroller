
public class Layer {
	final boolean left, right;
	private Obstacle[] layer = new Obstacle[Driver.maze.width];
	
	Layer(boolean l, boolean r, Layer oldLayer) {
		left = l;
		right = r;
	}
	
	protected generateRandom() {
		// random mutate layer and create obstacles
		
	}
	
	private boolean randomMazeDensity() {
		
	}
}
