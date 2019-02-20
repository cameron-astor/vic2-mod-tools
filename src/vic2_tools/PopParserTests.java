package vic2_tools;

import java.util.*;
import java.io.*;

//TODO
//Make these tests more cohesive and robust

//A set of tests for the PopParser and PopGroup classes
public class PopParserTests {
	
	public static final String FILENAME = "./all_pops.txt"; //The name of the file to be tested. (Most likely all_pops.txt)

	public static void main(String[] args) throws FileNotFoundException {
	    	    
		/**COMPLETE TESTS**/
		System.out.println("--Constructor tests--");
		/**Constructors test**/
		PopParser parser = new PopParser(FILENAME); //Filename only 
		
		//With ArrayList manually added
	    String[] defaultTypes = {"aristocrats", "farmers", "bureaucrats", "clergymen", "laborers", "slaves", "soldiers", "clerks",
	              "craftsmen", "officers", "artisans", "serfs", "capitalists"};
		ArrayList<String> defaultTypesList = new ArrayList<String>();
		for (int i = 0; i < defaultTypes.length; i++) {
			defaultTypesList.add(defaultTypes[i]);
		}	
		PopParser parserWithList = new PopParser(FILENAME, defaultTypesList); //With manually added ArrayList
		
		//Check for equality in the list 
		boolean passed = true;
		for (int i = 0; i < parser.getPopTypes().size(); i++) {
			if (!parser.getPopTypes().get(i).equals(parserWithList.getPopTypes().get(i))) {
				System.out.println("failed");
				passed = false;
			} 
		}
		if (passed) {
			System.out.println("passed\n");
		}
		
		/**Search test**/
		System.out.println("--Search tests--");
		
		int provinceID = 428; 
		String type = "artisans"; //test parameters
		String culture = "french";
		String religion = "jewish";
				
		System.out.println("Testing with the following parameters:");
		System.out.println(provinceID + " " + type + " " + culture + " " + religion);
		System.out.println();
		//null input
		System.out.println("null input fileSearch: " + parser.fileSearch(null, null, null));
		System.out.println("null input provinceSearch: " + parser.provinceSearch(provinceID, null, null, null)); 
		System.out.println();
		
		//One input
		System.out.println("one input fileSearch: " + parser.fileSearch(type, null, null));
		System.out.println("one input provinceSearch: " + parser.provinceSearch(provinceID, type, null, null));
		//bad input
		System.out.println("one input bad input: " + parser.fileSearch("fdsfse", null, null));
		System.out.println();
		
		//Two inputs
		System.out.println("two inputs fileSearch: " + parser.fileSearch(type, null, religion));
		System.out.println("two inputs provinceSearch: " + parser.provinceSearch(provinceID, type, null, religion));
		//bad input
		System.out.println("two input bad input: " + parser.fileSearch("fdsfse", null, "sdfecdsoo"));
		System.out.println();
		
		//Three inputs
		System.out.println("three inputs fileSearch: " + parser.fileSearch(type, culture, religion));
		System.out.println("three inputs provinceSearch: " + parser.provinceSearch(provinceID, type, culture, religion));
		System.out.println();
		
		/**PopGroup tests**/
		
//		//PRINT POPGROUP TEST
//		File test = new File("./test.txt");
//		PrintStream output = new PrintStream(test);
//		PopGroup group = new PopGroup("aristocrats", "french", "daoist", 4500);
//		PopGroup group2 = new PopGroup("communizers", "malibu", "hamish patterson thought", 27000);
//		group.printPopGroup(output);
//		group2.printPopGroup(output); 
		
//		//Test popGroup equalsAttributes and toString 
//		System.out.println(GROUP.toString());
//		System.out.println(GROUP.equalsAttributes(GROUP)); //self check
//		System.out.println(GROUP.equalsAttributes(group2)); //should be false
//		System.out.println(GROUP.equalsAttributes(sameGroup)); //should be true
		
//		//POPGROUP TEST
//		ArrayList<PopGroup> group = parser.groupPops();
//		System.out.println(group.get(500).getType());
//		System.out.println(group.get(500).getCulture());
//		System.out.println(group.get(500).getReligion());
//		System.out.println(group.get(500).getSize());
//		System.out.println("Total popgroups: " + group.size()); 
//		
//		//GROUP BY PROVINCE TEST
//		ArrayList<PopGroup> group2 = parser.groupByProvince(1);
//		System.out.println(group2.get(0).getType());
//		System.out.println(group2.get(0).getCulture());
//		System.out.println(group2.get(0).getReligion());
//		System.out.println(group2.get(0).getSize());
//		System.out.println("Total popgroups: " + group2.size()); 
//		
//		ArrayList<Integer> IDs = parser.getProvinceIDs();
//		System.out.println(IDs.get(67));
	
//		//POPPARSER TEST
//		PopParser test = new PopParser(parser.extractProvince(2));
//		int total2 = 0;                                 
//		for(int i = 0; i < pop_types.length; i++) {
//			total2 = test.sumAll(pop_types[i]);
//			System.out.println("Total " + pop_types[i] + ": " + total2);
//		}		
		
	}
}
