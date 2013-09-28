import java.util.ArrayList;


public class Scene {
	private static final int HEIGHT = 20;
	private boolean vertical;
	private ArrayList<Layer> scene = new ArrayList<Layer>();
	private Layer next;
	
	Scene(boolean vert) {
		for (int i = 0; i < HEIGHT; i++)
			scene.add(new Layer());
		vertical = vert;
	}
	
	void updateNext(Layer layer) {
		scene.add(next);
		next = layer;
		scene.remove(0);
	}
	
	
}
