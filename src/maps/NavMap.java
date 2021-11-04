package maps;

public abstract class NavMap {
	protected byte[][] path;
	protected Point start, goal;
	
	public NavMap(Point start, Point end) {
		path = new byte[getHeight()][getWidth()];
		this.start = start;
		this.goal = end;
	}
	
	// Abstract methods which have to be overridden in subclasses
	abstract public byte[][] getMap();
	abstract public int getWidth();
	abstract public int getHeight();
	
	public boolean isObstacle(int x, int y) {
		try {
			return getMap()[y][x] == 1;
		} catch (ArrayIndexOutOfBoundsException ex) {
			return true; // Out of map is treated as an obstacle
		}
	}
	
	public Point getStart() {
		return start;
	}

	public void setStart(Point start) {
		this.start = start;
	}

	public Point getGoal() {
		return goal;
	}

	public void setGoal(Point goal) {
		this.goal = goal;
	}

	// total number of grids in the map
	public int getSize() {
		return getWidth() * getHeight();
	}
	
	// Checks if a point is within the map
	public boolean isInMap(Point p) {
		return p.getX() >= 0 && p.getY() >= 0 && p.getX() < getWidth() && p.getX() < getHeight();
	}
	
	// Transforms the 2D map into a 1D vector representation
	public byte[] getMapVector() {
		byte[] map = new byte[getWidth()*getHeight()];
		int k = 0;
		for(int i = 0; i < getHeight(); i++) {
			for(int j = 0; j < getWidth(); j++) {
				map[k] = getMap()[i][j];
				k++;
			}

		}
		return map;
	}
	
	// Adds a path to this map for visualisation purposes
	public void setPath(Path path) {
		this.path = new byte[getHeight()][getWidth()];
		if(path != null)
			for(Point p : path) {
				try {
					this.path[p.getY()][p.getX()] = 1;
				} catch (ArrayIndexOutOfBoundsException ex) {} // Out of map is ignored for drawing
			}
	}
	
	@Override
    public String toString() {
		String map = "";
		for(int i = 0; i < getHeight(); i++) {
			for(int j = 0; j < getWidth(); j++) {
				if(getMap()[i][j] == 1 && path[i][j] == 1)
					map += "C";
				else if(getMap()[i][j] == 1)
					map += "X";
				else if(start.equals(j, i))
					map += "S";
				else if(goal.equals(j, i))
					map += "G";
				else if(path[i][j] == 1)
					map += "-";
				else
					map += "0";
				map += " ";
			}
			System.out.println(map);
			map = "";
		}
		return map;
    }
}
