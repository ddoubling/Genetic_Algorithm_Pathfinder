package maps;

public class Point {
	private int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point() {
		this(-1, -1);
	}
	
	// Creates a new point using a 1D vector of the 2D map
	public Point(int index, int width, int height) {
		this(index % width, (int) index / width);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}	
	
	public int toIndex(int width, int height) {
		return x + width * y;
	}
	
	@Override
	public boolean equals(Object o) {
		return equals(((Point)o).getX(), ((Point)o).getY());
	}
	
	public boolean equals(int x, int y) {
		return x == getX() && y == getY();
	}
	
	@Override
	public String toString() {
		return x+", "+y;
	}
}