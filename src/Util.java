import java.util.Scanner;

public class Util {
	static final int UP = 0; 
	static final int DOWN = 2; 
	static final int LEFT = 1; 
	static final int RIGHT = 3;

	static void println(Object o) {
		System.out.println(o);
	}

	static int generateInteger(int min, int max) {
		int range = max - min + 1;
		return (int) ((Math.random() * range) + min);
	}

	public static void main(String[] args) {
	}
}
