package ga;

import java.util.Arrays;

public class Population {

    private Individual[] individuals;
    private boolean sorted;
    
    public Population(int populationSize, boolean initialise) {
    	sorted = false;
        individuals = new Individual[populationSize];
        // Initialise population
        if (initialise) {
            // Loop and create individuals
            for (int i = 0; i < size(); i++) {
                Individual newIndividual = new Individual();
                newIndividual.generateIndividual();
                setIndividual(i, newIndividual);
            }
        }
    }

    public Individual getIndividual(int index) {
        return individuals[index];
    }
    
    public Individual[] getFittest(int size) {
    	if(!sorted) {
    		Arrays.sort(individuals, new SortByFitness());
    	}
    	Individual[] indi = new Individual[size];
    	for(int i = 0; i < size; i++)
    		indi[i] = individuals[i];
    	return indi;
    }
    
    public Individual getFittest() {
    	
    	return getFittest(1)[0];
    	
    }

    public int size() {
        return individuals.length;
    }

    public void setIndividual(int index, Individual indiv) {
        individuals[index] = indiv;
    }
    
    public double getAverageFitness() {
    	double average = 0;
    	for(Individual i : individuals) {
    		average += i.getFitness();
    	}
    	return average/individuals.length;
    }
}
