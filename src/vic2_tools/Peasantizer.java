package vic2_tools;
//Test tool for pop manipulation. Turns a specified percentage of all farmer pops in every province into peasants.

import java.util.*;
import java.io.*;

public class Peasantizer {
	
	public static final String FILE = "Afghanistan.txt";
	public static final double PERCENTAGE = 0.3; //The percentage (expressed as a double from 0.0 - 1.0) of the farmer population to convert.
	public static final String POP_TYPE = "aristocrats"; 
	public static final String CULTURE = "pashtun";
	public static final String RELIGION = "sunni";

	public static void main(String[] args) throws FileNotFoundException {
		
		//TODO
		//Create a user input system for choosing a file.
		//Make it generic for all pop types using class constant
		
		File popFile = new File(FILE);
		//Format output file as [name]_peasantized.txt.
		PrintStream output = new PrintStream(new File(popFile.getName().replace(".txt", "") + "_peasantized.txt"));
		//Attach a Scanner to the relevant pop file.
		Scanner input = new Scanner(popFile);
		while(input.hasNextLine()) {
			output.println(input.nextLine()); //Keep track of two scanners, one doing work and one checking for breaks between sections.
			if(input.hasNextInt()) { //When the Scanner reaches a new province (i.e. an integer label such as '1203')
				output.println(input.nextLine());
			  //peasantizeProvince(input, output);
				evaluatePopGroup(input, output);
				break;
			}
		}
	}

	
	/*
	public static void peasantizeProvince(Scanner input, PrintStream output) { 
		
	}
	*/

	//Parses a single group of one pop type in a province.
	public static int evaluatePopGroup(Scanner input, PrintStream output) {
		String popHeading = input.nextLine(); //"aristocrats", "bureaucrats", etc.
		output.println(popHeading);                  
		output.println(input.nextLine());        
		output.println(input.nextLine());
		output.print("        " + input.next()); //note: these values are indented 8 spaces. 
		output.print(" " + input.next() + " ");
		double popNum = input.nextInt();
		double newPopNum = 0.0;
		//If this pop block is of the desired pop type
		if(popHeading.contains(POP_TYPE)) {
			newPopNum = popNum * (1.0 - PERCENTAGE);
			output.print((int)newPopNum);
		} else {
			output.print((int)popNum);
		}
		for(int i = 0; i < 3; i++) {
			output.println(input.nextLine());
		}
		return (int)(popNum - newPopNum); //Return the net number of peasants to be created from this pop group.
	}
}
