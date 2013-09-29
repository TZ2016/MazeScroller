import java.util.LinkedList;

public class Scene {
	static int DIMENSION;
	private LinkedList<Layer> scene = new LinkedList<Layer>();
	
	Scene(boolean left, boolean right) {
		for (int i = 0; i < DIMENSION; i++)
			scene.add(new Layer(left, right));
	}
	
	void updateNext(Layer layer) {
		scene.removeFirst();
		scene.addLast(layer);
	}
	
	Layer getTop() {
		return scene.getLast();
	}
	
	public String toString() {
		String rtn = "";
		for (Layer layer : scene) 
			rtn += layer.toString() + "\n";
		return rtn;
	}
}
