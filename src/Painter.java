import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Painter {
	private final GraphicsContext _gc;
	private final QuadTree qTree;
	
	public Painter(GraphicsContext gc, QuadTree qTree){
		_gc = gc;
		this.qTree = qTree;
	}
	
	protected void draw() {
		drawPoints();
		drawQuads(qTree);
	}
	
	private void drawPoints() {
		_gc.setFill(Color.GREEN);
		
		for(Point point: qTree.getPoints()) {
			_gc.fillOval(point.getX(), point.getY(), 3, 3);
		}
	}
	
	private void drawQuads(QuadTree qTree) {
		_gc.setStroke(Color.BLUE);
		
		_gc.strokeRect(qTree.getX(), qTree.getY(), qTree.getW(), qTree.getH());
		
		if(qTree.hasChildren()) {
			for(QuadTree qtree: qTree.getChildren()) {
				drawQuads(qtree);
			}
		}
	}
}
