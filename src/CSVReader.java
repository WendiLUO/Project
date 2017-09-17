import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class parses a csv file into a formatted data table and provides all
 * core information of this stored table.
 * 
 * @author Foothill College, Wendi Luo
 */
public class CSVReader {

	/**
	 * List of the country names associated with {@link #cellularDataTable}
	 * (i.e. the row label).
	 */
	private String[] countryNames;

	/**
	 * List of the year associated with {@link #cellularDataTable} (i.e. the
	 * column label).
	 */
	private int[] yearLabels;

	/**
	 * The data of each country of each year we want to store.
	 */
	private double[][] cellularDataTable;

	/**
	 * The number of countries we want to store in {@link #cellularDataTable}.
	 */
	private int numberOfCountries = 0;

	/**
	 * Contruct the {@link CSVReader} object, parsing the file given and stored
	 * all the core information into their corresponding table.
	 * 
	 * @param fileName
	 *            The file path of the csv file to be parsed.
	 */
	public CSVReader(String fileName) {
		Scanner sc = null;
		try {
			sc = new Scanner(new File(fileName));
			if (!sc.hasNextLine()) {
				System.out.println("The given file is empty.");
				return;
			}

			// Locate the line starting with "Number of countries" and record it
			// down.
			String[] currentList = skipLinesUntilPatternShowedInFirstColumn("Number of countries", sc);
			if (currentList != null && currentList.length >= 2) {
				numberOfCountries = Integer.parseInt(currentList[1]);
			} else {
				System.out.println("The given file format is wrong.");
				return;
			}

			// Locate the line starting with "Country Name", and read the rest
			// of that line
			// one by one into the yearLabels array.
			currentList = skipLinesUntilPatternShowedInFirstColumn("Country Name", sc);
			if (currentList != null && currentList.length >= 2) {
				yearLabels = new int[currentList.length - 1];
				for (int i = 0; i < yearLabels.length; ++i) {
					yearLabels[i] = Integer.parseInt(currentList[i + 1]);
				}
			} else {
				System.out.println("The given file format is wrong.");
				return;
			}

			// Initializing the countryNames array and cellularDataTable array.
			countryNames = new String[numberOfCountries];
			cellularDataTable = new double[numberOfCountries][yearLabels.length];

			// Parse the rest of the file into countryNames and
			// cellularDataTable one by one.
			int i = 0;
			while (sc.hasNextLine() && i < numberOfCountries) {
				currentList = sc.nextLine().split(",");
				if (currentList != null && currentList.length >= 2) {
					countryNames[i] = currentList[0];
					for (int j = 0; j < yearLabels.length; ++j) {
						cellularDataTable[i][j] = Double.parseDouble(currentList[j + 1]);
					}
				} else {
					// Aborting the program because the current line's format is
					// not correct.
					System.out.println("The given file format is wrong.");
					return;
				}
				++i;
			}

		} catch (FileNotFoundException e) {
			System.out.println("The given file name is invalid.");
		} catch (NumberFormatException e) {
			System.out.println("The given file format is wrong.");
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
	}

	/**
	 * Keep skipping the csv file until the first line with the given pattern
	 * equals to the first column of the csv file we are parsing.
	 * 
	 * @return a string array representing the row of the csv file matches all
	 *         the requirement.
	 */
	private String[] skipLinesUntilPatternShowedInFirstColumn(String pattern, Scanner sc) {
		if (!sc.hasNextLine()) {
			// No more line, aborting.
			return null;
		}

		String[] currentList = sc.nextLine().split(",");
		// Keep finding until the correct pattern shows in the first column.
		while (currentList != null && currentList.length != 0 && !currentList[0].equals(pattern)) {
			if (sc.hasNextLine()) {
				currentList = sc.nextLine().split(",");
			} else {
				// Reaches the end of the file, yet still can't find the correct
				// pattern.
				// Aborting and return null.
				return null;
			}
		}
		return currentList;
	}

	/**
	 * Returns the list of the country names associated with
	 * {@link #cellularDataTable} (i.e. the row label).
	 * 
	 * @return countryNames
	 */
	public String[] getCountryNames() {
		return countryNames;
	}

	/**
	 * Returns the list of the year associated with {@link #cellularDataTable}
	 * (i.e. the column label).
	 * 
	 * @return yearLabels
	 */
	public int[] getYearLabels() {
		return yearLabels;
	}

	/**
	 * Returns the numbers of years we recorded.
	 * 
	 * @return numbers of years we recorded
	 */
	public int getNumberOfYears() {
		return yearLabels.length;
	}

	/**
	 * Returns the parsed table stored.
	 * 
	 * @return cellularDataTable
	 */
	public double[][] getParsedTable() {
		return cellularDataTable;
	}

}
