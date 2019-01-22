package vic2_tools;

import java.util.*;
import java.io.*;

//A set of tests for the PopParser class
public class PopParserTests {

	public static void main(String[] args) throws FileNotFoundException {
		String[] pop_types = {"aristocrats", "bureaucrats", "farmers", "clergymen", "laborers", "slaves", "soldiers", "communist peasants"};
		PopParser parser = new PopParser();
		int total = 0;
		for(int i = 0; i < pop_types.length; i++) {
			total = parser.addAll(pop_types[i]);
			System.out.println("Total " + pop_types[i] + ": " + total);
		}
	}
}
