package Perceptron;

import java.util.ArrayList;
import java.util.Random;

public class Helper {

	private Random random;

	public Helper(int rand) {
		random = new Random(rand);
	}

	public ArrayList<Feature> initFeatures(int num, int width, int height) {
		ArrayList<Feature> tempList = new ArrayList<Feature>();
		// Populate Features list up to NUM_FEATURES constant
		for (int i = 0; i < num; i++) {
			int[] row = { random.nextInt(width), random.nextInt(width),
					random.nextInt(width), random.nextInt(width) };
			int[] col = { random.nextInt(height), random.nextInt(height),
					random.nextInt(height), random.nextInt(height) };
			boolean[] sgn = { random.nextBoolean(), random.nextBoolean(),
					random.nextBoolean(), random.nextBoolean() };
			tempList.add(new Feature(row, col, sgn));
		}
		// Add dummy Feature
		tempList.add(new Feature(1));
		return tempList;
	}

	public ArrayList<Double> initWeights(int num) {
		ArrayList<Double> tempList = new ArrayList<Double>();
		// Populate Features list up to NUM_WEIGHTS constant
		for (int i = 0; i <= num; i++) {
			tempList.add(random.nextDouble());
		}
		return tempList;
	}

	public void printAllFeatures(ArrayList<Feature> features) {
		System.out.println("-----PRINTING FEATURES-----");
		for (Feature feature : features) {
			System.out.println(feature.toString());
		}
	}

	public void printAllWeights(ArrayList<Double> weights) {
		System.out.println("-----PRINTING WEIGHTS-----");
		for (Double weight : weights) {
			System.out.print("[" + weight.toString() + "] ");
		}
	}
}
