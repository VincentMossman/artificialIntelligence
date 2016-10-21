package presentation;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import genetics.Chromosome;

public class ColorGeneticsApp 
{
	public static void main(String[] args)
    {
    	@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
    	
    	//Variables
    	Chromosome target;
    	int numGeneration, numCrossover, numMutation,
            numNewblood, numSelection, numChromosome;
    	ArrayList<Chromosome> chromosomes = new ArrayList<Chromosome>();
    	
    	while (true)
    	{	
    		System.out.print("What is your input bit string? ");
    		target = new Chromosome(in.next());
    		System.out.println("Target color: r=" +
    					    target.getIntegerR() + " g=" + 
    					    target.getIntegerG() + " b=" + 
    					    target.getIntegerB());
    		
    		//Loop for simple data validation
    		while (true)
    		{
	    		System.out.print("How many chromosomes per population? ");
	    		numChromosome = in.nextInt();
	    		
	    		System.out.print("How many chromosomes undergo selection? ");
	    		numSelection = in.nextInt();
	    		
	    		System.out.print("How many chromosomes undergo mutation? ");
	    		numMutation = in.nextInt();
	    		
	    		System.out.print("How many new chromosomes per generation? ");
	    		numNewblood = in.nextInt();
	    		
	    		System.out.print("How many crossover pairs per generation? ");
	    		numCrossover = in.nextInt();
	    		
	    		//Check for valid population values
	    		if (numChromosome == numSelection + numMutation +
	    				numNewblood + 2 * numCrossover)
	    			break;
	    		else
	    			System.out.println("\nInvalid population. Try again...\n\n");
    		}
    		
    		System.out.print("How many generations? ");
    		numGeneration = in.nextInt();
    		
    		chromosomes = setupPopulation(numChromosome);
    		chromosomes = sort(target, chromosomes);

            System.out.print("\nAfter generation 0 the best fit chromosome is " + chromosomes.get(0).getBitString());
            System.out.print("\nWith a fitness of " + chromosomes.get(0).getFittness(target));
            System.out.print("\nAnd color r=" + chromosomes.get(0).getIntegerR() +
                                  ", g=" + chromosomes.get(0).getIntegerG() +
                                  ", b=" + chromosomes.get(0).getIntegerB() + "\n");
    		
    		for (int i = 0; i < numGeneration; i++)
    		{
    			chromosomes = nextGen(chromosomes, numSelection, numMutation, numCrossover, numNewblood);
    			chromosomes = sort(target, chromosomes);
    			
    			System.out.print("\nAfter generation " + (i + 1) + " the best fit chromosome is "+chromosomes.get(0).getBitString());
                System.out.print("\nWith fitness of " + chromosomes.get(0).getFittness(target));
                System.out.print("\nAnd color r="+ chromosomes.get(0).getIntegerR() +
                                    ", g=" + chromosomes.get(0).getIntegerG() +
                                    ", b=" + chromosomes.get(0).getIntegerB() + "\n");
              
    		}
    		System.out.println("\n\n");
    	}
    }
	
    private static ArrayList<Chromosome> setupPopulation(int inNumChromosome) 
    {
    	ArrayList<Chromosome> chromosomes = new ArrayList<Chromosome>();
    	
		for (int i=0; i < inNumChromosome; i++)
		{
            chromosomes.add(new Chromosome());
        }

		return chromosomes;
    }
    
    private static ArrayList<Chromosome> sort(Chromosome inTarget, ArrayList<Chromosome> inChromosomes) 
    {
        if(inChromosomes.size() > 1)
        {
            Chromosome maxChromosome;
            ArrayList<Chromosome> tempChromos = new ArrayList<Chromosome>(inChromosomes);
            inChromosomes.clear();
            for (int i=0; i < tempChromos.size(); i++)
            {
                maxChromosome= new Chromosome(tempChromos.get(0).getBitString());
                for (Chromosome chromo : tempChromos)
                {
                    if(maxChromosome.getFittness(inTarget) >= chromo.getFittness(inTarget))
                    {
                        maxChromosome = new Chromosome(chromo.getBitString());
                    }
                }
                inChromosomes.add(maxChromosome);
                tempChromos.remove(maxChromosome);
            }
        }  
        return inChromosomes;
    }
    
