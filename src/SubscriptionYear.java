/**
 * Representing the subscription data of a year.
 *
 * @author Foothill College, Wendi Luo
 */
public class SubscriptionYear {
	private int year;
	private double subscriptions;

	/**
	 * Create a {@link SubscriptionYear} instance.
	 * 
	 * @param year
	 *            the year of the subscription data
	 * @param numberOfSubscriptions
	 *            the number of subscriptions
	 */
	public SubscriptionYear(int year, double numberOfSubscriptions) {
		this.year = year;
		this.subscriptions = numberOfSubscriptions;
	}

	/**
	 * Get the year of subscription data.
	 * 
	 * @return the year represented
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Set the year of subscription data.
	 * 
	 * @param year
	 *            the year to be set as the subscription year
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Get the number of subscriptions.
	 * 
	 * @return the number of subscriptions.
	 */
	public double getSubscriptions() {
		return subscriptions;
	}

	/**
	 * Set the number of subscriptions.
	 * 
	 * @param subscriptions
	 *            the number of subscriptions to be set.
	 */
	public void setSubscriptions(double subscriptions) {
		this.subscriptions = subscriptions;
	}

	/**
	 * Override of the original toString method.
	 * 
	 * @return the number of subscriptions
	 */
	@Override
	public String toString() {
		return String.format("%.2f", subscriptions);
	}
}
