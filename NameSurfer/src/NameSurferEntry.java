/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */
	public NameSurferEntry(String line) {
		// You fill this in //
		int index = line.indexOf(" ");
		name = line.substring(0, index);
		line = line.substring(index + 1);
		while (true) {
			index = line.indexOf(" ");
			if (index == -1) {
				Integer rank = Integer.parseInt(line.substring(0));
				rankList.add(rank);
				break;
			}
			Integer rank = Integer.parseInt(line.substring(0, index));
			rankList.add(rank);
			line = line.substring(index + 1);
		}
	}

/* Method: getName() */
/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		// You need to turn this stub into a real implementation //
		return name;
	}

/* Method: getRank(decade) */
/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade) {
		// You need to turn this stub into a real implementation //
		int index = (decade - START_DECADE) / 10;
		return rankList.get(index).intValue();
	}

/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	public String toString() {
		// You need to turn this stub into a real implementation //
		String str = "" + name + " [";
		for (int i = 0; i < rankList.size() - 1; i++) {
			str += rankList.get(i) + " ";
		}
		str += rankList.get(rankList.size() - 1) + "]";
		return str;
	}
	
	/* Instance variables */
	private String name;
	private ArrayList<Integer> rankList = new ArrayList<Integer>(); // Shouldn't make it static. Shouldn't let it be shared through different objects. You can try to see what happens.
}

