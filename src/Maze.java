public class Maze {
	private final int width, height;
	private static double mazeDensity = 0.15;
	private MazeGen maze;	
	private int currX, currY;
	
	public Maze (MazeGen m) {
		maze = m;
		width = m.horiz;
		height = m.vert;
		currX = 0;
		currY = height-1;
	}
	
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
	
	// 0 ahead, -1 left, 1 right, 2 back
	Layer requestLayer (int direction, Layer oldLayer) {
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
