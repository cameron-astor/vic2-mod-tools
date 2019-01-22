package vic2_tools;
//Test tool for pop manipulation. Turns a specified percentage of all farmer pops in every province into peasants.

import java.util.*;
import java.io.*;

public class Peasantizer {
	
	public static final double PERCENTAGE = 0.3; //The percentage (expressed as a double from 0.0 - 1.0) of the farmer population to convert.
	public static final String POP_TYPE = "farmers"; 

	public static void main(String[] args) throws FileNotFoundException {
		
		//TODO
		//Create a user input system for choosing a file.
		//Make it generic for all pop types using class constant
		
		File popFile = new File("Afghanistan.txt");
		//Format output file as [name]_peasantized.txt.
		PrintStream output = new PrintStream(new File(popFile.getName().replace(".txt", "") + "_peasantized.txt"));
		//Attach a Scanner to the relevant pop file.
		Scanner input = new Scanner(popFile);
		while(input.hasNextLine()) {
			output.println(input.nextLine()); 
			if(input.hasNextInt()) { //When the Scanner reaches a new province (i.e. an integer label such as '1203')
				output.println(input.nextLine());
				peasantizeProvince(input, output);
			}
		}
	}
	
	//Tallies farmer pops for a province, applies PERCENTAGE modifier, 
	public static void peasantizeProvince(Scanner input, PrintStream output) {
		//int tally = 0; //Total number of relevant pops in the current province 
		while(!input.nextLine().contains("#")) {
			output.println(input.nextLine());
			//tally += evaluatePopGroup(input, output);
		}
		output.println(input.nextLine());
	}

	public static int evaluatePopGroup(Scanner input, PrintStream output) {
		while(!input.next().equals("}")) {
			if(input.next().equals(POP_TYPE)) {
				//test 
				System.out.println(input.next());
				
			}
		}		
		return 0;
	}
	
}
