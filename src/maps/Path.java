package maps;

import java.util.LinkedList;

public class Path extends LinkedList<Point> {
	public void setStart(Point start) {
		add(start);
	}
	
	public boolean contains(Object o) {
		for(Point p : this) {
			if(p.equals(((Point)o)))
				return true;
		}
		return false;
	}
	
	public boolean contains(int x, int y) {
		return contains(new Point(x, y));
	}
	
	public int indexOf(Object o) {
		for(int i = 0; i < size(); i++) {
			if(get(i).equals(((Point)o)))
				return i;
		}
		return -1;
	}
	
	public int indexOf(int x, int y) {
		return indexOf(new Point(x, y));
	}
	
	public void setNext(Direction dir, NavMap map) {
		Point last = get(size() - 1);
		Point p = null;
		switch(dir) {
		case UP:
			p = new Point(last.getX(), last.getY()-1);
			break;
		case DOWN:
			p = new Point(last.getX(), last.getY()+1);
			break;
		case LEFT:
			p = new Point(last.getX()-1, last.getY());
			break;
		case RIGHT:
			p = new Point(last.getX()+1, last.getY());
			break;
		case NONE:
			return;
		}
		add(p);
	}
}
