/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		//	 You fill in the rest //
		this.update();
	}
	
	/* Method: addLines */
	/**
	 * Add lines and decades.
	 */
	private void addLines() {
		dx = this.getWidth() / NDECADES;
		
		// Add the line on the top. //
		add(new GLine(0, GRAPH_MARGIN_SIZE, this.getWidth(), GRAPH_MARGIN_SIZE));
		
		// Add vertical lines and numbers. //
		for (int i = 0; i < NDECADES; i++) {
			add(new GLine(i * dx, 0, i * dx, this.getHeight()));
			add(new GLabel("" + (START_DECADE + i * 10)), i * dx + 2, this.getHeight());
		}
		
		// Add the line on the bottom. //
		add(new GLine(0, this.getHeight() - GRAPH_MARGIN_SIZE, this.getWidth(), this.getHeight() - GRAPH_MARGIN_SIZE));
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		//	 You fill this in //
		entryList.clear();
		this.update();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		// You fill this in //
		if (entry != null) {
			entryList.add(entry);
			this.update();
		}
	}
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		//	 You fill this in //
		this.removeAll();
		addLines();
		plotGraphs();
	}
	
	/* Method: plotGraphs */
	/**
	 * Plot the graph.
	 */
	private void plotGraphs() {
		dy = (this.getHeight() - GRAPH_MARGIN_SIZE * 2.0) / MAX_RANK;
		for (int i = 0; i < entryList.size(); i++) {
			colorNum = i;
			NameSurferEntry entry = entryList.get(i);
			ArrayList<GPoint> pointList = findPoints(entry);
			addPlots(pointList);
			addLabel(pointList, entry);
		}
	}
	
	/* Method: addLabel */
	private void addLabel(ArrayList<GPoint> pointList, NameSurferEntry entry) {
		String name = entry.getName();
		for (int i = 0; i < NDECADES; i++) {
			String label ="" + name;
			int decade = START_DECADE + 10 * i;
			int rank = entry.getRank(decade);
			if (rank == 0) {
				label += " *"; 
			} else {
				label += " " + rank;
			}
			GLabel newLabel = new GLabel(label);
			newLabel.setColor(pickColor(colorNum));
			add(newLabel, pointList.get(i).getX() + 2, pointList.get(i).getY());
		}
	}
	
	/* Method: pickColor */
	/**
	 * Pick a color for each plot.
	 * @param num
	 * @return
	 */
	private Color pickColor(int num) {
		int r = num % totalColors;
		switch(r) {
		case 0: return Color.black;
		case 1: return Color.red;
		case 2: return Color.blue;
		case 3: return Color.magenta;
		default: return null;
		}
	}
	
	/* Method: addPlots */
	/**
	 * Plot the points from the given point list.
	 * @param pointList
	 */
	private void addPlots(ArrayList<GPoint> pointList) {
		for (int i = 1; i < pointList.size(); i++) {
			GLine newLine = new GLine(pointList.get(i - 1).getX(), pointList.get(i - 1).getY(), pointList.get(i).getX(), pointList.get(i).getY());
			newLine.setColor(pickColor(colorNum));
			add(newLine);
		}
	}
	
	/* Method: findPoints */
	/**
	 * Find all the points for each rank.
	 * @param entry
	 * @return
	 */
	private ArrayList<GPoint> findPoints(NameSurferEntry entry) {
		ArrayList<GPoint> pointList = new ArrayList<GPoint>();
		for (int i = 0; i < NDECADES; i++) {
			int decade = START_DECADE + 10 * i;
			int rank = entry.getRank(decade);
			double x = i * dx;
			double y = 0;
			if (rank == 0) {
				y = this.getHeight() - GRAPH_MARGIN_SIZE;
			} else {
				y = GRAPH_MARGIN_SIZE + rank * dy;
			}
			pointList.add(new GPoint(x, y));
		}
		return pointList;
	}
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
	/* Declare instance variables. */
	private static int dx, colorNum;
	private static double dy;
	private ArrayList<NameSurferEntry> entryList = new ArrayList<NameSurferEntry>();
	private static int totalColors = 4;
}
