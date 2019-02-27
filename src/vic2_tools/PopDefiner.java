package vic2_tools;

import java.io.*;
import java.util.*;

//A program for editing Victoria II population files.
public class PopDefiner {

	public static int provinceID; //The current province being worked with
	public static String dirPath = "./all_pops_provinces/";
	
	public static final boolean ENTER_DIR = false; //Specify directory in the program or not
	
	public static void main(String[] args) {
		intro();
		Scanner in = new Scanner(System.in);
		if (ENTER_DIR) {
			dirPath = inputDir(in);
		} else {
			System.out.println("Directory already specified");
			System.out.println();
		}
		provinceID = inputID(in);
				
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
}
