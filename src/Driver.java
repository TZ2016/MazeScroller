import java.util.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Driver extends BasicGame {	
	
	public Driver(String MazeScroller) {
		super(MazeScroller);
	}
	
	public final static int SCREEN_WIDTH = 800;
	public final static int SCREEN_HEIGHT = 600;
	public final static int BORDER = 30;
	
	static Maze maze;
	static Player player;
	static Scene scene;
	static Layer buffer;
	static int interval;
	
	private int timePassed;
	private int key;
	
	//GUI
	private static int unitWidth;
	Image imgPlayer, imgObs, imgWall;
	
	private static void setUp() {
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
		
		// default value
		Scene.DIMENSION = 5; // depends on user input
		interval = 5000;
		
		// GUI
		unitWidth = (SCREEN_WIDTH - 2*BORDER) / (Scene.DIMENSION + 2);
		
		// init
		player = new Player(100, Scene.DIMENSION / 2);
		maze = new Maze(new MazeGen(w, h));
		scene = maze.getScene(Util.UP);
		buffer = maze.requestLayer(scene.getTop());
		
		System.out.print(maze.debugInfo());
		System.out.println("current scene: \n" + scene.toString());
		System.out.println("current buffer: \n" + buffer.toString());
	}
	
	public void init(GameContainer arg0) throws SlickException {
		timePassed = 0;
		imgPlayer = new Image("res/player.jpg");
		imgObs = new Image("res/obstacle.jpg");
		imgWall = new Image("res/wall.jpg");
	}
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		timePassed += delta;
		if (timePassed > interval) {
			performAction(key);
			key = 0;
			timePassed = 0;
		}
	}
	
	private void performAction (int command) {
		switch(command) {
		case 203: // left arrow
			player.position--;
			if (scene.getBottom().getObstacle(player.position) != null) {
				System.out.println("stupid decision!");
				player.position++;
			}
			break;
		case 205: // right arrow
			player.position++;
			if (scene.getBottom().getObstacle(player.position) != null) {
				System.out.println("stupid decision!");
				player.position--;
			}
			break;
		case 30:
			Util.println("moving left!");
			scene = maze.getScene(Util.LEFT);
			buffer = maze.requestLayer(scene.getTop());
			
			Util.print(maze.debugInfo());
			break;
		case 32:
			scene = maze.getScene(Util.RIGHT);
			buffer = maze.requestLayer(scene.getTop());
			Util.print(maze.debugInfo());
			Util.println("current buffer: \n" + buffer.toString());
			break;
		case 31:
			scene = maze.getScene(Util.DOWN);
			buffer = maze.requestLayer(scene.getTop());
			Util.print(maze.debugInfo());
			break;
		default: // go ahead
			scene.updateNext(buffer);
			buffer = maze.requestLayer(buffer);
			Util.print(maze.debugInfo());
		}

		Util.println("current scene: \n" + scene.toString());
		Util.println("current buffer: \n" + buffer.toString());
		System.out.println("player is at " + player.position);
		
		if (scene.getBottom().getObstacle(player.position) != null) {
			throw new IllegalArgumentException("YOU DIE!");
		}
	}
	
	public void keyPressed(int key, char code) {
		
		System.out.println(key + " | " + code);
		
		if (!maze.canTurn(Util.LEFT) && key == 30) return; 
		if (!maze.canTurn(Util.RIGHT) && key == 32) return; 
		if (!maze.canTurn(Util.DOWN) && key == 31) return; 
		
		if (player.position == 0 && key == 203) return; 
		if (player.position == Scene.DIMENSION && key == 205) return; 
		this.key = key;
	}
	
	@Override
	public void render(GameContainer container, Graphics arg1) throws SlickException {
		for (int height = 0; )
	}
	
	public static void main (String[] args) throws SlickException {
		setUp();
		
		AppGameContainer app = new AppGameContainer(new Driver("haha"));
		app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
		app.setVSync(true);
		app.setMaximumLogicUpdateInterval(500);
		app.setMinimumLogicUpdateInterval(100);
		
		app.start();
	}
}
