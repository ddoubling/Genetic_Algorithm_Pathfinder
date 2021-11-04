package ga;

import maps.*;


public class FitnessCalculator {
	private static Point start, goal, point;
	private static NavMap map;
	private static int x, y;
	private static double cCount;
	private static double pLength;
	private static double oSurround;
	// Add the map to be able to calculate the fitness
	public static void setMap(NavMap map) {
		FitnessCalculator.map = map;
		start = map.getStart();
		goal = map.getGoal();
	}

	// Calculate individuals fitness
	static double getFitness(Individual individual) {
		Path path = createPath(individual);
		checkObstacle(path);
		pathCounter(path);
		checkSurroundings(path);
		// fitness = distance from goal + number of collisions/available map spaces
		return getDistance(path.get(path.size()-1), goal)+wallcollision()+pathWeight()+obstacleSurrounding();
	}
	
	// Calculate the Euclidean distance from the last point in the path to the goal
	public static double getDistance(Point a, Point b) {
		return Math.sqrt(Math.pow(b.getX()-a.getX(), 2) + Math.pow(b.getY()-a.getY(), 2));
	}
	
	// Create a path (list of points in 2D space) from the instructions in the individual
	public static Path createPath(Individual individual) {
		Path path = new Path();
		path.setStart(start);
		for(int i = 0; i < individual.size(); i++) {
			path.setNext(individual.getGene(i), map);
		} 
		return path;
	}
	
	//change population to 1 for immediate easier proof of method check
	static void checkObstacle(Path p) {
		  //i = each singular movement to new coordinate
		cCount = 0;  
		int collision = 0;  
	for (int i = 0; i < p.size(); i++) {
		
		//console print of movement No. and new position // Used for testing only
	//	System.out.println("position movement " + i + " " + p.get(i));
		
		//sets point to check as new position coordinate
		point = p.get(i);
		//sets x and y coordinates from point
		x = point.getX();
		y = point.getY();
		
		//console print to confirm original p coordinates are used // Used for testing only
		//System.out.println(x +" " +y);
		
		//boolean check to see if each set of coordinates is entering an obstacle space as path is built // Used for testing only
	//	System.out.println(map.isObstacle(x, y));
		
		//if true and hits robot, static counter of collisions for path is increased by 1
		if(map.isObstacle(x, y) == true) {
			// System print of count of collisions in given path // Used for testing only
		//	System.out.println(++collision);
			cCount = ++collision;
			//System.out.println(cCount);
			}
		}
	}
	
	 // take number of collisions and add to fitness.
	 static double wallcollision() {
		return 0.1*cCount;
	}
	 
	// for each movement until no more movement taken, increase pLength counter
	static void pathCounter(Path p) {
		pLength = 0;
	for (int i = 0; i < p.size(); i++) {
			++pLength;
			//System.out.println(pLength);
			
		}
	}
	
	// Weighted penalty using 1 / area of map * number of steps taken on path
	static double pathWeight() {
		pLength = 0.1  * pLength;
		//System.out.println(pLength);
		return pLength;
	}
	
	//looks at each movement along the path and checks the 8 point direction surrounding each point. If obstacle is found, counter is increased.
	static void checkSurroundings(Path p) {
		int obstacle = 0;
		for (int i = 0; i<p.size(); i++) {
			point = p.get(i);
			x =point.getX();
			y =point.getY();
			
			//above current position
			if(map.isObstacle(x, y-1) == true) {
				++obstacle;
			}
			
			//below current position
			if(map.isObstacle(x, y+1) == true) {
				++obstacle;
			}
			
			//left current position
			if(map.isObstacle(x-1, y) == true) {
				++obstacle;	
			}
			
			//right current position
			if(map.isObstacle(x+1, y) == true) {
				++obstacle;	
			}
			
			//up/right current position
			if(map.isObstacle(x+1, y-1) == true) {
				++obstacle;	
			}
			
			//down/right current position
			if(map.isObstacle(x+1, y+1) == true) {
				++obstacle;	
			}
			
			//up/left current position
			if(map.isObstacle(x-1, y-1) == true) {
				++obstacle;	
			}
			
			//down/right current position
			if(map.isObstacle(x-1, y+1) == true) {
				++obstacle;	
			}
		}
		oSurround = obstacle;
		
	}
	
	
	// take number of surrounding obstacles and add to fitness.
	static double obstacleSurrounding() {
	oSurround = 0.1 * oSurround;
	//System.out.println(oSurround);
	return oSurround;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
