package vic2_tools;

import java.io.*;
import java.util.*;

//TODO
//Press P to change province
//Press E to quit
//FEEDBACK: print current province, current pop group, ...
//
//Turn the readGroupString function into a reusable function elsewhere

//A program for editing Victoria II population files.
public class PopDefiner {

	public static int provinceID; //The current province being worked with
	public static String dirPath = "./all_pops_provinces/"; //Directory of pop files to work with
	
	public static final boolean ENTER_DIR = false; //Specify directory in the program or not
	
	public static void main(String[] args) throws FileNotFoundException {
		intro();
		Scanner in = new Scanner(System.in);
		if (ENTER_DIR) {
			System.out.println("Enter directory: ");
			dirPath = inputDir(in);
		} else {
			System.out.println("Directory already specified");
			System.out.println();
		}
		File dir = new File(dirPath);
		
		//Create PopParser and PopEditor objects to be used.
		PopParser parser = null;
		boolean valid = false;
		while (!valid) {
			try {
				parser = createParser(in, dir);     //Note: you shouldn't have to do this work here. Go edit PopParser and have its constructor throw an exception.
				valid = true; 
			} catch (Exception e) {
				System.out.println("File not found, try another ID: ");
			}
		}
		//Create editor
		PopEditor editor = new PopEditor(parser.getFile());
		editor.setProvinceID(provinceID); //Set the province of the editor to the province in the file
		
		PopGroup group = defineGroup(in);
	}

	//Prints the introduction
	public static void intro() {
		System.out.println("Welcome to the Victoria II Pop Definer v0.00 pre-alpha.");
		System.out.println();	
		System.out.println("Note that this program assumes that pop files are split up by province");
		System.out.println("in the format 1_pops.txt, 2_pops.txt, ... ");
		System.out.println();	
	}
	
	//Get path of pop file directory
	public static String inputDir(Scanner in) {
		System.out.print("Enter the path of the pop file directory (ending in a /): ");
		return in.next();
	}
	
	//Reads in the desired province ID from the user.
	public static int inputID(Scanner in) {
		System.out.print("Enter the ID of the province you would like to edit: ");
		String input = null;
		boolean valid = false;
		while (!valid) {
			input = in.next();
			try  { //check if input is an integer
				Integer.parseInt(input);
				valid = true;
			} catch (Exception e) {
				System.out.print("Invalid input, try again: ");
			}
		}
		return Integer.parseInt(input);
	}
	
	//Create a PopParser
	public static PopParser createParser(Scanner in, File dir) throws FileNotFoundException {
		PopParser parser = null;

		//Check if file is present in the directory
		boolean valid = false;
		while (!valid) {
			provinceID = inputID(in);
			for (File f : dir.listFiles()) {
				int fileID = Integer.parseInt(f.getName().split("_")[0]);
				if (fileID == provinceID) {
					valid = true;
					break; //if the file is found
				} 
			} 
			if (valid) {
				break;
			}
			throw new IllegalArgumentException(); //File is not present
		}
		
		parser = new PopParser(provinceID + "_pops.txt");
		return parser;
	}
	
	//Asks the user to define a pop group to add or subtract.
	public static PopGroup defineGroup(Scanner in) {
		System.out.println();
		System.out.println("Define a population group in the following format:");
		System.out.println("aristocrats, french, catholic, 2500");
		System.out.println();
		
		String input = null; 
		PopGroup group = null;
		boolean valid = false;
		while (!valid) {
			input = in.next(); //Read in the user's input
			try {
				group = readGroupString(input);
				valid = true;
			} catch (Exception e){
				System.out.println("Bad format, try again: ");
				e.printStackTrace();
			}			
		}
		return group;
	}
	
	//Takes in a string formatted like a PopGroup toString result
	//and returns a new PopGroup object with its attributes.
	public static PopGroup readGroupString(String s) {
		String[] attributes = s.split(", ");
		System.out.println(Arrays.toString(attributes));//test
		PopGroup group = new PopGroup(attributes[0], attributes[1], attributes[2], Integer.parseInt(attributes[3]));
		return group;
	}
}
