package vic2_tools;

import java.util.*;
import java.io.*;

//TODO
//Learn how to write more robust and cohesive tests and make this one better.

//A set of tests for the PopEditor class
public class PopEditorTests {
	
	public static final PopGroup GROUP = new PopGroup("artisans", "dixie", "daoist", 9840);
	public static final String FILENAME = "./Austria.txt";
	
	public static void main(String[] args) throws FileNotFoundException{

		PopEditor editor = new PopEditor(FILENAME);
		
		//CLEAR test 
		editor.setProvince(613);
		editor.clear();
		
		//which province?
		editor.setProvince(613);
		
		//check fields
		System.out.println(editor.getFileName());
		System.out.println(editor.getProvinceID());
		
		testAddRuntime(editor);

	}
	
	//Tests the runtime of the add() method.
	private static void testAddRuntime(PopEditor editor) throws FileNotFoundException {
		//Tests the runtime 
		long startTime = System.currentTimeMillis(); //Test runtime
		//add
		editor.add(GROUP);
		
		long endTime = System.currentTimeMillis(); //Test runtime
		System.out.println("Runtime: " + (endTime - startTime) + " milliseconds");
	}

}
