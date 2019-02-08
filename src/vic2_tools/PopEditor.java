package vic2_tools;

import java.util.*;
import java.io.*;

//TODO 
//Throw an exception or some other such thing for if the file does not contain
//the desired province.

//An class which provides functionality to add and subtract pop groups from a pop file.
public class PopEditor {
	
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
	
	//Sets the province being edited.
	public void setProvince(int provinceID) {
		this.provinceID = provinceID;
	}
	
	//Sets the overwrite flag.
	public void setOverwrite(boolean overwrite) {
		this.overwrite = overwrite;
	}
	
	//Adds a single pop group to the desired file.
	public void add(PopGroup pops) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		PrintStream output;
		if(overwrite) { //If overwrite is true, will print to the current file.
			output = new PrintStream(file);
		} else { //else creates a new output file with the _edited suffix.
			output = new PrintStream(new File(fileName + "_edited.txt"));
		}
		String currentLine;
		while(input.hasNextLine()) {
			currentLine = input.nextLine();			
			if(currentLine.equals(provinceID + " = {")) {
				
			}				
			output.println(currentLine);
		}
		
	}
	
	//Takes in an array list of PopGroups to be added to the desired file.
	public void addAll(ArrayList<PopGroup> pops) {
		
	}
	
	
	
}
