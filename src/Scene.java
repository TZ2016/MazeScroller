import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Scene {
	static int DIMENSION = 0;
//	private List<Layer> scene = new ArrayList<Layer>();
	private LinkedList<Layer> scene = new LinkedList<Layer>();
	private Layer next;
	
	Scene(boolean left, boolean right) {
		for (int i = 0; i < DIMENSION; i++)
			scene.add(new Layer(left, right));
	}
	
	void updateNext(Layer layer) {
//		scene.add(next);
//		next = layer;
//		scene.remove(0);
		scene.removeFirst();
		scene.addLast(layer);
	}
	
	Layer getTop() {
//		return scene.get(DIMENSION - 1);
		return scene.getLast();
	}
	
	public String toString() {
		String rtn = "";
		for (Layer layer : scene) 
			rtn += layer.toString() + "\n";
		return rtn;
	}
}
