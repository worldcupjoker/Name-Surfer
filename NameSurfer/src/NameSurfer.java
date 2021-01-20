/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
	    // You fill this in, along with any helper methods //
		this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		addTextField();
		addButtons();
		graph = new NameSurferGraph();
		add(graph);
		addActionListeners();
	}
	
	/* Method: addTextField */
	/**
	 * Add a text field.
	 */
	private void addTextField() {
		tf = new JTextField(10);
		add(new JLabel("Name"), SOUTH);
		add(tf, SOUTH);
		tf.addActionListener(this);
	}
	
	/* Method: addButtons */
	private void addButtons() {
		add(new JButton("Graph"), SOUTH);
		add(new JButton("Clear"), SOUTH);
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		// You fill this in //
		String cmd = e.getActionCommand();
		String text = tf.getText();
		if (e.getSource() == tf) {
			drawGraphs();
		}
		if (cmd.equals("Graph")) {
			drawGraphs();
		}
		if (cmd.equals("Clear")) {
			//println("Clear");
			graph.clear();
		}
	}
	
	/* Method: graph */
	private void drawGraphs() {
		String text = tf.getText();
		NameSurferEntry entry = data.findEntry(text);
		graph.addEntry(entry);
		//String str = entry.toString();
		//println("Graph: " + str);
	}
	
	/* Instance variables */
	private JTextField tf;
	private NameSurferDataBase data = new NameSurferDataBase(NAMES_DATA_FILE);
	private NameSurferGraph graph;
}
