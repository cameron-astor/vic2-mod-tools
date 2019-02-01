package vic2_tools;

import java.util.*;
import java.io.*;

//Uses the PopParser to create individual text files for every province in the relevant pop file.
//Places these extracted provinces in a new directory located in ___________ <- ???
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
			//System.out.println(currentLine);
			if(currentLine.contains(" = {") && !currentLine.contains("[^a-z]")) {
				System.out.println(currentLine);
				String parseLine = currentLine.replaceAll("[\\D]", "");
				System.out.println(parseLine); //test
				
				provinceID = Integer.parseInt(parseLine);
				parser.extractProvince(provinceID, dir.getPath() + "/");
				System.out.println(provinceID); //test
			}
		}
		input.close();
	}
	
}
