package vic2_tools;

import java.util.*;
import java.io.*;

//TODO
//Introduce basic functionality for WHOLE FILE. 
//Then try to narrow to PROVINCE --> Check for white space in front of }!!! 
//Then finally to POP GROUP
//Must be able to have functionality for TYPE, RELIGION, CULTURE, SIZE

//A class for reading and extracting information from a Victoria 2 pop file.
public class PopParser {

	private ArrayList<String> pop_types; //"aristocrats", "bureaucrats", etc.
	private String file_name; //The name of the file to be parsed
	private File file; //The file object to be parsed
	
	//Constructs a new PopParser object. Takes ________ as parameters
	public PopParser() throws FileNotFoundException{
		
		//USER INPUT SYSTEM
		//Scanner user_input = new Scanner(System.in);
		//System.out.println("Input pop file name: ");
		//file_name = user_input.next();
		
		file_name = "Afghanistan.txt"; 
		file = new File(file_name); //The file to be parsed
		pop_types = new ArrayList<String>();
		
		//user_input.close();
	}
	
	//Returns an integer total of all pops of this type in the file.
	public int sumAll(String popType) throws FileNotFoundException {
		Scanner parser = new Scanner(file);
		int total = 0;
		while(parser.hasNextLine()) { //Iterate to the end of the file
			String currentLine = parser.nextLine();
			if(currentLine.contains(popType)) {
				parser.nextLine(); //Skip culture line
				parser.nextLine(); //Skip religion line
				parser.next(); //size
				parser.next(); // =
				total += parser.nextInt(); //The number we wanted
			}
		}
		parser.close();
		return total;
	}
	
	//EXPERIMENTAL make sumAll() more flexible using a HashMap
	//Adds the sum of every pop type in a pop file to a map as a value, with their respective types as the keys.
	private Map<String, Integer> popTypeTotals() throws FileNotFoundException {
		Map<String, Integer> popMap = new HashMap<String, Integer>();
		Scanner parser = new Scanner(file);
		while(parser.hasNextLine()) {
			String currentLine = parser.nextLine();

		}
		return popMap;
	}
	
	
}
