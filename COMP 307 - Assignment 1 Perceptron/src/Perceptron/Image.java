package Perceptron;

public class Image {
	private String category;//"Yes" or "other"
	private int rows;
	private int cols;
	private boolean[][] image;

	public Image(String category, int rows, int cols, boolean[][] image) {
		this.category = category;
		this.rows = rows;
		this.cols = cols;
		this.image = image;
	}
	
	public boolean get(int row, int col){
		return image[row][col];
	}

	public String getCategory() {
		return category;
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public boolean[][] getImage() {
		return image;
	}

	public String toString() {
		String s = "Category: " + category + " Rows: " + rows + " Cols: "
				+ cols + "\n";
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				boolean tempBool = image[r][c];
				if (tempBool == true) {
					s += 1;
				} else {
					s += 0;
				}
			}
		}
		return s;
	}
}
