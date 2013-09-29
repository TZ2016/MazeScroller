import java.util.*;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Driver extends BasicGame {
	
	static Maze maze;
	static Scene scene;
	static Layer buffer;
	
	public Driver(String title) {
		super(title);
	}
	
	@Override
	public void init(GameContainer arg0) throws SlickException {
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

		Scene.DIMENSION = 5; // depends on user input
		maze = new Maze(new MazeGen(w, h));
		scene = maze.getScene(Util.UP);
		buffer = maze.requestLayer(scene.getTop());
		
		
		System.out.print(maze.debugInfo());
		System.out.println("current scene: \n" + scene.toString());
		System.out.println("current buffer: \n" + buffer.toString());
	}
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
//		System.out.println(delta);
	}
	
	public void keyPressed(int key, char code) {
		System.out.println(key + " " + code);
	}
	
	@Override
	public void render(GameContainer container, Graphics arg1) throws SlickException {
		
	}
	
	public static void main (String[] args) throws SlickException {

		AppGameContainer app = new AppGameContainer(new Driver("haha"));
		app.setDisplayMode(800, 600, false);
		app.start();
		
		Scanner sc = new Scanner(System.in);
		String command = "";
		sc = new Scanner(System.in);
		while (true) {
			try {
				command = sc.next();
			} catch(Exception e) {
				System.out.println("command wrong");
				System.exit(1);
			}
			switch(command) {
			case "a":
				System.out.println("moving left!");
				scene = maze.getScene(Util.LEFT);
				buffer = maze.requestLayer(scene.getTop());
				
				System.out.print(maze.debugInfo());
				System.out.println("current scene: \n" + scene.toString());
				System.out.println("current buffer: \n" + buffer.toString());
				break;
			case "d":
				scene = maze.getScene(Util.RIGHT);
				buffer = maze.requestLayer(scene.getTop());
				System.out.print(maze.debugInfo());
				System.out.println("current scene: \n" + scene.toString());
				System.out.println("current buffer: \n" + buffer.toString());
				break;
			case "s":
				scene = maze.getScene(Util.DOWN);
				buffer = maze.requestLayer(scene.getTop());
				System.out.print(maze.debugInfo());
				System.out.println("current scene: \n" + scene.toString());
				System.out.println("current buffer: \n" + buffer.toString());
				break;
			default:
				scene.updateNext(buffer);
				buffer = maze.requestLayer(buffer);
				System.out.print(maze.debugInfo());
				System.out.println("current scene: \n" + scene.toString());
				System.out.println("current buffer: \n" + buffer.toString());
			}
		}
	}

}
