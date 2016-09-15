package Perceptron;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;

import ImageEditor.FileDialog;

public class FileReader {

	private static JFileChooser chooser = new JFileChooser(".");
	
	public FileReader() {
	}

	public ArrayList<Image> load() {
		ArrayList<Image> images = new ArrayList<Image>();
		boolean[][] newimage = null;
		int rows = 10;
		int cols = 10;
		String category = "other";
		String categoryName;
		try {
			java.util.regex.Pattern bit = java.util.regex.Pattern
					.compile("[01]");
			File file = new File(open("Select Image File"));
			System.out.println("Importing data from '"+file.getName()+"' at location '"+file.getPath()+"'");
			Scanner f = new Scanner(file);
			while (f.hasNext()) {
				if (!f.next().equals("P1")){
					System.out.println("Not a P1 PBM file, CLOSING READER");
					f.close();
					throw new IllegalArgumentException();
				}
				category = f.next().substring(1);
				if (!category.equals("other"))
					categoryName = category;
				rows = f.nextInt();
				cols = f.nextInt();

				newimage = new boolean[rows][cols];
				for (int r = 0; r < rows; r++) {
					for (int c = 0; c < cols; c++) {
						newimage[r][c] = (f.findWithinHorizon(bit, 0)
								.equals("1"));
					}
				}
				Image tempImage = new Image(category, rows, cols, newimage);
//				System.out.println(tempImage.toString());
				images.add(tempImage);
			}
			f.close();
		} catch (IOException e) {
			System.out.println("Importing from file failed");
		}
		System.out.println("Importing successful: "+images.size()+" images");
		return images;
	}
	
    public static String open(String title){
	chooser.setDialogTitle(title);
	String ans = open();
	chooser.setDialogTitle("");
	return ans;
    }
    
    public static String open(){
	int returnVal =  chooser.showOpenDialog(null);
	if (returnVal == JFileChooser.APPROVE_OPTION) 
	    return (chooser.getSelectedFile().getPath());
	else
	    return null;
    }
}
