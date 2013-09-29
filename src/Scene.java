import java.util.ArrayList;

public class Scene {
	static final int HEIGHT = 20;
	private ArrayList<Layer> scene = new ArrayList<Layer>();
	private Layer next;
	
	Scene(boolean left, boolean right) {
		for (int i = 0; i < HEIGHT; i++)
			scene.add(new Layer(left, right));
	}
	
	void updateNext(Layer layer) {
		scene.add(next);
		next = layer;
		scene.remove(0);
	}
	
	Layer getTop() {
		return scene.get(HEIGHT - 1);
	}
}
