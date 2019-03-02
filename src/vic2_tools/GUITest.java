package vic2_tools;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

//GUI experimentation
public class GUITest extends Frame {

	public static void main(String[] args) {
		
		//Create the frame
	       JFrame frame = new JFrame("GUI");
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.setSize(500,400);
	       
	    //Create a menu bar
	       JMenuBar mb = new JMenuBar();
	       JMenu m1 = new JMenu("File");
	       JMenu m2 = new JMenu("Edit");
	       mb.add(m1);
	       mb.add(m2);
	       JMenuItem m1_1 = new JMenuItem("Open");
	       JMenuItem m1_2 = new JMenuItem("Save");
	       m1.add(m1_1);
	       m1.add(m1_2);
	       
	        //Creating the panel at bottom and adding components
	        JPanel panel = new JPanel(); // the panel is not visible in output
	        JLabel label = new JLabel("Enter Text");
	        JTextField tf = new JTextField(10); // accepts upto 10 characters
	        JButton send = new JButton("Send");
	        JButton reset = new JButton("Reset");
	        panel.add(label); // Components Added using Flow Layout
	        panel.add(label); // Components Added using Flow Layout
	        panel.add(tf);
	        panel.add(send);
	        panel.add(reset);

	    //Makes the created frame visible
	       frame.getContentPane().add(BorderLayout.SOUTH, panel);
	       frame.getContentPane().add(BorderLayout.NORTH, mb);
	       frame.setVisible(true);
	}

}
