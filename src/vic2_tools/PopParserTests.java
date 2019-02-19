package vic2_tools;

import java.util.*;
import java.io.*;

//TODO
//Make these tests more cohesive and robust

//A set of tests for the PopParser class
public class PopParserTests {

	public static void main(String[] args) throws FileNotFoundException {
	    	    
		PopParser parser = new PopParser("./all_pops.txt");
		
//		//PRINT POPGROUP TEST
//		File test = new File("./test.txt");
//		PrintStream output = new PrintStream(test);
//		PopGroup group = new PopGroup("aristocrats", "french", "daoist", 4500);
//		PopGroup group2 = new PopGroup("communizers", "malibu", "hamish patterson thought", 27000);
//		group.printPopGroup(output);
//		group2.printPopGroup(output); 
		
		
//		 //SEARCH TEST
//		String type = "capitalists";
//		String culture = "dixie";
//		String religion = "protestant";
//		System.out.println(culture + " " + religion + " " + type + ": " + parser.search(type, culture, religion)); 
		
//		//SUM TEST
//		int total = 0;  
//		int grandTotal = 0;
//		for(int i = 0; i < popTypes.length; i++) {
//			total = parser.sumAll(popTypes[i]);
//			grandTotal += total;
//			System.out.println("Total " + popTypes[i] + ": " + total);
//		} 
//		System.out.println("Grand total: " + grandTotal); 
		
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
		
		//COMPLETE TEST
		
		
	}
}
