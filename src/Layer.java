import java.util.ArrayList;

public class Layer {
	final boolean left, right;
	private Obstacle[] layer = new Obstacle[Driver.maze.getWidth()];
	private Layer prevLayer;
	
	Layer(boolean l, boolean r, Layer oldLayer) {
		left = l;
		right = r;
		prevLayer = oldLayer;
		generateRandom();
	}
	
	void generateRandom() {
		// create random obstacles for this layer
		int guaranteedOpening = prevLayer.randomOpening();
		for (int i = 0; i < Driver.maze.getWidth(); i++)
			if (randomMazeDensity() && i != guaranteedOpening)
				layer[i] = Obstacle.generateObstacle();
	}
	
	int randomOpening() {
		ArrayList<Integer> openings = new ArrayList<Integer>();
		int count = 0;
		for (int i = 0; i < Driver.maze.getWidth(); i++) 
			if (layer[i] == null) {
				openings.add(i);
				count++;
			}
		return openings.get(Util.generateInteger(0, count));
	}
	
	private boolean randomMazeDensity() {
		if (Math.random() < Driver.maze.getDensity())
			return true;
		return false;
	}
	
	Obstacle getObstacle(int index) {
		return layer[index];
	}
}
