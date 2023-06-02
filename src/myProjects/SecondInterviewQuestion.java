package myProjects;

import java.util.Arrays;
import java.util.List;

public class SecondInterviewQuestion {
	public static void main(String[] args) {
		// Set Weights
		Integer[] weights = new Integer[] {3, 11};
		// Set Max Weight
		int maxWeight = 16;
		
		// Find closest weight combination, print result
		int closest = new SecondInterviewQuestion().closestWeight(Arrays.asList(weights), maxWeight);
		System.out.println("Closest Weight = " + closest);
	}
	
	// Method to find the closest weight, starts the first step of recursion
	public int closestWeight(List<Integer> weights, int maxWeight) {
		return step(weights, maxWeight, 0);
	}
	
	// Step of recursion
	public int step(List<Integer> weights, int maxWeight, int currSum) {
		// In this branch of recursion, the closest to maxWeight at the beginning is set by default to currSum
		int closest = currSum;
		// Loop through the list of weights, add each weight to the current sum as a test
		for(int idx = 0; idx < weights.size(); idx++) {
			int testSum = currSum + weights.get(idx);
			// If the test yields a sum that is greater than maxWeight, disregard it
			if(testSum > maxWeight) {
				continue;
			}
			// If the test yields a sum that is equal to maxWeight, we know the maxWeight is to itself
			// Ignore everything else, return immediately
			if(testSum == maxWeight) {
				return maxWeight;
			}
			// If the test yields a sum that is less than the max, pass it into the next step of recursion,
			// which will eventually return the closest sum of weights in the branch created from the test sum
			if(testSum < maxWeight) {
				int recurSum = step(weights, maxWeight, testSum);
				// If the recurSum is equal to maxWeight, return maxWeight immediately
				if(recurSum == maxWeight) {
					return maxWeight;
				}
				// Otherwise, check if recurSum is closer to maxWeight than the current value of closest, and set value as needed
				// Don't have to worry about recurSum being greater than maxWeight, due to the guard clause in step (line 31)
				if(recurSum > closest) {
					closest = recurSum;
				}
			}
		}
		// Return the closest value to maxWeight in this branch of the recursive steps
		return closest;
	}
	
}
