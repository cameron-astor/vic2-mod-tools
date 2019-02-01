package vic2_tools;

import java.util.*;
import java.io.*;

//TODO
//Allow users to select what file they're working with via console.
//Introduce basic functionality for WHOLE FILE. 
//Then try to narrow to PROVINCE --> Check for white space in front of }!!! 
//Then finally to POP GROUP
//Must be able to have functionality for TYPE, RELIGION, CULTURE, SIZE

//A class for reading and extracting information from a Victoria 2 pop file.
public class PopParser {

	private ArrayList<String> popTypes; //"aristocrats", "bureaucrats", etc.
	private String fileName; //The name of the file to be parsed
	private File file; //The file object to be parsed
	
	//Constructs a new PopParser object. Takes file name and an array list of pop types as parameters.
	public PopParser(String newFileName, ArrayList<String> pops) throws FileNotFoundException{
		fileName = newFileName; 
		file = new File(fileName); //The file to be parsed
		popTypes = pops;
	}
		
	//Constructs a new PopParser object. Takes file name as a parameter.
	//TODO
	//Create a default ArrayList of pop types with the base HPM pop types so 
	//this object doesn't end up trying to do things with an empty array list.
	public PopParser(String newFileName) throws FileNotFoundException{		
		this(newFileName, null);
	}
	
	//Returns the file the parser is attached to.
	public File getFile() throws FileNotFoundException {
		return this.file;
	}
	
	//Returns an integer total of all pops of this type in the file.
	//Throws FileNotFoundException.
	//sumAll(popType, religion, culture, etc...) 
	public int sumAll(String popType) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		int total = 0;
		String currentLine;
		while(input.hasNextLine()) { //Iterate to the end of the file
			currentLine = input.nextLine();
			if(currentLine.contains(popType)) { //Reached appropriate pop group
				while(!currentLine.contains("size =")) {
					currentLine = input.nextLine();
				}
				total += Integer.parseInt(currentLine.replaceAll("[\\D]", "")); //Replace all non digits with blanks, then interpret as int.
			}
		}
		input.close();
		return total;
	}
	
	//Returns the number of pops with the passed attributes in the relevant file. 
	//If one wishes not to specify an attribute, pass null.
	public int search(String popType, String culture, String religion) throws FileNotFoundException {
		int total = 0;
		ArrayList<PopGroup> groups = this.groupPops();
		for(PopGroup g : groups) {
			if(g.getType().equals(popType) && g.getCulture().equals(culture) && g.getReligion().equals(religion)) {
				total += g.getSize();
			} else if(popType == null && g.getCulture().equals(culture) && g.getReligion().equals(religion)) {
				total += g.getSize();
			} else if(g.getType().equals(popType) && culture == null && g.getReligion().equals(religion)) {
				total += g.getSize();
			} else if(g.getType().equals(popType) && g.getCulture().equals(culture) && religion == null) {
				total += g.getSize();
			} else if(popType == null && culture == null && g.getReligion().equals(religion)) {
				total += g.getSize();
			} else if(g.getType().equals(popType) && culture == null && religion == null) {
				total += g.getSize();
			} else if(popType == null && g.getCulture().equals(culture) && religion == null) {
				total += g.getSize();
			}
		}
		return total;
	}
	
	//Returns an ArrayList of PopGroup objects comprised of all pop groups in the file.
	public ArrayList<PopGroup> groupPops() throws FileNotFoundException {
		ArrayList<PopGroup> list = new ArrayList<PopGroup>();
		Scanner input = new Scanner(file);
		String currentLine;
		while(input.hasNextLine()) {
			currentLine = input.nextLine();
			if(popTypes.contains(currentLine.replaceAll("[^a-z]", ""))) {
				String popType = currentLine.replaceAll("[^a-z]", "");
				while(!currentLine.contains("culture =")) {
					currentLine = input.nextLine();
				}
				String culture = currentLine.replace("\t\tculture = ", "");
				while(!currentLine.contains("religion =")) {
					currentLine = input.nextLine();
				}				
				String religion = currentLine.replace("\t\treligion = ", "");
				while(!currentLine.contains("size =")) {
					currentLine = input.nextLine();
				}
				int size = Integer.parseInt(currentLine.replaceAll("[\\D]", ""));
				list.add(new PopGroup(popType, culture, religion, size));
			}
		}
		input.close();
		return list;
	}
	
	//Takes in a province ID number (an integer ranging from 1 to 4 digits), and 
	//outputs only this province's pop data to a new file formatted as XXXX_pops.txt.
	//Returns the name of the file as a string
	//Also takes in a path to the desired output directory 
	//Throws FileNotFoundException.
	public String extractProvince(int provinceID, String outputDir) throws FileNotFoundException {
		Scanner input =  new Scanner(file);
		PrintStream output = new PrintStream(new File(outputDir + provinceID + "_pops.txt")); //Creating the output file.
		boolean endOfProvince = false;//End of province flag
		String currentLine;
		while(!endOfProvince) { //Iterate until the end of the desired province
			currentLine = input.nextLine(); //Keep scanning until the desired province number is found.
			if(currentLine.equals(provinceID + " = {")) { //Found correct province				
				output.println(); //Formatting
				output.println(currentLine);
				while(input.hasNext()) { //Until the end of the province
					currentLine = input.nextLine();
					if(currentLine.contains("#")) {
						break; //Bad style but hey, it works ¯\_(ツ)_/¯
					}
					output.println(currentLine);
				}
				endOfProvince = true; 
			}
		}
		input.close();
		output.close();
		return provinceID + "_pops.txt";
	}
	
}
