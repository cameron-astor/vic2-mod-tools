package vic2_tools;

import java.util.*;
import java.io.*;

//TODO
//Allow users to select what file they're working with.

//Introduce basic functionality for WHOLE FILE. 
//Then try to narrow to PROVINCE --> Check for white space in front of }!!! 
//Then finally to POP GROUP
//Must be able to have functionality for TYPE, RELIGION, CULTURE, SIZE

//n.b. use Scanner delimiters!


//A class for reading and extracting information from a Victoria 2 pop file.
public class PopParser {

	private ArrayList<String> pop_types; //"aristocrats", "bureaucrats", etc.
	private String file_name; //The name of the file to be parsed
	private File file; //The file object to be parsed
	
	//Constructs a new PopParser object. Takes ________ as parameters
	public PopParser() throws FileNotFoundException{		
		file_name = "all_pops.txt"; 
		file = new File(file_name); //The file to be parsed
		pop_types = new ArrayList<String>();
	}
	
	//TODO
	//SUM IS CURRENTLY BROKEN FOR USE WITH THE ALL_POPS FILE
	
	//Returns an integer total of all pops of this type in the file.
	//Throws FileNotFoundException.
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
	
	//Takes in a province ID number (an integer ranging from 1 to 4 digits), and 
	//outputs only this province's pop data to a new file formatted as XXXX_pops.txt.
	//Throws FileNotFoundException.
	public void extractProvince(int provinceID) throws FileNotFoundException {
		Scanner parser =  new Scanner(file);
		PrintStream output = new PrintStream(new File(provinceID + "_pops.txt"));
		boolean endOfProvince = false;
		String currentLine;
		while(!endOfProvince) { //Iterate until the end of the desired province
			currentLine = parser.nextLine(); //Keep scanning until the desired province number is found.
			if(currentLine.equals(provinceID + " = {")) { //Found correct province				
				output.println(); //Formatting
				output.println(currentLine);
				while(parser.hasNext()) { //Until the end of the province
					currentLine = parser.nextLine();
					if(currentLine.contains("#")) {
						break; //Bad style but hey, it works ¯\_(ツ)_/¯
					}
					output.println(currentLine);
				}
				endOfProvince = true; 
			}
		}
	}
	
}
