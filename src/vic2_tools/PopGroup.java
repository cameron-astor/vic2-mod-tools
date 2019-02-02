package vic2_tools;

import java.util.*;
import java.io.*;

//Represents a group of pops in a certain pop file as defined by their type, culture,
//religion, and size.
public class PopGroup {
	
	private int size; 
	private String popType;
	private String religion;
	private String culture;
	
	//Constructs a new PopGroup. A PopGroup represents the smallest unit of population definition
	//in a pop file. For example:
	//aristocrats = {
	//	culture = alaskan
	//	religion = orthodox
	//	size = 5
	//}
	//A PopGroup stores all of the relevant data for a single one of these groups.
	
	public PopGroup(String inputPopType, String inputCulture, String inputReligion, int size) {
		
		this.popType = inputPopType;
		this.culture = inputCulture;
		this.religion = inputReligion;
		this.size = size;
		
	}
	
	//Returns the number of pops in this pop group
	public int getSize() {
		return size;
	}
	
	//Returns the pop type of this group
	public String getType() {
		return popType;
	}
	
	//Returns the religion of the pops in this pop group
	public String getReligion() {
		return religion;
	}
	
	//Returns the culture of the pops in this pop group
	public String getCulture() {
		return culture;
	}
	
	//Prints a PopGroup to a text file with a blank line underneath
	//Must be given a PrintStream to do so with.
	public void printPopGroup(PrintStream output) {
		output.println("	" + popType + " = {");
		output.println("		culture = " + culture);
		output.println("		religion = " + religion);
		output.println("		size = " + size);
		output.println("	}");
		output.println();
	}
	
}
