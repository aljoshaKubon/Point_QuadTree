import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class Main extends Application{
	protected static final int WIDTH = 960;
	protected static final int HEIGHT = 960;
	
	private Canvas canvas;
	protected static Painter painter;
	private QuadTree qTree;
	
	public static void main(String[] args) {launch(args);}

	@Override
	public void start(Stage stage) throws Exception {
		qTree = new QuadTree(0, 0, WIDTH, HEIGHT, 5, createPoints());
		canvas = new Canvas(WIDTH, HEIGHT);
		painter = new Painter(canvas.getGraphicsContext2D(), qTree);
		
		
		painter.draw();
		
		stage.setTitle("QuadTree Visualization");
		stage.setScene(new Scene(new Group(canvas)));
		stage.setOnCloseRequest(e -> {
			stage.close();
			System.exit(0);
		});
		stage.show();
	}
	
	private List<Point> createPoints() {
		List<Point> points = new ArrayList<Point>();
		
		for(int i = 0; i < 500; i++) {
			int x = ThreadLocalRandom.current().nextInt(Main.WIDTH);
			int y = ThreadLocalRandom.current().nextInt(Main.HEIGHT);
			
			points.add(new Point(x,y));
		}
		
		return points;
	}
	

}
