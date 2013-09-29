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
	
	public final static int SCREEN_WIDTH = 1280;
	public final static int SCREEN_HEIGHT = 1100;
	public final static int BORDER = 30;
	
	static Maze maze;
	static Player player;
	static Scene scene;
	static Layer buffer;
	static int interval;
	
	private boolean win;
	private boolean lose;
	private int timePassed;
	private int key;
	
	//GUI
	private static int unitWidth;
	Image imgPlayer, imgObs, imgWall, imgWin, imgLose;
	
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
		Scene.DIMENSION = 10; // depends on user input
		interval = 250;
		
		// GUI
		unitWidth = (SCREEN_WIDTH - 2*BORDER) / (Scene.DIMENSION + 2);
		
		// init
		player = new Player(100, Scene.DIMENSION / 2);
		maze = new Maze(new MazeGen(w, h));
		scene = maze.getScene(Util.UP);
		buffer = maze.requestLayer(scene.getTop());
		
		System.out.print(maze.debugInfo());
//		System.out.println("current scene: \n" + scene.toString());
//		System.out.println("current buffer: \n" + buffer.toString());
	}
	
	public void init(GameContainer arg0) throws SlickException {
		timePassed = 0;
		win = lose = false;
		imgPlayer = new Image("player.jpg");
		imgObs = new Image("obstacle.jpg");
		imgWall = new Image("obstacle.jpg");
		imgWin = new Image("win.jpg");
		imgLose = new Image("sad.jpg");
	}
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		timePassed += delta;
		if (key == 203) {
			player.position--;
			if (player.position == -1 ||
				scene.getBottom().getObstacle(player.position) != null) {
//				System.out.println("stupid decision!");
				player.position++;
			}
			key = 0;
		}
		if (key == 205) {
			player.position++;
			if (player.position == Scene.DIMENSION ||
				scene.getBottom().getObstacle(player.position) != null) {
//				System.out.println("stupid decision!");
				player.position--;
			}
			key = 0;
		}
		if (timePassed > interval) {
			try {
				performAction(key);
			}
			catch (IllegalArgumentException e) {
				win = true;
			}
			catch (ArrayIndexOutOfBoundsException f) {
				lose = true;
			}
			key = 0;
			timePassed = 0;
		}
	}
	
	private void performAction (int command) {
		switch(command) {
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

//		Util.println("current scene: \n" + scene.toString());
//		Util.println("current buffer: \n" + buffer.toString());
//		System.out.println("player is at " + player.position);
		
		if (scene.getBottom().getObstacle(player.position) != null) {
			throw new ArrayIndexOutOfBoundsException();
//			System.exit(1);
		}
	}
	
	public void keyPressed(int key, char code) {
		
//		System.out.println(key + " | " + code);
		
		if (!maze.canTurn(Util.LEFT) && key == 30) return; 
		if (!maze.canTurn(Util.RIGHT) && key == 32) return; 
		if (!maze.canTurn(Util.DOWN) && key == 31) return; 
		
		if (player.position == 0 && key == 203) return; 
		if (player.position == Scene.DIMENSION && key == 205) return; 
		this.key = key;
	}
	
	@Override
	public void render(GameContainer container, Graphics arg1) throws SlickException {
		
		if (win) {
			imgWin.draw(BORDER, BORDER, scene.DIMENSION*unitWidth, scene.DIMENSION*unitWidth);
			return;
		}
		if (lose) {
			imgLose.draw(BORDER, BORDER, scene.DIMENSION*unitWidth, scene.DIMENSION*unitWidth);
			return;
		}
		
		int height = BORDER;
		Iterator<Layer> iter = scene.layers();
		while (iter.hasNext()) {
			Layer curr = iter.next();
			int width = BORDER;
			// draw left wall
			if (!curr.left)
				imgWall.draw(width, height, unitWidth, unitWidth);
			width += unitWidth;
			
			// draw obstacles
			for (int obs = 0; obs < Scene.DIMENSION; obs++) {
				if (curr.getObstacle(obs) != null) {
					imgObs.draw(width, height, unitWidth, unitWidth);
				}
				width += unitWidth;
			}
			
			// draw right wall
			if (!curr.right)
				imgWall.draw(width, height, unitWidth, unitWidth);

			height += unitWidth;
		}
		imgPlayer.draw(BORDER+(player.position+1)*unitWidth, BORDER+unitWidth*(scene.DIMENSION-1), unitWidth, unitWidth);
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
