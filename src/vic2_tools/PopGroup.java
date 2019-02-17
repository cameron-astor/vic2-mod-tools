package vic2_tools;

import java.util.*;
import java.io.*;

//TODO
//Implement comparable?

//Represents a group of pops in a certain pop file as defined by their type, culture,
//religion, and size.
public class PopGroup {
	 
	private String popType;
	private String culture;
	private String religion;
	private int size;
	
	//Constructs a new PopGroup. A PopGroup represents the smallest unit of population definition
	//in a pop file. For example:
	//aristocrats = {
	//	culture = alaskan
	//	religion = orthodox
	//	size = 5
	//}
	//A PopGroup stores all of the relevant data for a single one of these groups.
	
	//TODO
	//Overhaul constructor -> make it a builder. 
	
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
	
	//Checks for equality in qualitative attributes 
	//(type, culture, and religion)
	//Returns a boolean.
	public boolean equalsAttributes(PopGroup g) {
		return(this.popType == g.popType && this.culture == g.culture && this.religion == g.religion);
	} 
	
	//Prints a PopGroup to a text file with a blank line underneath.
	//Must be given a PrintStream to do so with.
	public void printPopGroup(PrintStream output) {
		output.println("	" + popType + " = {");
		output.println("		culture = " + culture);
		output.println("		religion = " + religion);
		output.println("		size = " + size);
		output.println("	}");
		output.println();
	}
	
	@Override
	//Returns a string with the popGroup's information:
	//type, culture, religion, and size
	public String toString() {
		return "type: " + popType + " culture: " + culture + " religion: " + religion + " size: " + size;
	}
}
