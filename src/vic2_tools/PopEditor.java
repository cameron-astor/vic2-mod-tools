package vic2_tools;

import java.util.*;
import java.io.*;


//An class which provides functionality to add and subtract pop groups from a pop file.
public class PopEditor {
	
	//TODO 
	//fix overwrite 
	//clear method (remove all pop groups from province)
	//subtract method
	//subtractAll method 
	
	private String fileName; //Name of the file to be edited
	private File file; //The file to be edited
	private boolean overwrite; //If true, the file is overwritten.
							   //If false, a new, edited file is created alongside the old one.
							   //Overwrite is FALSE by default.
	private int provinceID; //The ID of the province to be edited
	
	//Constructs a PopEditor. Takes the name of the file to be edited as a parameter.
	public PopEditor(String fileName) {
		this.fileName = fileName;
		this.file = new File(fileName);
		this.overwrite = false;
		this.provinceID = 0;
	}
	
	//Constructs a PopEditor. Takes a file object referring to the 
	//file to be edited as a parameter.
	public PopEditor(File file) {
		this.file = file;
		this.fileName = file.getName();
		this.overwrite = false;
		this.provinceID = 0;
	}
	
	//Returns the name of the file the editor is attached to.
	public String getFileName() {
		return this.fileName;
	}
	
	//Returns the current province ID.
	public int getProvinceID() {
		return this.provinceID;
	}
	
	//Returns the status of overwrite.
	public boolean getOverwriteStatus() {
		return this.overwrite;
	}
	
	//Sets the province being edited.
	public void setProvince(int provinceID) {
		this.provinceID = provinceID;
	}
	
	//Sets the overwrite flag.
	public void setOverwrite(boolean overwrite) {
		this.overwrite = overwrite;
	}
	
	//removes all PopGroups from the province 
	public void clear() {
		
	}
	
	//Adds a single pop group to the desired file.
	//If the province ID is not found in the file, file is unchanged.
	public void add(PopGroup pops) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		PrintStream output;
		if(overwrite) { //If overwrite is true, will print to the current file.
			output = new PrintStream(new File(fileName));          //OVERWRITE CURRENTLY BROKEN
		} else { //else creates a new output file with the _edited suffix.
			output = new PrintStream(new File(fileName.replace(".txt", "") + "_edited.txt"));
		}
		String currentLine;
		boolean containsProvinceID = false;
		while(input.hasNextLine()) {
			currentLine = input.nextLine();		
			output.println(currentLine);
			if(currentLine.equals(provinceID + " = {")) { //When desired province is reached 
				pops.printPopGroup(output);
				containsProvinceID = true;
			}							
		}
		input.close();
		System.out.println("Contained province ID: " + containsProvinceID);
	}
	
	//Takes in an array list of PopGroups to be added to the desired file.
	public void addAll(ArrayList<PopGroup> pops) {
		
	}
		
}
