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
	private File file;
	private String fileName; 
	private PopParser parser; //For helping to resolve size
	
	//Constructs a new PopGroup in them most specific case, with an explicitly stated pop type, 
	//religion, and culture. If one or more is to be omitted, input null. 
	public PopGroup(String inputPopType, String inputReligion, String inputCulture, 
					String inputFileName) throws FileNotFoundException{
		
		this.popType = inputPopType;
		this.religion = inputReligion;
		this.culture = inputCulture;
		this.fileName = inputFileName;
		this.file = new File("fileName");
		this.parser = new PopParser(fileName);
		this.size = resolveSize();
		
	}
	
	//Returns the number of pops in this pop group
	public int getSize() {
		return size;
	}
	
	//Returns the religion of the pops in this pop group
	public String getReligion() {
		return religion;
	}
	
	//Returns the culture of the pops in this pop group
	public String getCulture() {
		return culture;
	}
	
	//Returns the name of the file this pop group was extracted from
	public String getFileName() {
		return fileName;
	}
	
	private int resolveSize() throws FileNotFoundException{
		Scanner input = new Scanner(fileName);
		int total = 0;
		
		return total;
	}
	
	//Prints a PopGroup to a text file
	public void printPopGroup(File file, PrintStream output) {
		
	}
	
}
