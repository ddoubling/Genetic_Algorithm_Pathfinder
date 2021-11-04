package ga;

import java.util.Comparator;
import java.util.Random;

import maps.*;

// Comparator used for sorting individuals based on their fitness
class SortByFitness implements Comparator<Individual> 
{ 
	public int compare(Individual a, Individual b) 
    { 
    	return Double.compare(a.getFitness(), b.getFitness()); 
    } 
}

public class Individual {
	// Maximum number of instructions to reach the goal
	private static final int GENE_LENGTH = 40;
	
	private Direction[] genes; // The genome is an array of directions which tell the robot how to move
    private double fitness = Double.POSITIVE_INFINITY; // The lower the fitness the better so we start of as high as possible
    private Random rand;
    
    public Individual() {
    	rand = new Random();
    	genes = new Direction[GENE_LENGTH];
    }

    // Create a random individual
    public void generateIndividual() {
        for (int i = 0; i < size(); i++) {
        	// Select a random value from all available directions
        	setGene(i, Direction.values()[rand.nextInt(Direction.values().length)]);
        }
    }

    public Direction getGene(int index) {
        return genes[index];
    }
    
    public Direction[] getGenes() {
    	return genes;
    }

    public void setGene(int index, Direction value) {
        genes[index] = value;
        fitness = Double.POSITIVE_INFINITY;
    }

    public int size() {
        return GENE_LENGTH;
    }

    public double getFitness() {
    	// If the fitness is still at the starting value, calculate it first
        if (fitness == Double.POSITIVE_INFINITY) {
            fitness = FitnessCalculator.getFitness(this);
        }
        return fitness;
    }

    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < size(); i++) {
        	// Exclude NONE direction when printing
            if(getGene(i) != Direction.NONE)
            	geneString += getGene(i) + " ";
        }
        return geneString;
    }

}
