package abc;

	import java.util.Arrays;
	import java.util.Random;

	public class proj {

	    private static final double MAX_TEMPERATURE = 10000;
	    private static final double COOLING_RATE = 0.03;
	    private static int[] values = {68, 64, 47, 55, 72, 53, 81, 60, 72, 80, 62, 42, 48, 47, 68, 51, 48, 68, 83, 55, 48, 44, 49, 68, 63, 71, 82, 55, 60, 63, 56, 75, 42, 76, 42, 60, 75, 68, 67, 42, 71, 58, 66, 72, 67, 78, 49, 50, 51};
	    private static int[] weights = {21, 11, 11, 10, 14, 12, 12, 14, 17, 13, 11, 13, 17, 14, 16, 10, 18, 10, 16, 17, 19, 12, 12, 16, 16, 13, 17, 12, 16, 13, 21, 11, 11, 10, 14, 12, 12, 14, 17, 13, 11, 13, 17, 14, 16, 10, 18, 10, 16};

	    private static int knapsackCapacity = 300;

	    private static boolean[] currentSolution;
	    private static boolean[] bestSolution;
	    private static int currentValue;
	    private static int bestValue;

	    public static void main(String[] args) {
	        // Define the temperature differences to test
	        double[] temperatureDifferences = {0.1, 0.2, 0.3, 0.4, 0.5};

	        for (double temperatureDifference : temperatureDifferences) {
	            System.out.println("Temperature Difference: " + temperatureDifference);

	            long startTime = System.nanoTime();

	            int totalSolutionValue = 0;
	            int numValidSolutions = 0;

	            for (int run = 1; run <= 5; run++) {
	                currentSolution = new boolean[values.length];
	                bestSolution = new boolean[values.length];
	                currentValue = 0;
	                bestValue = 0;

	                Random random = new Random();

	                double stoppingTemperature = MAX_TEMPERATURE * temperatureDifference;

	                boolean validSolutionFound = false;
	                while (!validSolutionFound) {
	                    for (int i = 0; i < weights.length; i++) {
	                        currentSolution[i] = random.nextBoolean();
	                    }
	                    bestSolution = currentSolution.clone();
	                    currentValue = calculateValue(currentSolution);
	                    bestValue = currentValue;
	                    double temperature = MAX_TEMPERATURE;

	                    while (temperature > stoppingTemperature) {
	                        boolean[] newSolution = currentSolution.clone();
	                        int index = random.nextInt(newSolution.length);
	                        newSolution[index] = !newSolution[index];
	                        int newSolutionValue = calculateValue(newSolution);
	                        int delta = newSolutionValue - currentValue;

	                        if (delta > 0 || delta > calculateAcceptanceProbability(currentValue, newSolutionValue, temperature)) {
	                            currentSolution = newSolution;
	                            currentValue = newSolutionValue;
	                        }

	                        if (currentValue > bestValue) {
	                            bestSolution = currentSolution.clone();
	                            bestValue = currentValue;
	                        }

	                        temperature *= COOLING_RATE;
	                    }

	                    if (bestValue > 0) {
	                        validSolutionFound = true;
	                    }
	                }

	                totalSolutionValue += bestValue;
	                numValidSolutions++;

	                System.out.println("Run " + run + ": Best Solution: " + Arrays.toString(bestSolution));
	                System.out.println("Run " + run + ": Best Value: " + bestValue);
	            }

	            double averageSolutionValue = numValidSolutions > 0 ? (double) totalSolutionValue / numValidSolutions : 0;

	            System.out.println("Average Solution Value: " + averageSolutionValue);
	            System.out.println();

	            long endTime = System.nanoTime();
	            long totalRunTime = endTime - startTime;
	            System.out.println("Total Run Time: " + totalRunTime + "ns");
	            System.out.println();
	        }
	    }

	    private static int calculateValue(boolean[] solution) {
	        int value = 0;
	        int weight = 0;
	        for (int i = 0; i < solution.length; i++) {
	            if (solution[i]) {
	                value += values[i];
	                weight += weights[i];
	            }
	        }
	        if (weight > knapsackCapacity) {
	            return 0;
	        } else {
	            return value;
	            
	        }
	    }

	    private static double calculateAcceptanceProbability(int currentValue, int neighborValue, double temperature) {
	        if (neighborValue > currentValue) {
	            return 1;
	        } else {
	            return Math.exp((neighborValue - currentValue) / temperature);
	        }
	    }
	}

