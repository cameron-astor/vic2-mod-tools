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
	
	//Constructs a new PopGroup in them most specific case, with an explicitly stated pop type, 
	//religion, and culture. If one or more is to be omitted, input null. 
	public PopGroup(String inputPopType, String inputReligion, String inputCulture, 
					String inputFileName) {
		
	}
	
}
