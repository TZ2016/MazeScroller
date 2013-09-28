import java.io.*;
import java.util.Scanner;

public class Driver {
	static Maze maze;
	static Scene scene;
	public static void main (String[] args) {

		System.out.print("Choose difficulti and maze size: [dif] [h] [w]");
		Scanner sc = new Scanner(System.in);
		
		int dif = 0, w = 0, h = 0;

		try {
			dif = sc.nextInt();
			w = sc.nextInt();
			h = sc.nextInt();
		} catch(Exception e) {
			System.out.println("diff w h wrong");
			System.exit(1);
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
