class Obstacle {

	final int damage;
	final int style; // a space holder for GUI

	Obstacle (int dmg, int stl) {
		style = stl;
		damage = dmg;
	}

	static Obstacle generateObstacle() {
		return new Obstacle(generateInteger(1, 5), generateInteger(1, 5));
	}

	private static int generateInteger(int min, int max) {
		int range = max - min + 1;
		return (int) ((Math.random() * range) + min);
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) 
			println(generateInteger(1, 5));
	}

	public static void println(Object o) {
		System.out.println(o);
	}
}
