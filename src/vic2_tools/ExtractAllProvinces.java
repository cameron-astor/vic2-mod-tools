package vic2_tools;

import java.util.*;
import java.io.*;

//Uses the PopParser to create individual text files for every province in the relevant pop file.
//Places these extracted provinces in a new directory located in ___________ <- ???

//TODO
//File pathing

public class ExtractAllProvinces {
	
	public static void main (String[] args) throws FileNotFoundException {
		//Set up PopParser and output directory.
		String fileName = "C://Users/camer/Documents/Paradox Interactive/vic2-mod-tools/all_pops.txt";
		PopParser parser = new PopParser(fileName);
		File dir = new File(fileName.replace(".txt", "") + "_provinces");
		dir.mkdir();
		
		//Extract provinces
		Scanner input = new Scanner(parser.getFile());
		String currentLine;
		int provinceID;
		while(input.hasNextLine()) {
			currentLine = input.nextLine();
			//If it is a province number line (i.e. it starts with a digit)
			if(currentLine.contains(" = {") && currentLine.matches("\\d.*")) {
				currentLine = currentLine.replaceAll("[\\D]", "");
				provinceID = Integer.parseInt(currentLine);
				//Extract province to folder 
				parser.extractProvince(provinceID, dir.getPath() + "/");
			}
		}
		input.close();
	}
	
}
