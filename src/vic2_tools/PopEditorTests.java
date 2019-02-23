package vic2_tools;

import java.util.*;
import java.io.*;


//A set of tests for the PopEditor class
public class PopEditorTests {
	
	public static final PopGroup GROUP = new PopGroup("artisans", "dixie", "daoist", 9840);
	public static final String FILENAME = "./all_pops_copy.txt";
	
	public static void main(String[] args) throws FileNotFoundException{

		PopEditor editor = new PopEditor(FILENAME);
		PopGroup sameGroup = new PopGroup("artisans", "dixie", "daoist", 9840);
		PopGroup group2 = new PopGroup("capitalists", "occitan", "animist", 320000);
		PopGroup group3 = new PopGroup("clerks", "ukrainian", "orthodox", 21);
		PopGroup austrian = new PopGroup("aristocrats", "south_german", "catholic", 450);
		PopGroup group4 = new PopGroup("capitalists", "occitan", "animist", 160000);
		
//		//CLEAR test 
//		editor.setProvince(613);
//		editor.clear();
		
		//which province?
		editor.setProvinceID(613);
		
		//check fields
//		System.out.println(editor.getFileName());
//		System.out.println(editor.getProvinceID());
		
		//testAddRuntime(editor);

		//test addAll
//		ArrayList<PopGroup> list = new ArrayList<PopGroup>();
//		list.add(GROUP);
//		list.add(group2);
//		list.add(group3);
//		editor.addAll(list);
		
		long startTime = System.currentTimeMillis(); //Test runtime
		//Subtract test
		editor.add(group2);
		editor.subtract(group4);
		
		long endTime = System.currentTimeMillis(); //Test runtime
		System.out.println("Runtime: " + (endTime - startTime) + " milliseconds");
		//COMPLETE TEST 
		
	}

}
