package ga;

import java.util.Random;

import maps.Direction;

public class GeneticAlgorithm {
    /* GA parameters */
    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.05;
    private static final int tournamentSize = 5;
    
    /* Public methods */
    
    // Evolve a population
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.size(), false);

        // Crossover population
        // Loop over the population size and create new individuals with
        // crossover
        
        
        
        
        Individual fittest = findFittest(pop);
       // System.out.println(fittest);
        //System.out.println(fittest.getFitness());
        
        // number of fittest individual to be carried to next generation // Note this size must be the same or smaller than population
        int elite = 10;
        // for number of elite to set fittest in new population
        for (int j=0; j < elite; j++) {//System.out.println(fittest);
        newPopulation.setIndividual(j, fittest);
      //  System.out.println(newPopulation.getIndividual(j));
        }
        //population size munus elite to use ensure only space left is used
        for (int  i=0; i < pop.size()-elite; i++) {
            Individual indiv1 = tournamentSelection(pop);
            Individual indiv2 = tournamentSelection(pop);
            Individual newIndiv = crossover(indiv1, indiv2);
            // i+ elite so as to continue index from setting elite individuals
            newPopulation.setIndividual(i+elite, newIndiv);
        }

        // Mutate population - elite, removing the fittest from mutation ensures that there is always at least the fittest available from previous generations
        for (int i = 0; i < newPopulation.size()-elite; i++) {
            mutate(newPopulation.getIndividual(i+elite));
        }


    //   System.out.println(newPopulation.getIndividual(0));
   //    System.out.println(newPopulation.getIndividual(0).getFitness());
    
        return newPopulation;
    }
    
 // 
    
    private static Individual findFittest(Population pop) {
    	Individual fittest = pop.getFittest();
    	//System.out.println(fittest+"here");
    	return fittest;
    }
    
    

    // Crossover individuals
    private static Individual crossover(Individual indiv1, Individual indiv2) {
        Individual newSol = new Individual();
        // Loop through genes
        for (int i = 0; i < indiv1.size(); i++) {
            // Crossover
            if (Math.random() <= uniformRate) {
                newSol.setGene(i, indiv1.getGene(i));
            } else {
                newSol.setGene(i, indiv2.getGene(i));
            }
        }
        return newSol;
    }

    // Mutate an individual
    private static void mutate(Individual indiv) {
    	Random rand = new Random();
        // Loop through genes
        for (int i = 0; i < indiv.size(); i++) {
            if (Math.random() <= mutationRate) {
                // Create random gene
            	Direction gene = Direction.values()[rand.nextInt(Direction.values().length)];
                indiv.setGene(i, gene);
            }
        }
    }

    // Select individuals for crossover
    private static Individual tournamentSelection(Population pop) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        // For each place in the tournament get a random individual
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.setIndividual(i, pop.getIndividual(randomId));
        }
        // Get the fittest individual across entire population
        Individual fittest = tournament.getFittest();
        return fittest;
    }
}
