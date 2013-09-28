import java.io.*;
import java.util.*;

public class Driver {
	static Maze maze;
	static Scene scene;
	static Layer buffer;
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
		scene = maze.getScene();
		buffer = maze.requestLayer(0, scene.getTop());
		
		String command = "";
		while (true) {
			sc = new Scanner(System.in);
			try {
				command = sc.next();
			} catch(Exception e) {
				System.out.println("comand wrong");
				System.exit(1);
			}
			switch(command) {
			case "a":
				System.out.println("I'm moving left!");
				
			case "d":
				
			}
		}
		
	}
}
