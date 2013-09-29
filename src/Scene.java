import java.util.Iterator;
import java.util.LinkedList;

public class Scene {
	static int DIMENSION = 0;
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
	
	Layer getBottom() {
		return scene.getFirst();
	}
	
	public String toString() {
		String rtn = "";
		Iterator<Layer> iter = scene.descendingIterator();
		while (iter.hasNext()) {
			rtn += iter.next().toString() + "\n";
		}
		return rtn;
	}
}
