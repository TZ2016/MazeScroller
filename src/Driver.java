import java.io.*;
import java.util.*;

public class Driver {
	static Maze maze;
	static Scene scene;
	static Layer buffer;
	private static Timer timer = Util.timer;
	public static void main (String[] args) {

		Scanner sc = new Scanner(System.in);
		boolean legalargs = false;

		int dif = 0, w = 0, h = 0;
		while(!legalargs) {
			System.out.print("Choose difficulti and maze size: \n");
			try {
				dif = sc.nextInt();
				w = sc.nextInt();
				h = sc.nextInt();
			} catch(Exception e) {
			} 
			if (w > 0 && h > 0) 
				legalargs = true;
		}

		// initializing
		maze = new Maze(new MazeGen(w, h));
		scene = maze.getScene(Util.UP);
		buffer = maze.requestLayer(scene.getTop());
		String command = "";
		sc = new Scanner(System.in);
		while (true) {
			switch(command) {
			case "a":
				System.out.println("I'm moving left!");
				scene = maze.getScene(Util.LEFT);
				buffer = maze.requestLayer(scene.getTop());
				break;
			case "d":
				scene = maze.getScene(Util.RIGHT);
				buffer = maze.requestLayer(scene.getTop());
				break;
//			case "s":
//				scene = maze.getScene(REVERT);
//				buffer = maze.requestLayer(scene.getTop());
//				break;
			default:
				scene.updateNext(buffer);
				buffer = maze.requestLayer(buffer);
			}
		}
	}
}
