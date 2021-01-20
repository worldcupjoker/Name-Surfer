import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {
	
/* Constructor: NameSurferDataBase(filename) */
/**
 * Creates a new NameSurferDataBase and initializes it using the
 * data in the specified file.  The constructor throws an error
 * exception if the requested file does not exist or if an error
 * occurs as the file is being read.
 */
	public NameSurferDataBase(String filename) {
		// You fill this in //
		rd = openFileReader(filename);
		try {
			while(true) {
				String line = rd.readLine();
				if (line == null) {
					break;
				}
				nameLine.add(line);				
			}
			rd.close();
		} catch (IOException ex) {
			//println("An I/O exception has occurred.");
		}
	}
	
	/* Method: openFileReader */
	/**
	 * Open and read a file.
	 * @param prompt
	 * @return
	 */
	private BufferedReader openFileReader(String fileName) {
		BufferedReader rd = null;
		while (rd == null) {
			try {
				rd = new BufferedReader(new FileReader(fileName));
			} catch (IOException ex) {
				//println("Can't open that file.");
			}
		}
		return rd;
	}
	
/* Method: findEntry(name) */
/**
 * Returns the NameSurferEntry associated with this name, if one
 * exists.  If the name does not appear in the database, this
 * method returns null.
 */
	public NameSurferEntry findEntry(String name) {
		// You need to turn this stub into a real implementation //
		// Binary search //
		int lh = 0;
		int rh = nameLine.size() - 1;
		while (true) {
			if (lh > rh) {
				return null;
			}
			int mid = (lh + rh) / 2;
			
			// Get the name from the name line. //
			String midLine = nameLine.get(mid);
			int index = midLine.indexOf(" ");
			midLine = midLine.substring(0, index);
			
			// Compare the name strings. //
			int cmp = name.compareToIgnoreCase(midLine);
			if (cmp == 0) {
				return new NameSurferEntry(nameLine.get(mid));
			} else if (cmp < 0) {
				rh = mid - 1;
			} else {
				lh = mid + 1;
			}
		}
	}
	
	/* Instance variables */
	private BufferedReader rd;
	private ArrayList<String> nameLine = new ArrayList<String>();
}

