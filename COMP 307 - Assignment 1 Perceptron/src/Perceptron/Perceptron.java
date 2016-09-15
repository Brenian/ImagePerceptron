package Perceptron;

import java.util.ArrayList;
import java.util.Random;

public class Perceptron {
	private static int NUM_FEATURES = 50;
	private static int NUM_WEIGHTS = 50;
	private static int IMAGE_WIDTH = 10;
	private static int IMAGE_HEIGHT = 10;
	private static int MAX_ITERATIONS = 1000;
	private static double LEARNING_RATE = 0.025;

	private FileReader fReader;
	private Helper helper;
	private ArrayList<Feature> features;
	private ArrayList<Image> images;
	private ArrayList<Double> weights;
	private double threshold = 4;
	
	private static int rand = 5;//Sets the seed for the RNG in Feature

	public Perceptron() {
		fReader = new FileReader();
		helper = new Helper(rand);
		images = fReader.load();
		features = helper.initFeatures(NUM_FEATURES, IMAGE_WIDTH, IMAGE_HEIGHT);
		weights = helper.initWeights(NUM_WEIGHTS);
//		String result = runPerceptron();
		multiTest();
//		helper.printAllFeatures(features);
//		helper.printAllWeights(weights);
//		System.out.println(result);
	}

	private void multiTest() {
		for(int i = 0; i < 100; i++){
			rand = i;
			System.out.println("Random value: "+rand);
			features = helper.initFeatures(NUM_FEATURES, IMAGE_WIDTH, IMAGE_HEIGHT);
			weights = helper.initWeights(NUM_WEIGHTS);
			System.out.println(runPerceptron());
		}
	}

	private String runPerceptron() {
		System.out.println("Processing Images...");
		int accuracy = 0;
		int i = 0;
		// Until the perceptron is always right (or some limit):
		while (i < MAX_ITERATIONS && accuracy < 100) {
			// Present an example (+ve or -ve)
			int numCorrect = 0;
			for (Image image : images) {
				boolean correct = false;
				double sum = 0;
				int[] hits = new int[NUM_FEATURES];
				for (int j = 0; j < features.size()-1; j++) {
					int value = features.get(j).value(image);//returns 1 for a match, 0 for a miss
					sum += value * weights.get(j).doubleValue();//Sum(Wi.Xi)
					hits[j] += value;//active features
				}
				sum+=weights.get(weights.size()-1);//+w0
				if ((sum >= threshold && image.getCategory().equals("Yes")) || (sum < threshold && image.getCategory().equals("other"))) {
					correct = true;
				}
				// If perceptron is correct, do nothing
				if (correct) {
					numCorrect++;
				}
				// If -ve example and wrong
				// (ie, weights on active features are too high)
				else if (image.getCategory().equals("other") && !correct) {
					// Subtract feature vector from weight vector
					for(int k = 0; k < hits.length; k++){
						if(hits[k] == 1){
							weights.set(k, weights.get(k)-1*LEARNING_RATE);
						}
					}
				}
				// If +ve example and wrong
				// (ie, weights on active features are too low)
				else if (image.getCategory().equals("Yes") && !correct) {
					// Add feature vector to weight vector
					for(int k = 0; k < hits.length; k++){
						if(hits[k] == 1){
							weights.set(k, weights.get(k)+1*LEARNING_RATE);
						}
					}
				}
			}
			accuracy = numCorrect;
			i++;
		}
		return ("\nProcessing Complete!\nAccuracy: "+accuracy+"/100, Epochs: "+i);
	}

	public static void main(String[] args) {
		new Perceptron();
	}
}
