package vic2_tools;

import java.util.*;
import java.io.*;

//A set of tests for the PopEditor class
public class PopEditorTests {
	
	public static void main(String[] args) throws FileNotFoundException{
		File file = new File("./Austria.txt");
		PopEditor editor = new PopEditor(file);  //field checks
		
		//ADD test
//		//group to be added 
//		PopGroup group = new PopGroup("bureaucrats", "french", "sunni", 4500); 
//		
//		//which province?
//		editor.setProvince(1);
//		
//		//check fields
//		System.out.println(editor.getFileName());
//		System.out.println(editor.getProvinceID());
//		
//		//add
//		editor.add(group);
		
		//CLEAR test 
		editor.setProvince(613);
		editor.clear();
	}


}
