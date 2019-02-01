package vic2_tools;

import java.util.*;
import java.io.*;

//A set of tests for the PopParser class
public class PopParserTests {

	public static void main(String[] args) throws FileNotFoundException {
	    	    
		PopParser parser = new PopParser("./all_pops.txt");
		
		String type = "capitalists";
		String culture = "dixie";
		String religion = "protestant";
		System.out.println(culture + " " + religion + " " + type + ": " + parser.search(type, culture, religion));
		
	/*	
		int total = 0;  
		int grandTotal = 0;
		for(int i = 0; i < popTypes.length; i++) {
			total = parser.sumAll(popTypes[i]);
			grandTotal += total;
			System.out.println("Total " + popTypes[i] + ": " + total);
		} 
		System.out.println("Grand total: " + grandTotal); */
		
	/*
		ArrayList<PopGroup> group = parser.groupPops();
		System.out.println(group.get(0).getType());
		System.out.println(group.get(0).getCulture());
		System.out.println(group.get(0).getReligion());
		System.out.println(group.get(0).getSize());
		System.out.println("Total popgroups: " + group.size()); */
		
		
		//System.out.println(parser.sumAll("theravada"));
	
		/*
		PopParser test = new PopParser(parser.extractProvince(2));
		int total2 = 0;                                 
		for(int i = 0; i < pop_types.length; i++) {
			total2 = test.sumAll(pop_types[i]);
			System.out.println("Total " + pop_types[i] + ": " + total2);
		}	*/	
		
	}
	
}
