import java.util.*;
import org.newdawn.slick.*;

public class Driver extends BasicGame {	
	private Image player = new Image("player.jpg");
	
	public Driver(String MazeScroller) {
		super(MazeScroller);
	}
	
	static Maze maze;
	static Scene scene;
	static Layer buffer;
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		boolean legalargs = false;

		int dif = 0, w = 0, h = 0;
		while(!legalargs) {
			System.out.print("Choose difficulty, max width, max height\n");
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
		Scene.DIMENSION = 5; // depends on user input
		
		scene = maze.getScene(Util.UP);
		System.out.print(maze.debugInfo());
		System.out.println("current scene: \n" + scene.toString());
		
		buffer = maze.requestLayer(scene.getTop());
		System.out.println("current buffer: \n" + buffer.toString());
		
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
	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}
}