    private static ArrayList<Chromosome> nextGen(ArrayList<Chromosome> inChromosomes, int inNumSelection,
    					 int inNumMutations, int inNumCrossover, int inNumNewblood)
    {
        int numTickets, ticketNumber, ticket;
        ArrayList<Chromosome> nextGeneration = new ArrayList<Chromosome>();
        Chromosome chromo;
        Random rng = new Random();
        
        numTickets = (inChromosomes.size() * (inChromosomes.size() + 1)) / 2;
        ticket=inChromosomes.size() - 1;
        inChromosomes.get(0).setMinTicket(0);
        inChromosomes.get(0).setMaxTicket(ticket);
      
        for(int i=1; i<inChromosomes.size(); i++)
        {
            ticket--;
            inChromosomes.get(i).setMinTicket(inChromosomes.get(i - 1).getMaxTicket() + 1);
            inChromosomes.get(i).setMaxTicket(inChromosomes.get(i).getMinTicket() + ticket);
        }

        for(int k=0; k < inChromosomes.size(); k++)
        {

                if(inChromosomes.size() == 1) ticketNumber = 0;
                else ticketNumber=rng.nextInt(numTickets - 1);
                
                if (k <= inNumSelection && inNumSelection > 0)
                {
                    while(true)
                    {
                        chromo=inChromosomes.get(rng.nextInt(inChromosomes.size()));
                        if (ticketNumber >= chromo.getMinTicket() && ticketNumber <= chromo.getMaxTicket())
                        {
                            nextGeneration.add(new Chromosome(chromo.getBitString()));
                            break;
                        }
                    }
                } 
                else if(inNumMutations > 0 && k <= inNumSelection + inNumMutations)
                {
                    while (true)
                    {
                        chromo = inChromosomes.get(rng.nextInt(inChromosomes.size()));
                        
                        if (ticketNumber >= chromo.getMinTicket() && ticketNumber <= chromo.getMaxTicket())
                        {
                            nextGeneration.add(new Chromosome(performMutations(chromo).getBitString()));
                            break;
                        }
                    }
                } 
                else if (k <= inNumSelection + inNumMutations + inNumCrossover && 
                		inNumCrossover > 0)
                {
                    String chromosomeA = "";
                    String chromosomeB = "";
                    int randomSplit = rng.nextInt(24);
                    ArrayList<Chromosome> chromosomePair = new ArrayList<Chromosome>();
                    while(chromosomePair.size() < 2)
                    {
                        while(true)
                        {
                            chromo = inChromosomes.get(rng.nextInt(inChromosomes.size()));
                            if (ticketNumber >= chromo.getMinTicket() && ticketNumber <= chromo.getMaxTicket())
                            {
                                chromosomePair.add(chromo);
                                break;
                            }
                        }
                        
                        ticketNumber=rng.nextInt(numTickets - 1);
                    }
                    
                    chromosomeA=chromosomeA.concat(chromosomePair.get(0).getBitString().substring(0, randomSplit));
                    chromosomeA=chromosomeA.concat(chromosomePair.get(1).getBitString().substring(randomSplit));
                    chromosomeB=chromosomeB.concat(chromosomePair.get(1).getBitString().substring(0, randomSplit));
                    chromosomeB=chromosomeB.concat(chromosomePair.get(0).getBitString().substring(randomSplit));
                    nextGeneration.add(new Chromosome(chromosomeA));
                    nextGeneration.add(new Chromosome(chromosomeB));
                    k++;       

                } 
                else if (k <= inNumSelection + inNumMutations + inNumNewblood + 
                		inNumCrossover && inNumNewblood > 0)
                {
                    nextGeneration.add(performNewblood());
                } 
            }
        return inChromosomes = new ArrayList<>(nextGeneration);
    }

    private static Chromosome performMutations(Chromosome chromo) 
    {
        int randomIndex;
        Random rng = new Random();
        
        randomIndex=rng.nextInt(chromo.getBitString().length());
        chromo.changeBit(randomIndex);

        return chromo;
    }

    private static Chromosome performNewblood() 
    {
        String newChromosome = "";
        Random rng = new Random();
        
        for (int j = 0; j < 24; j++){
            newChromosome=newChromosome.concat(String.valueOf(rng.nextInt(2)));
        }
        
        return new Chromosome(newChromosome);
    }
}