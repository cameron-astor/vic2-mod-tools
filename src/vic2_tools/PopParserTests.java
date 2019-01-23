package vic2_tools;

import java.util.*;
import java.io.*;

//A set of tests for the PopParser class
public class PopParserTests {

	public static void main(String[] args) throws FileNotFoundException {
	    String[] pop_types = {"aristocrats", "bureaucrats", "farmers", "clergymen", "laborers", "slaves", "soldiers", "peasants", "clerks",
				              "craftsmen", "officers", "serfs", "artisans"};
		PopParser parser = new PopParser();
		/*
		int total = 0;                                 //sumAll currently broken for large files
		for(int i = 0; i < pop_types.length; i++) {
			total = parser.sumAll(pop_types[i]);
			System.out.println("Total " + pop_types[i] + ": " + total);
		}
		*/
		parser.extractProvince(1);
		extractEveryProvince();
		
	}
	
	//Uses the PopParser to create individual text files for every province. 
	public static void extractEveryProvince() {
		
	}
}
