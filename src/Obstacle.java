class Obstacle {

	final int damage;
	final int style; // a space holder for GUI

	Obstacle(){}
	
	Obstacle (int dmg, int stl) {
		style = stl;
		damage = dmg;
	}

	static Obstacle generateObstacle() {
		return new Obstacle(Util.generateInteger(1, 5), Util.generateInteger(1, 5));
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) 
			Util.println(Util.generateInteger(1, 5));
	}
}
