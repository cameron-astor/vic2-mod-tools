package vic2_tools;

import java.util.*;
import java.io.*;

//A set of tests for the PopEditor class
public class PopEditorTests {
	
	public static void main(String[] args) throws FileNotFoundException{
		File file = new File("./1_pops.txt");
		PopEditor editor = new PopEditor(file);  //field checks
		
		//group to be added 
		PopGroup group = new PopGroup("bureaucrats", "french", "sunni", 4500); 
		
		//which province?
		editor.setProvince(1);
		
		//overwrite?
		editor.setOverwrite(true);
		
		//check fields
		System.out.println(editor.getFileName());
		System.out.println(editor.getProvinceID());
		System.out.println(editor.getOverwriteStatus());
		
		//add
		editor.add(group);
	}


}
