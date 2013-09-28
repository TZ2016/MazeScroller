public class Maze {
	
	public final int width, height;
	private MazeGen maze;
	
	private int currX, currY;
	
	public Maze (MazeGen m) {
		maze = m;
		width = m.horiz;
		height = m.vert;
		currX = 0;
		currY = height-1;
	}
	
	private boolean canTurnLeft(int x, int y) {
		if (x == 0) return false;
		return maze.verticalWall(x-1, y);
	}
	private boolean canTurnRight(int x, int y) {
		if (x == width-1) return false;
		return maze.verticalWall(x, y);
	}
	private boolean canGoAhead(int x, int y) {
		if (y == 0) return false;
		return maze.horizontalWall(x, y-1);
	}
	private boolean canGoBack(int x, int y) {
		if (y == height-1) return false;
		return maze.horizontalWall(x, y);
	}
	
	// 0 ahead, -1 left, 1 right, 2 back
	Layer requestLayer (int direction) {
		switch(direction) {
		case 0:
			if (!canGoAhead(currX, currY)) System.err.println("invalid ahead");
			currY -= 1; // move up
			break;
		case -1:
			if (!canTurnLeft(currX, currY)) System.err.println("invalid left");
			currX -= 1; // move left
			break;
		case 1:
			if (!canTurnRight(currX, currY)) System.err.println("invalid right");
			currX += 1;
			break;
		case 2:
			if (!canGoBack(currX, currY)) System.err.println("invalid back");
			currY += 1;
			break;
		default:
			System.err.println("invalid direction");
			System.exit(1);
		}
		
	}
	
}
