import java.io.*;
import java.util.*;

public class Driver {
	static Maze maze;
	static Scene scene;
	public static void main (String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int dif = 0, w = 0, h = 0;

		boolean legalargs = false;
		while(!legalargs) {
			System.out.print("Choose difficulti and maze size: \n[dif] [h] [w]\n");
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
		
		while (true) {
			String command = "";
			sc = new Scanner(System.in);
			try {
				command = sc.nextLine();
			} catch(Exception e) {
				System.out.println("comand wrong");
				System.exit(1);
			}
			if (command.equals("a")) {
				System.out.println("I'm moving left!");
			}
		}
		
	}
}
