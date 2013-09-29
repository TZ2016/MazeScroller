import java.util.*;
import org.newdawn.slick.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Driver extends BasicGame {	
	private Image playerImg;
	private static Player player;

	public Driver(String MazeScroller) {
		super(MazeScroller);
	}

	static Maze maze;
	static Scene scene;
	static Layer buffer;

//	@Override
//	public void init(GameContainer arg0) throws SlickException {
//		player = new Image("Player.jpg");
//		Scanner sc = new Scanner(System.in);
//		boolean legalargs = false;
//		int dif = 0, w = 0, h = 0;
//		while(!legalargs) {
//			Util.print("Choose difficulty, max width, max height\n");
//			try {
//				dif = sc.nextInt();
//				w = sc.nextInt();
//				h = sc.nextInt();
//			} catch(Exception e) {
//			} 
//			if (w > 0 && h > 0) 
//				legalargs = true;
//		}
//
//		Scene.DIMENSION = 5; // depends on user input
//		maze = new Maze(new MazeGen(w, h));
//		scene = maze.getScene(Util.UP);
//		buffer = maze.requestLayer(scene.getTop());
//
//
//		Util.print(maze.debugInfo());
//		Util.println("current scene: \n" + scene.toString());
//		Util.println("current buffer: \n" + buffer.toString());
//	}
//	@Override
//	public void update(GameContainer container, int delta) throws SlickException {
//	}
//
//	public void keyPressed(int key, char code) {
//		Util.println(key + " " + code);
//	}
//
//	@Override
//	public void render(GameContainer container, Graphics arg1) throws SlickException {
//		
//	}

	public static void main (String[] args) throws SlickException {

//		AppGameContainer app = new AppGameContainer(new Driver("MazeScroller"));
//		app.setDisplayMode(500, 500, false);
//		app.start();
		Scanner sc = new Scanner(System.in);
		boolean legalargs = false;
		int dif = 0, w = 0, h = 0;
		while(!legalargs) {
			Util.print("Choose difficulty, max width, max height\n");
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
		player = new Player(100); // player MUST be constructed after setting scene.DIMENSION
		maze = new Maze(new MazeGen(w, h));
		scene = maze.getScene(Util.UP);
		buffer = maze.requestLayer(scene.getTop());

		Util.print(maze.debugInfo());
		Util.println("current scene: \n" + scene.toString());
		Util.println("current buffer: \n" + buffer.toString());

		String command = "";
		sc = new Scanner(System.in);
		while (true) {
			try {
				command = sc.next();
			} catch(Exception e) {
				Util.println("command wrong");
				System.exit(1);
			}
			switch(command) {
			case "a":
				Util.println("moving left!");
				scene = maze.getScene(Util.LEFT);
				buffer = maze.requestLayer(scene.getTop());

				Util.print(maze.debugInfo());
				Util.println("current scene: \n" + scene.toString());
				Util.println("current buffer: \n" + buffer.toString());
				break;
			case "d":
				scene = maze.getScene(Util.RIGHT);
				buffer = maze.requestLayer(scene.getTop());
				Util.print(maze.debugInfo());
				Util.println("current scene: \n" + scene.toString());
				Util.println("current buffer: \n" + buffer.toString());
				break;
			case "s":
				scene = maze.getScene(Util.DOWN);
				buffer = maze.requestLayer(scene.getTop());
				Util.print(maze.debugInfo());
				Util.println("current scene: \n" + scene.toString());
				Util.println("current buffer: \n" + buffer.toString());
				break;
			default:
				scene.updateNext(buffer);
				buffer = maze.requestLayer(buffer);
				Util.print(maze.debugInfo());
				Util.println("current scene: \n" + scene.toString());
				Util.println("current buffer: \n" + buffer.toString());
			}
		}
	}
}
