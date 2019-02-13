package vic2_tools;

import java.util.*;
import java.io.*;


//An class which provides functionality to add and subtract pop groups from a pop file.
public class PopEditor {
	
	//TODO 
	//clear method (remove all pop groups from province)
	//
	//NOTE: add() and clear() are VERY similar. think of how this could be refactored. 
	//
	//subtract method
	//subtractAll method 
	
	private String fileName; //Name of the file to be edited
	private File file; //The file to be edited
	private int provinceID; //The ID of the province to be edited
	
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
		Scanner input = new Scanner(file);
		PrintStream output;
		File temp = new File("temp.txt");
		output = new PrintStream(temp); 
		
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
		input.close();
		output.close();
		overwrite(temp);
	}
	
	//Adds a single pop group to the desired file (overwrites) in the province
	//specified by the provinceID.
	//If the province ID is not found in the file, file is unchanged. 
	public void add(PopGroup pops) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		PrintStream output;
		File temp = new File("temp.txt");
		output = new PrintStream(temp);  
		
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
		output.close();
		input.close();
		overwrite(temp);
		System.out.println("Contained province ID: " + containsProvinceID); //Diagnostic
	}
	
	//Takes in an array list of PopGroups to be added to the desired file.
	public void addAll(ArrayList<PopGroup> pops) {
		
	}
	
	//Writes temp file to original file, deletes temp file.
	private void overwrite(File temp) throws FileNotFoundException {
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
