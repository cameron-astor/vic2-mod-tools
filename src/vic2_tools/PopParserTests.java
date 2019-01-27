package vic2_tools;

import java.util.*;
import java.io.*;

//A set of tests for the PopParser class
public class PopParserTests {

	public static void main(String[] args) throws FileNotFoundException {
	    String[] popTypes = {"aristocrats", "farmers", "bureaucrats", "clergymen", "laborers", "slaves", "soldiers", "peasants", "clerks",
				              "craftsmen", "officers", "artisans", "serfs"};
	    
	    ArrayList<String> pops = new ArrayList<String> ();
	    for(String s : popTypes) {
	    	pops.add(s);
	    }
	    
		PopParser parser = new PopParser("C://Users/camer/Documents/Paradox Interactive/vic2-mod-tools/1836.1.1_HPM/China.txt", pops);
		
	/*	
		int total = 0;  
		int grandTotal = 0;
		for(int i = 0; i < popTypes.length; i++) {
			total = parser.sumAll(popTypes[i]);
			grandTotal += total;
			System.out.println("Total " + popTypes[i] + ": " + total);
		} 
		System.out.println("Grand total: " + grandTotal); */
	
		ArrayList<PopGroup> group = parser.groupPops();
		System.out.println(group.get(14).getType());
		System.out.println(group.get(14).getCulture());
		System.out.println(group.get(14).getReligion());
		System.out.println(group.get(14).getSize());
		System.out.println("Total popgroups: " + group.size());
		
		
		//System.out.println(parser.sumAll("theravada"));
	
		/*
		PopParser test = new PopParser(parser.extractProvince(2));
		int total2 = 0;                                 
		for(int i = 0; i < pop_types.length; i++) {
			total2 = test.sumAll(pop_types[i]);
			System.out.println("Total " + pop_types[i] + ": " + total2);
		}	*/	
		
		//extractEveryProvince();
		
	}
	
	//Uses the PopParser to create individual text files for every province. 
	//MUST FIGURE OUT HOW TO SET FILE PATHS AND CREATE DIRECTORIES FOR THIS METHOD
	public static void extractEveryProvince() {
		
	}
}
