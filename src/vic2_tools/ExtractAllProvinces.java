package vic2_tools;

import java.util.*;
import java.io.*;

//Uses the PopParser to create individual text files for every province.
//Places these extracted provinces in a new directory located in the classpath directory.
public class ExtractAllProvinces {
	
	public static void main (String[] args) throws FileNotFoundException {
		//Set up PopParser and output directory.
		String fileName = "C://Users/camer/Documents/Paradox Interactive/vic2-mod-tools/1836.1.1_HPM/Afghanistan.txt";
		PopParser parser = new PopParser(fileName);
		File dir = new File(fileName.replace(".txt", "") + "_provinces");
		dir.mkdir();
		
		//Extract provinces
		Scanner input = new Scanner(parser.getFile());
		String currentLine;
		int provinceID;
		while(input.hasNextLine()) {
			currentLine = input.nextLine();
			System.out.println(currentLine);
			if(currentLine.contains(" = { ") && !currentLine.contains("[^a-z]")) {
				provinceID = Integer.parseInt(currentLine.replaceAll("[\\D]", ""));
				parser.extractProvince(provinceID, dir.getPath() + "/");
				System.out.println(provinceID);
			}
		}
		input.close();
	}
	
}
