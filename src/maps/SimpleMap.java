package maps;

public class SimpleMap extends NavMap {
	private final int WIDTH = 11;
	private final int HEIGHT = 8;
	private final byte[][] MAP = {
			{0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,1,1,1,0,0,0,0},
			{0,0,0,0,1,1,1,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0},
	};
	
	public SimpleMap(Point start, Point end) {
		super(start, end);
	}
	
	public byte[][] getMap() {
		return MAP;
	}
	
	public int getWidth() {
		return WIDTH;
	}
	
	public int getHeight() {
		return HEIGHT;
	}

}
