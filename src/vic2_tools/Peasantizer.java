package vic2_tools;
//Test tool for pop manipulation. Turns a specified percentage of all farmer pops in every province into peasants.

import java.util.*;
import java.io.*;

public class Peasantizer {
	
	public static final double PERCENTAGE = 0.3; //The percentage (expressed as a double from 0.0 - 1.0) of the farmer population to convert.

	public static void main(String[] args) throws FileNotFoundException {
		File popFile = new File("Afghanistan.txt");
		PrintStream output = new PrintStream(new File(popFile.getName() + "_peasantized"));
		Scanner input = new Scanner(popFile);
		while(input.hasNext()) {
			peasantizeProvince(input, output);
		}
	}
	
	public static void peasantizeProvince(Scanner input, PrintStream output) {
		
	}
}
