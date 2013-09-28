import java.util.ArrayList;

public class Layer {
	final boolean left, right;
	private Obstacle[] layer = new Obstacle[Driver.maze.getWidth()];
	private Layer prevLayer;

	Layer(boolean l, boolean r, Layer oldLayer) {
		this(l, r);
		prevLayer = oldLayer;
		generateRandom();
	}

	Layer(boolean l, boolean r) {
		left = l;
		right = r;
		layer = new Obstacle[Driver.maze.getWidth()];
	}

	void generateRandom() {
		// create random obstacles for this layer, leaving at least one of the previous layer's opening also open
		int guaranteedOpening = prevLayer.randomOpening();
		for (int i = 0; i < Driver.maze.getWidth(); i++)
			if (underMazeDensity() && i != guaranteedOpening)
				setObstacle(i, Obstacle.generateObstacle());
	}

	private int randomOpening() {
		ArrayList<Integer> openings = new ArrayList<Integer>();
		int count = 0;
		for (int i = 0; i < Driver.maze.getWidth(); i++) 
			if (getObstacle(i) == null) {
				openings.add(i);
				count++;
			}
		return openings.get(Util.generateInteger(0, count));
	}

	private boolean underMazeDensity() {
		if (Math.random() < Driver.maze.getDensity())
			return true;
		return false;
	}

	Obstacle getObstacle(int index) {
		return layer[index];
	}
	void setObstacle(int index, Obstacle o) {
		layer[index] = o;
	}
	
	public String toString() {
		boolean first = true;
		String rtn = "";
		for (Obstacle o : layer) {
			if (first) 
				first = false;
			else 
				rtn += " ";
			rtn += o;
		}
		return rtn;
	}
}
