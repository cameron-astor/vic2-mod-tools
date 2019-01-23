package vic2_tools;

//For copying the contents of country pop files into a single file.
//The relevant 1836.1.1 folder must be placed in this project's directory.

import java.io.*;
import java.util.*;

public class CopyPopFiles {
	public static void main(String[] args) throws FileNotFoundException {
		PrintStream output = new PrintStream(new File("all_pops_vanilla.txt"));
		File dir = new File("1836.1.1_vanilla");
		for(File file : dir.listFiles() ) {
			Scanner input = new Scanner(file);
			//Before each file's contents add the name of said file (for ease of organization)
			String areaName = file.getName();
			output.println("##" + areaName); 
			output.println();  //Blank line for organization 
			//Begin parsing current file
			while(input.hasNextLine()) {
				output.println(input.nextLine());
			}
			output.println();
			input.close();
		}
	}
}
