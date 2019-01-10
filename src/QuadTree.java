import java.util.ArrayList;
import java.util.List;

public class QuadTree {
	private int _x, _y, _w, _h, _cap;
	private List<Point> _points = new ArrayList<Point>();
	private QuadTree NW, NE, SW, SE;
	private boolean _hasChildren = false;
	
	public QuadTree(int x, int y, int w, int h, int cap, List<Point> points) {
		_x = x;
		_y = y;
		_w = w;
		_h = h;
		_cap = cap;

		checkContent(points);
	}
	
	protected int getX() {
		return _x;
	}
	
	protected int getY() {
		return _y;
	}
	
	protected int getW() {
		return _w;
	}
	
	protected int getH() {
		return _h;
	}
	
	protected boolean hasChildren() {
		return _hasChildren;
	}
	
	protected List<Point> getPoints(){
		return _points;
	}
	
	protected List<QuadTree> getChildren(){
		List<QuadTree> children = new ArrayList<QuadTree>();
		children.add(NW);
		children.add(NE);
		children.add(SW);
		children.add(SE);
		
		return children;
	}
	
	private void checkContent(List<Point> points) {
		for(Point point: points) {
			if(intersects(point)) {
				_points.add(point);
			}
		}
		System.out.println("Number points in Rect: " + _points.size());
		
		if(_points.size() > _cap) {
			subTrees();
			_hasChildren = true;
		}
	}
	
	private void subTrees() {
		NW = new QuadTree(_x, 		 _y, 		_w/2, _h/2, _cap, _points);
		NE = new QuadTree(_x + _w/2, _y, 		_w/2, _h/2, _cap, _points);
		SW = new QuadTree(_x, 		 _y + _h/2, _w/2, _h/2, _cap, _points);
		SE = new QuadTree(_x + _w/2, _y + _h/2, _w/2, _h/2, _cap, _points);
	}
	
	private boolean intersects(Point point) {
		System.out.print("P(" + point.getX() + "|" + point.getY() + ")");
		System.out.print("\t T(" + _x + "-" + _w + "|" + _y + "-" + _h + ")");
		
		if (	point.getX() >= _x &&
				point.getX() <= _x + _w &&
				point.getY() >= _y &&
				point.getY() <= _y + _h) {
			System.out.print(" T\n");
			return true;
		}
		System.out.println("");
		return false;
	}

}
