
public class Util {

	static void println(Object o) {
		System.out.println(o);
	}

	static int generateInteger(int min, int max) {
		int range = max - min + 1;
		return (int) ((Math.random() * range) + min);
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) 
			println(generateInteger(1, 5));
	}
}
