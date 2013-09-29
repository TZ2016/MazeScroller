class Obstacle {
	final int damage;
	final int style; // a space holder for GUI
	final static int WALL = 0;
	
	Obstacle (int dmg, int stl) {
		style = stl;
		damage = dmg;
	}

	static Obstacle generateObstacle() {
		return new Obstacle(Util.generateInteger(1, 5), Util.generateInteger(1, 5));
	}
	
	public String toString() {
		return "X";
	}
}
