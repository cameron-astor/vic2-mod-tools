package vic2_tools;

import java.util.*;
import java.io.*;

//TODO
//Introduce basic functionality for WHOLE FILE. 
//Then try to narrow to PROVINCE --> Check for white space in front of }!!! 
//Then finally to POP GROUP

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
	public int addAll(String popType) throws FileNotFoundException {
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
	
	/*
	private int evaluatePopGroup(Scanner input, PrintStream output) {
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
	*/
}
