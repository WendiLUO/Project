import java.util.Iterator;

/**
 * Representing a country and its data of subscriptions.
 * 
 * @author Foothill College, Wendi Luo
 */
public class Country {
	private String name;
	private LinkedList<SubscriptionYear> subscriptions;

	private int minYear = Integer.MAX_VALUE;
	private int maxYear = Integer.MIN_VALUE;

	private static final double NUM_FOR_PERIOD_ERROR_RESULT = -1f;

	/**
	 * Create a {@link Country} instance.
	 * 
	 * @param name
	 *            name of the country
	 */
	public Country(String name) {
		this.name = name;
		this.subscriptions = new LinkedList<SubscriptionYear>();
	}

	/** @return the name */
	public String getName() {
		return name;
	}

	/**
	 * Add a country's data to the stored table.
	 * 
	 * @param year
	 *            the year of the subscription data to be added
	 * @param numberOfSubscriptions
	 *            the number of subscription of the country in the given year
	 * 
	 */
	public void addSubscriptionYear(int year, double numberOfSubscriptions) {
		minYear = minYear > year ? year : minYear;
		maxYear = maxYear < year ? year : maxYear;
		subscriptions.add(new SubscriptionYear(year, numberOfSubscriptions));
	}

	/**
	 * Calculate the total number of subscriptions during the provided period.
	 * 
	 * @param startingYear
	 *            the starting year of period user trying to retrieve
	 * @param endingYear
	 *            the ending year of period user trying to retrieve
	 * @return {@link #NUM_FOR_PERIOD_ERROR_RESULT}, when the period provided is
	 *         invalid OR the total number of subscription of this given period,
	 *         if all provided parameters are valid
	 */
	public double getNumSubscriptionsForPeriod(int startingYear, int endingYear) {
		if (startingYear > maxYear || endingYear < minYear || endingYear < startingYear) {
			System.out.printf("ERROR : requested period is not valid.");
			return NUM_FOR_PERIOD_ERROR_RESULT;
		}
		Iterator<SubscriptionYear> iterator = subscriptions.iterator();
		double sum = 0;
		while (iterator.hasNext()) {
			SubscriptionYear year = iterator.next();
			if (year.getYear() >= startingYear && year.getYear() <= endingYear) {
				sum += year.getSubscriptions();
			}
		}
		return sum;
	}

	/**
	 * Override of the original toString method.
	 * 
	 * @return a string representing the stored list of subscriptions in a print
	 *         friend form
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.name);
		sb.append('\t');
		Iterator<SubscriptionYear> iterator = subscriptions.iterator();
		int counter = 0;
		while (iterator.hasNext()) {
			SubscriptionYear year = iterator.next();
			sb.append(String.format("%.2f", year.getSubscriptions()));
			sb.append('\t');
			// We don't want to add enter when this is the last data.
			if ((counter + 1) % 10 == 0 && counter != subscriptions.size() - 1) {
				sb.append('\n');
			}
			++counter;
		}
		return sb.toString();
	}

	/**
	 * Override of the original equals method. We consider two Country object
	 * equals if the name of two countries are the same regardless of its
	 * subscription year.
	 * 
	 * @return whether two countries objects are equal.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Country)) {
			return false;
		}
		Country other = (Country) obj;
		return other.name.equals(this.name);
	}

}
