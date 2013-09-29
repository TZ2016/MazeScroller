public class Maze {
	private final int width, height;
	private static double mazeDensity = 0.5;
	private MazeGen maze;	
	
	private int counter;
	private int currX, currY;
	private int userFacing;
	
	public Maze (MazeGen m) {
		maze = m;
		width = m.horiz;
		height = m.vert;
		currX = 0;
		currY = height;
		counter = 0;
	}
	
	private boolean canTurn(int turningDirection) {
		int absoluteDir = (turningDirection + userFacing) % 4;
		switch(absoluteDir) {
		case Util.UP:
			if (currY == 0) return false;
			return !maze.horizontalWall(currX, currY-1);
		case Util.DOWN:
			if (currY == height-1) return false;
			return !maze.horizontalWall(currX, currY);
		case Util.LEFT:
			if (currX == 0) return false;
			return !maze.verticalWall(currX-1, currY);
		case Util.RIGHT:
			if (currX == width-1) return false;
			return !maze.verticalWall(currX, currY);
		default:
			System.err.println("impossible direction!");
			return true;
		}
	}
	private boolean isOut() {
		return currX == width-1 && currY == 0;
	}
	private void updatePosition() {
		switch(userFacing){
		case Util.UP: // up
			currY--;
			break;
		case Util.DOWN: // down
			currY++;
			break;
		case Util.LEFT: // left
			currX--;
			break;
		case Util.RIGHT: // right
			currX++;
			break;
		default:
			System.err.println("invalid direction");
		}
		if (isOut())
			throw new IllegalArgumentException("You're out!");
	}
	
	Scene getScene(int turningDirection) {
		counter = 0;
		userFacing = (userFacing + turningDirection) % 4;
		updatePosition();
		return new Scene(canTurn(Util.LEFT), canTurn(Util.RIGHT));
	}

	// default to going forward
	Layer requestLayer (Layer oldLayer) {
		if (!canTurn(Util.UP))
			return new Layer();
		counter++;
		if (counter == Scene.DIMENSION) {
			updatePosition();
			counter = 0;
		}
		return new Layer(canTurn(Util.LEFT), canTurn(Util.RIGHT), oldLayer);
	}
	
	String debugInfo() {
		String output = "======Maze debug info======\n";
		output += maze.toString() + "\n";
		output += "User is at X=" + currX + ",Y=" + currY + 
				", facing" + userFacing + "\n";
		output += "counter=" + counter + "\n";
		output += "User can move to: ";
		if (canTurn(Util.UP)) output += "up ";
		if (canTurn(Util.DOWN)) output += "down ";
		if (canTurn(Util.LEFT)) output += "left ";
		if (canTurn(Util.RIGHT)) output += "right ";
		output += "\n";
		output += "User is at X = " + currX + ", Y = " + currY + 
				", facing " + userFacing + "\n";
		return output;
		
	}
	
	int getWidth() {
		return width;
	}
	int getHeight() {
		return height;
	}	
	double getDensity() {
		return mazeDensity;
	}
}
