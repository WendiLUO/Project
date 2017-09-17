/**
 *  Tests the LinkedList class by creating multiple objects of type Node<T>.
 *  Creates one object of type CSVReader class, which reads input from a CSV file. Uses
 *  the attributes stored in CSVReader object to create objects of type Country class.
 *  Then adds countries to a singly linked list.
 *
 * @author Foothill College, [YOUR NAME HERE]
 */

import java.util.Random;
import java.util.Scanner;

public class TestGenericList 
{
	// Sets the Scanner object to the beginning of the input stream
	// Since we will be reading user input in more than one place.
	// To avoid incorrectly closing/reopening closing the stream,
	// the reference to the stream is set as a class attribute.
	public static Scanner input = new Scanner(System.in);

	/**
	 * Builds a list of countries to debug.
	 */
	private void debugListOfCountries(Country [] allCountries)
	{
		// TODO: The purpose is to help you debug
		// Note: The implementation of method is optional.
	}

	/**
	 * Creates a generic linked list. Then based on the user's input,
	 * adds a random number of countries to the list.
	 * @param allCountries      An array of Country objects
	 */
	private LinkedList<Country> createRandomListOfCountries(Country [] allCountries)
	{	
		input = new Scanner(System.in);
		System.out.println("How many countries do you want to add to the list?");
		int requestedSize = Integer.parseInt(input.nextLine());

		// Build the list out of a random selection of countries.
		Random random = new Random();
		LinkedList<Country> selectedCountries = new LinkedList<Country>();
		for (int i = 0; i < requestedSize; i++)
		{
			int selectedIndex = random.nextInt(allCountries.length);
			selectedCountries.add(allCountries[selectedIndex]);
		}

		return selectedCountries;
	}

	/**
	 * Displays the list of countries. 
	 * Each country index is output along with the country's index in the list.
	 * @param countryList	list of countries to display
	 */
	private void displayGeneratedList(LinkedList<Country> countryList)
	{
		String countryNames = "";
		int counter = 0;

		// TODO: Implement a nested ListIterator class in your generic LinkedList class
		java.util.Iterator<Country> iterator = countryList.iterator();

		// Uses a reference of type ListIterator to traverse your country list and collect country names
		while(iterator.hasNext())
		{
			// NOTE: Each country should be output on a *separate* line.
			//       Otherwise, your output will not be considered readable.
			countryNames += counter + " " + iterator.next().getName();

			// uses a ternary operator to prettify the output
			countryNames += counter++ % 10 == 0 ? "\n" : ", ";
		}

		System.out.println("\nCountry Names: " + countryNames);
	}

	/**
	 * Tests the contains() method of LinkedList class. 
	 * @param selectedCountryList  list of countries to search
	 */
	private void testSearchInList(LinkedList<Country> selectedCountryList) 
	{		
		// Tests that the iterator of the LinkedList class by calling its toString() method.
		// Note: To debug your list, comment this line in
		System.out.println("\nSize of list: " + selectedCountryList.size());
		System.out.println("\nList of countries: " + selectedCountryList);

		// Check if the name of a country is in the list.
		// If the country is found, print the details.
		// Otherwise output not found.
		System.out.println("\nWhat country do you want to search for?");
		String countryNameToFind = input.nextLine();

		// NOTE: The generic linked list should be able to check whether
		//       the list has an element that equals the generic type
		//       passed as an argument to the contains() method.
		Country tmpCountry = new Country(countryNameToFind);
		Country foundCountry = selectedCountryList.contains(tmpCountry);
		if (foundCountry != null)
		{
			System.out.println("Country " + countryNameToFind + " found with details:\n" + foundCountry);
		}
		else
		{
			System.out.println("Country " + countryNameToFind + " not found.");	
		}
	}

	/**
	 * Tests the getNumSubscriptionsForPeriod() method of LinkedList class. 
	 * @param selectedCountryList  list of countries to search
	 */
	private void testSubscriptionsTotalInList(LinkedList<Country> selectedCountryList) 
	{
		// Testing getNumSubscriptionsForPeriod() method
		displayGeneratedList(selectedCountryList);

		System.out.printf("\nWhat country are you interested in? Enter a number between 0 and %d \n", selectedCountryList.size() - 1);
		int requestedCountryNum = Integer.parseInt(input.nextLine());
		Country foundCountry = selectedCountryList.getIndex(requestedCountryNum);

		System.out.println("\nWhat subscription period do you want to total? Enter [starting year],[end year].");
		String period = input.nextLine();
		String [] tokens = period.split(",");
		int requestedStartYear = Integer.parseInt(tokens[0]);
		int requestedEndYear = Integer.parseInt(tokens[1]);

		double totalSubscriptions = foundCountry.getNumSubscriptionsForPeriod(requestedStartYear,requestedEndYear);
		System.out.printf("Total subscriptions for %s between %d and %d is %f \n", foundCountry.getName(), requestedStartYear, requestedEndYear, totalSubscriptions);
	}


	/**
	 * Uses a CSV to parse a CSV file.
	 * Adds the data for each country to an array of Country objects.
	 * Adds a random selection of countries to a generic LinkedList object.
	 */
	public static void main(String[] args) 
	{		
		final String FILENAME = "resources/cellular.csv";	// Directory path for Mac OS X
		//final String FILENAME = "resources\\cellular.csv";	// Directory path for Windows OS (i.e. Operating System)

		// Parse the CSV data file
		//
		CSVReader parser = new CSVReader(FILENAME);

		String [] countryNames = parser.getCountryNames();
		int [] yearLabels = parser.getYearLabels();
		double [][] parsedTable = parser.getParsedTable();		


		// Create and set objects of type Country 
		//
		Country [] countries;
		countries = new Country[countryNames.length];

		Country current;
		for (int countryIndex = 0; countryIndex < countries.length; countryIndex++)
		{
			int numberOfYears = yearLabels.length;   

			// TODO: Initially convert your CountryList to a generic LinkedList and make sure that list builds 
			// 		 correctly using the original Country constructor which takes the numberOfYears to setup
			// 		 the array of subscriptions.
			// NOTE: Once you've verified that your generic LinkedList builds correctly,
			//       make sure to comment the line below before submitting.
			//current = new Country(countryNames[countryIndex], numberOfYears);		// version 1

			// TODO: Once you are successful in creating a generic LinkedList of countries, create a
			// 		 LinkedList of SubscriptionYear in the Country class.
			// 	     So, your Country class should no longer have an array of SubscriptionYear objects.
			current = new Country(countryNames[countryIndex]);	// version 2 and final version of Country constructor

			for (int yearIndex = 0; yearIndex < numberOfYears; yearIndex++)
			{
				double [] allSubscriptions = parsedTable[countryIndex];
				double countryData = allSubscriptions[yearIndex];
				current.addSubscriptionYear(yearLabels[yearIndex], countryData);
			}
			countries[countryIndex] = current;
		}

		TestGenericList application = new TestGenericList();

		// TODO: Initially, to test your output you may hard-code the number of 
		//       countries added, and the array positions selected.
		//		 However, make sure to comment this out before submitting your work.
		//application.debugListOfCountries(countries);

		LinkedList<Country> selectedCountryList = application.createRandomListOfCountries(countries);

		// Output the countries added to the generic LinkedList
		application.displayGeneratedList(selectedCountryList);

		// Searches the generic LinkedList of countries
		application.testSearchInList(selectedCountryList);

		// Tests retrieving subscription information 
		application.testSubscriptionsTotalInList(selectedCountryList);

		// flush the error stream
		System.err.flush();

		System.out.println("\nDone with TestGenericList.");
	}
}