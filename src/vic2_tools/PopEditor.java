package vic2_tools;

import java.util.*;
import java.io.*;


//An class which provides functionality to add and subtract pop groups from a pop file.
//Operations are in general carried out on a province by province basis.
public class PopEditor {
	
	//TODO 
	//subtract method
	//subtractAll method 
	//
	//When all methods are working, then try and implement them so that they sort
	//based on pop type, religion, etc. when printing to the file (so it is easier to read).
	//
	
	private String fileName; //Name of the file to be edited
	private File file; //The file to be edited
	private int provinceID; //The ID of the province to be edited
	private PopParser parser; //A PopParser for the file 
	
	private File temp; //A pointer to a temporary file to be used in editing 
	private Scanner input; //A pointer to a Scanner to be used in editing
	private PrintStream output; //A pointer to a PrintStream to be used in editing
	
	//Constructs a PopEditor. Takes the name of the file to be edited as a parameter.
	public PopEditor(String fileName) throws FileNotFoundException {
		this.fileName = fileName;
		this.file = new File(fileName);
		this.provinceID = 0;
		this.parser = new PopParser(fileName);
	}
	
	//Constructs a PopEditor. Takes a file object referring to the 
	//file to be edited as a parameter.
	public PopEditor(File file) throws FileNotFoundException {
		this.file = file;
		this.fileName = file.getName();
		this.provinceID = 0;
		this.parser = new PopParser(fileName);
	}
	
	//Returns the name of the file the editor is attached to.
	public String getFileName() {
		return this.fileName;
	}
	
	//Returns the current province ID.
	public int getProvinceID() {
		return this.provinceID;
	}
	
	//Sets the province being edited.
	public void setProvinceID(int provinceID) {
		this.provinceID = provinceID;
	}
	
	//removes all PopGroups from the province 
	public void clear() throws FileNotFoundException{
		setup();	
		//Do the clearing
		String currentLine;
		while(input.hasNextLine()) {
			currentLine = input.nextLine();
			output.println(currentLine);
			if(currentLine.equals(provinceID + " = {")) {
				while(!currentLine.equals("}")) {
					currentLine = input.nextLine();
				}
				output.println(currentLine);
			}
		}
		overwrite();
	}
	
	//Adds a single pop group to the desired file (overwrites) in the province
	//specified by the provinceID.
	//If the province ID is not found in the file, file is unchanged.
	public void add(PopGroup pops) throws FileNotFoundException {
		setup();
		//Do the adding.
		ArrayList<PopGroup> groups = parser.groupByProvince(provinceID);
		groups.add(pops);
		writeGroups(groups);
		overwrite();	
	}
	
	//Takes in an array list of PopGroups to be added to the desired file.
	public void addAll(ArrayList<PopGroup> pops) throws FileNotFoundException{
		setup();
		ArrayList<PopGroup> groups = parser.groupByProvince(provinceID);
		groups.addAll(pops); //Merge the two lists.
		writeGroups(groups);
		overwrite();
	}
	
	//Performs the writing of PopGroups from an ArrayList.
	private void writeGroups(ArrayList<PopGroup> groups) {
		String currentLine;
		while(input.hasNextLine()) {
			currentLine = input.nextLine();
			output.println(currentLine);
			if(currentLine.equals(provinceID + " = {")) {
				for(PopGroup g : groups) {
					g.printPopGroup(output);
				}
			}
		}
	}
	
	//Subtracts the passed PopGroup from the province.
	//This means that if the total population size of the relevant 
	//pops in the file are less than or equal to the number being subtracted,
	//all groups containing it will be deleted. If the number is more than the
	//number being subtracted, there will be a single remainder group created 
	//(with all others being deleted).
	
	//CURRENTLY BROKEN - Must use Iterator to resolve ConcurrentModificationException
	public void subtract(PopGroup pops) throws FileNotFoundException{
		setup();
		ArrayList<PopGroup> groups = parser.groupByProvince(provinceID);
		int originalSize = 0; //Keep track of number of relevant pops to begin with in the province
		for(PopGroup g : groups) {
			if(g.hasSameAttributes(pops)) { //Remove ALL relevant popGroups, reconstitute later
				originalSize = originalSize + g.getSize(); //get size of popGroup
				System.out.println(originalSize); //TEST
				groups.remove(g); //remove it 
			}
		}
		int newSize = originalSize - pops.getSize(); //Obtain new number of pops by subtraction
		if(newSize > 0) { //if there is a remainder, create a new popGroup to represent the entire remainder.
			groups.add(new PopGroup(pops.getType(), pops.getCulture(), pops.getReligion(), newSize));
		}
		writeGroups(groups);
		overwrite();
	}
	
	//Takes in an ArrayList of PopGroup objects to be subtracted from the file.
	//Methodology is the same as in subtract(), but is performed for multiple groups.
	public void subtractAll(ArrayList<PopGroup> pops) {
		
	}
	
	//Preps the scanners, printstreams, and files for editing.
	private void setup() throws FileNotFoundException {
		this.input = new Scanner(file);
		this.temp = new File("temp.txt");
		this.output = new PrintStream(temp); 
	}
	
	//Writes temp file to original file, deletes temp file.
	private void overwrite() throws FileNotFoundException {
		input.close(); //Close the Scanners and PrintStreams to allow for file deletion
		output.close();
		Scanner tempInput = new Scanner(temp);
		PrintStream originalOutput = new PrintStream(file);
		while(tempInput.hasNextLine()) {
			originalOutput.println(tempInput.nextLine());
		}
		tempInput.close();
		originalOutput.close();
		try {
			temp.delete(); //delete temp
		} catch(Exception e) {
			e.printStackTrace(); //if deletion throws an exception
		}		
	}
	
}
