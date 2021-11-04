package main;

import ga.*;
import maps.*;

public class NavigationMain {
	// The value used to determine when to end the algorithm
	// The fitness is inverted meaning the lower the number the better
	// If the fitness is equal to or less than the FITNESS_CUTOFF value, we assume to have found a path
	//private static final double FITNESS_CUTOFF = 0.14;
	private static int fitCount = 0;
	private static double bestFit = 0;
	

	public static void main(String[] args) {
		// Select the map 
		/****************************
		 * Simple Map
//		 */
//		Point start = new Point(1,1);
//		Point end = new Point(8,6);
//		NavMap map = new SimpleMap(start, end);
		/****************************
		 * Hard Map
		 */
		Point start = new Point(1,1);
		Point end = new Point(8,10);
		NavMap map = new HardMap(start, end);
		/****************************/
		
		System.out.println(map);
		
		// Pass the map to the fitness calculator to compute fitness of paths
        FitnessCalculator.setMap(map);

        // Create an initial population containing 50 random individuals
        Population myPop = new Population(50, true);
        
        // Evolve our population until we reach an optimum solution
        int generationCount = 1;
       // while (myPop.getFittest().getFitness() > FITNESS_CUTOFF) { // The fitness is inverted, the lower the better
        while (fitCount*2 != generationCount) {
        	// Print out the fittest individual and some stats
            System.out.println("Generation: " + generationCount + " Fittest: " + myPop.getFittest().getFitness());
            System.out.println("Genome: " + myPop.getFittest());
            map.setPath(FitnessCalculator.createPath(myPop.getFittest()));
            System.out.println(map);
            if(myPop.getFittest().getFitness() != bestFit) {
            	bestFit = myPop.getFittest().getFitness();
            	fitCount = 0;
            } else {
            	fitCount++;
            }
            // Evolve population
            myPop = GeneticAlgorithm.evolvePopulation(myPop);
            generationCount++;
        }
        
        // Print solution
        System.out.println(fitCount);
        System.out.println("Solution found!");
        System.out.println("Generation: " + generationCount + " Fittest: " + myPop.getFittest().getFitness());
        System.out.println("Genome: " + myPop.getFittest());
        map.setPath(FitnessCalculator.createPath(myPop.getFittest()));
        System.out.println(map);
        
        
	}

}
