package vic2_tools;

import java.util.*;
import java.io.*;


//An class which provides functionality to add and subtract pop groups from a pop file.
public class PopEditor {
	
	//TODO 
	//subtract method
	//addAll method
	//subtractAll method 
	
	private String fileName; //Name of the file to be edited
	private File file; //The file to be edited
	private int provinceID; //The ID of the province to be edited
	
	private File temp; //A pointer to a temporary file to be used in editing 
	private Scanner input; //A pointer to a Scanner to be used in editing
	private PrintStream output; //A pointer to a PrintStream to be used in editing
	
	//Constructs a PopEditor. Takes the name of the file to be edited as a parameter.
	public PopEditor(String fileName) {
		this.fileName = fileName;
		this.file = new File(fileName);
		this.provinceID = 0;
	}
	
	//Constructs a PopEditor. Takes a file object referring to the 
	//file to be edited as a parameter.
	public PopEditor(File file) {
		this.file = file;
		this.fileName = file.getName();
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
	
	//Sets the province being edited.
	public void setProvince(int provinceID) {
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
				output.println();
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
		//Do the adding
		String currentLine;
		boolean containsProvinceID = false; //Diagnostic
		while(input.hasNextLine()) {
			currentLine = input.nextLine();		
			output.println(currentLine);
			if(currentLine.equals(provinceID + " = {")) { //When desired province is reached 
				pops.printPopGroup(output);
				containsProvinceID = true; //Diagnostic
			}			
		}
		overwrite();
		System.out.println("Contained province ID: " + containsProvinceID); //Diagnostic
	}
	
	//Takes in an array list of PopGroups to be added to the desired file.
	public void addAll(ArrayList<PopGroup> pops) {
		
	}
	
	//Subtracts the passed PopGroup from the province.
	//This means that if the total population size of the relevant 
	//pops in the file are less than or equal to the number being subtracted,
	//all groups containing it will be deleted. If the number is more than the
	//number being subtracted, there will be a single remainder group created 
	//(with all others being deleted).
	public void subtract(PopGroup pops) throws FileNotFoundException{
		setup();
		String currentLine;
		while(input.hasNextLine()) {
			currentLine = input.nextLine();
			output.println(currentLine);
			if(currentLine.equals(provinceID + " = {")) {
				//Hold off on implementing the rest of this until PopParser has a
				//method to put all of the PopGroups of a province into an ArrayList
			}
		}
		
	}
	
	public void subtractAll(ArrayList<PopGroup> pops) {
		
	}
	
	//Preps the scanners, printstreams, and files for editing.
	private void setup() throws FileNotFoundException {
		this.input = new Scanner(file);
		temp = new File("temp.txt");
		output = new PrintStream(temp); 
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
			e.printStackTrace();
		}		
	}
	
	
}
