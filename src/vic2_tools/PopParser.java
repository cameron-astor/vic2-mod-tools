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
	private String fileName; //The name of the file to be parsed
	private File file; //The file object to be parsed
	
	//Constructs a new PopParser object. Takes file name and an array list of pop types as parameters.
	public PopParser(String newFileName, ArrayList<String> pops) throws FileNotFoundException{
		fileName = newFileName; 
		file = new File(fileName); //The file to be parsed
		pop_types = new ArrayList<String>();
	}
		
	//Constructs a new PopParser object. Takes file name as a parameter.
	public PopParser(String newFileName) throws FileNotFoundException{		
		this(newFileName, null);
	}
	
	//TODO
	//SUM IS CURRENTLY BROKEN FOR USE WITH THE ALL_POPS FILE
	
	//Returns an integer total of all pops of this type in the file.
	//Throws FileNotFoundException.
	//sumAll(popType, religion, culture, etc...) <- goal?
	public int sumAll(String popType) throws FileNotFoundException {
		Scanner parser = new Scanner(file);
		int total = 0;
		String currentLine;
		while(parser.hasNextLine()) { //Iterate to the end of the file
			currentLine = parser.nextLine();
			if(currentLine.contains(popType)) { //Reached appropriate pop group
				while(!currentLine.contains("size =")) {
					currentLine = parser.nextLine();
				}
				total += Integer.parseInt(currentLine.substring(9)); //Grab the size integer out of the size = xxxx string
			}
		}
		parser.close();
		return total;
	}
	
	//Takes in a province ID number (an integer ranging from 1 to 4 digits), and 
	//outputs only this province's pop data to a new file formatted as XXXX_pops.txt.
	//Returns the name of the file as a string
	//Throws FileNotFoundException.
	public String extractProvince(int provinceID) throws FileNotFoundException {
		Scanner parser =  new Scanner(file);
		PrintStream output = new PrintStream(new File(provinceID + "_pops.txt")); //Creating the output file.
		boolean endOfProvince = false;//End of province flag
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
		parser.close();
		output.close();
		return provinceID + "_pops.txt";
	}
	
}
