package Perceptron;

import java.util.Arrays;

public class Feature {
	private int[] row;
	private int[] col;
	private boolean[] sgn;
	private int value;

	public Feature(int[] row, int[] col, boolean[] sgn) {
		this.row = row;
		this.col = col;
		this.sgn = sgn;
	}

	public Feature(int value) {
		this.value = value;
	}

	public int value(Image image) {
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			if (image.get(row[i], col[i]) == sgn[i]) {
				sum++;
			}
		}
		return (sum >= 3) ? 1 : 0;
	}

	public void setValue(Image image) {
		this.value = value(image);
	}

	public int[] getRow() {
		return row;
	}

	public int[] getCol() {
		return col;
	}

	public boolean[] getSgn() {
		return sgn;
	}

	public int getValue() {
		return value;
	}

	public String toString() {
		if (row == null) {
			return "Dummy Feature, Value: " + value;
		}
		return "{ " + Arrays.toString(row) + " , " + Arrays.toString(col)
				+ " , " + Arrays.toString(sgn) + " , Value: " + value + " }";
	}
}
