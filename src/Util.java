import java.util.Scanner;


public class Util {
	static Timer timer = new Timer();
	static final int TIMEINTERVAL = 2000;
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
		String asdf = timedCommand();
		System.out.println(asdf);
	}
	
	static String timedCommand() {
		Scanner sc = new Scanner(System.in);
		String rtn = null;
		timer.reset();
		timer.start();
		while (timer.elapsed() < TIMEINTERVAL) {
			try {
				rtn = sc.next();
				if (timer.elapsed() >= TIMEINTERVAL)
					continue;
			} catch(Exception e) {
				System.out.println("command wrong");
				System.exit(1);
			}
		}
		timer.stop();
		return rtn;
	}
}
