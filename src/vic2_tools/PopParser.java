package vic2_tools;

import java.util.*;
import java.io.*;

//TODO
//Create a logical and clean system of interaction with the file system.
//Must be: relative path based, easily handled by other classes which use PopParser objects
//
//think of a better system for defining pop types?
//
//Constructor should throw an exception if the file is not found.

//A class for reading and extracting information from a Victoria 2 pop file.
public class PopParser {

	private ArrayList<String> popTypes; //"aristocrats", "bureaucrats", etc.
	private String fileName; //The name of the file to be parsed
	private File file; //The file object to be parsed
	
	//Constructs a new PopParser object. Takes file name and an array list of pop types as parameters.
	public PopParser(String newFileName, ArrayList<String> pops) throws FileNotFoundException {
		this.fileName = newFileName; 
		this.file = new File(fileName); //The file to be parsed
		this.popTypes = pops;
	}
		
	//Constructs a new PopParser object. Takes file name as a parameter.
	//Without specifying an array of pop types, the default HPM types are used. 
	public PopParser(String newFileName) throws FileNotFoundException {		
		this(newFileName, null);
	    String[] defaultTypes = {"aristocrats", "farmers", "bureaucrats", "clergymen", "laborers", "slaves", "soldiers", "clerks",
	              "craftsmen", "officers", "artisans", "serfs", "capitalists"};
		ArrayList<String> defaultTypesList = new ArrayList<String>();
		for(int i = 0; i < defaultTypes.length; i++) {
			defaultTypesList.add(defaultTypes[i]);
		}	
		this.popTypes = defaultTypesList;
		}
	
	//Returns the file object that the parser is attached to.
	public File getFile() throws FileNotFoundException {
		return this.file;
	}
	
	//Returns the name of the file the parser is attached to as a string
	public String getFileName() throws FileNotFoundException {
		return this.fileName;
	}
	
	//Returns the parser's current ArrayList of pop types.
	public ArrayList<String> getPopTypes() throws FileNotFoundException {
		return this.popTypes;
	}
	
	//Returns an integer total of pops with the passed attributes in the PopParser's file. 
	//If one wishes not to specify an attribute, pass null.
	public int fileSearch(String popType, String culture, String religion) throws FileNotFoundException {
		ArrayList<PopGroup> groups = this.groupPops();
		return search(groups, popType, culture, religion);
	}
	
	//Returns an integer total of pops with the passed attributes in the passed province.
	//If one wishes not to specify an attribute, pass null.
	public int provinceSearch(int provinceID, String popType, String culture, String religion) throws FileNotFoundException {
		ArrayList<PopGroup> groups = this.groupByProvince(provinceID);
		return search(groups, popType, culture, religion);
	}
	
	//Performs a searching operation on an ArrayList of PopGroups.
	//Takes in the list, and the attributes of the pops to be searched for.
	//Returns an integer total.
	private int search(ArrayList<PopGroup> groups, String popType, String culture, String religion) {
		int total = 0;
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
				currentLine = group(input, currentLine, list);
			}
		}
		input.close();
		return list;
	}
	
	//Returns an ArrayList of PopGroups representing all groups in the province with the 
	//passed ID.
	public ArrayList<PopGroup> groupByProvince(int provinceID) throws FileNotFoundException {
		ArrayList<PopGroup> list = new ArrayList<PopGroup>();
		Scanner input = new Scanner(file);
		String currentLine;
		while(input.hasNextLine()) {
			currentLine = input.nextLine();
			if(currentLine.equals(provinceID + " = {")) {
				while(!currentLine.equals("}")) {
					currentLine = input.nextLine();             
					if(popTypes.contains(currentLine.replaceAll("[^a-z]", ""))) {
						currentLine = group(input, currentLine, list);
					}
				}
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
	
	//Returns an ArrayList containing the IDs of all provinces in the file.
	public ArrayList<Integer> getProvinceIDs() throws FileNotFoundException {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Scanner input = new Scanner(file);
		String currentLine;
		while(input.hasNextLine()) {
			currentLine = input.nextLine();
			//If the current line contains the province suffix and does not start with a tab or a space
			if(currentLine.contains(" = {") && !currentLine.startsWith("\t") && !currentLine.startsWith(" ")) { 
				list.add(Integer.parseInt(currentLine.replaceAll("[\\D]", "")));
			}
		}
		input.close();
		return list;
	}
	
	//Performs the grouping operation for the groupPops methods.
	//Takes in a Scanner for the file, the current line the grouping method's Scanner is on,
	//and the ArrayList to be added to. Returns currentLine so the next level while loop can keep going.
	private String group(Scanner input, String currentLine, ArrayList<PopGroup> list) {
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
		return currentLine;
	}	
}
