public class Maze {
	private final int width, height;
	private static double mazeDensity = 0.15;
	private MazeGen maze;	
	
	private int counter;
	private int currX, currY;
	private int userFacing;
	
	public Maze (MazeGen m) {
		maze = m;
		width = m.horiz;
		height = m.vert;
		currX = 0;
		currY = height-1;
	}
	
	// userfacing
	private boolean canTurnLeft() {
		if (currX == 0) return false;
		return maze.verticalWall(currX-1, currY);
	}
	private boolean canTurnRight() {
		if (currX == width-1) return false;
		return maze.verticalWall(currX, currY);
	}
	private boolean canGoAhead() {
		if (currY == 0) return false;
		return maze.horizontalWall(currX, currY-1);
	}
	private boolean canGoBack() {
		if (currY == height-1) return false;
		return maze.horizontalWall(currX, currY);
	}
	private boolean isOut() {
		return currX == width-1 && currY == 0;
	}
	
	
	Scene getScene(int turningDirection) {
		counter = 0;
		userFacing = (userFacing + turningDirection) % 4;
		updatePosition();
		return new Scene(canTurnLeft(), canTurnRight());
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
	}
	
	// 0 ahead, -1 left, 1 right, 2 back
	Layer requestLayer (Layer oldLayer) {
		switch(direction) {
		case 0:
			if (!canGoAhead()) System.err.println("invalid ahead");
			currY -= 1; // move up
			break;
		case -1:
			if (!canTurnLeft()) System.err.println("invalid left");
			currX -= 1; // move left
			break;
		case 1:
			if (!canTurnRight()) System.err.println("invalid right");
			currX += 1;
			break;
		case 2:
			if (!canGoBack()) System.err.println("invalid back");
			currY += 1;
			break;
		default:
			System.err.println("invalid direction");
			System.exit(1);
		}
		if (isOut()) {
			throw new IllegalArgumentException("you are out!");
		}
		return new Layer(canTurnLeft(), canTurnRight(), oldLayer);
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
