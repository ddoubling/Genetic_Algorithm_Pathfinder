package maps;

public class HardMap extends NavMap {
	private final int WIDTH = 11;
	private final int HEIGHT = 12;
	private final byte[][] MAP = {
			{0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,1,1,1,0,0,0,0},
			{0,0,0,0,1,1,1,0,0,0,0},
			{0,0,0,0,1,1,1,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0},
			{1,1,1,1,1,1,1,0,0,0,0},
			{1,1,1,1,1,1,1,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0},
	};
	
	public HardMap(Point start, Point end) {
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
