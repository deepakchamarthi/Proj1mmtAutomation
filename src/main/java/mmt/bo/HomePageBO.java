package mmt.bo;

import java.util.ArrayList;
import java.util.List;

public class HomePageBO {

	// BO class memebrs.variables to hold values.
	private String cityName;

	private String checkInYear;
	private String checkInMonth;
	private String checkInDate;

	private String checkOutYear;
	private String checkOutMonth;
	private String checkOutDate;

	List<Integer> childrenAge = new ArrayList<Integer>();
	private int totalAdults;
	private int totalChildren;

	/**
	 * @return the childrenAge
	 */
	public List<Integer> getChildrenAge() {
		return childrenAge;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the totalAdults
	 */
	public int getTotalAdults() {
		return totalAdults;
	}

	/**
	 * @return the totalChildren
	 */
	public int getTotalChildren() {
		return totalChildren;
	}

	/**
	 * @param totalChildren the totalChildren to set
	 */
	public void setTotalChildren(int totalChildren) {
		this.totalChildren = totalChildren;
	}

	/**
	 * @return the checkInYear
	 */
	public String getCheckInYear() {
		return checkInYear;
	}

	/**
	 * @return the checkInMonth
	 */
	public String getCheckInMonth() {
		return checkInMonth;
	}

	/**
	 * @return the checkInDate
	 */
	public String getCheckInDate() {
		return checkInDate;
	}

	/**
	 * @return the checkOutYear
	 */
	public String getCheckOutYear() {
		return checkOutYear;
	}

	/**
	 * @return the checkOutMonth
	 */
	public String getCheckOutMonth() {
		return checkOutMonth;
	}

	/**
	 * @return the checkOutDate
	 */
	public String getCheckOutDate() {
		return checkOutDate;
	}

	/**
	 * @param childrenAge the childrenAge to set
	 */
	public void setChildrenAge(List<Integer> childrenAge) {
		this.childrenAge = childrenAge;

		if (totalChildren > 0 && !childrenAge.isEmpty() && totalChildren == childrenAge.size())
			this.childrenAge = childrenAge;
		else
			throw new IllegalStateException("Please provide age for " + totalChildren + " Childrens.");
	}

	/**
	 * @param totalAdults the totalAdults to set
	 */
	public void setTotalAdults(int totalAdults) {
		this.totalAdults = totalAdults;

	}

	/**
	 * @param checkInYear the checkInYear to set
	 */
	public void setCheckInYear(String checkInYear) {
		this.checkInYear = checkInYear;
	}

	/**
	 * @param checkInMonth the checkInMonth to set
	 */
	public void setCheckInMonth(String checkInMonth) {
		this.checkInMonth = checkInMonth;
	}

	/**
	 * @param checkInDate the checkInDate to set
	 */
	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	/**
	 * @param checkOutYear the checkOutYear to set
	 */
	public void setCheckOutYear(String checkOutYear) {
		this.checkOutYear = checkOutYear;
	}

	/**
	 * @param checkOutMonth the checkOutMonth to set
	 */
	public void setCheckOutMonth(String checkOutMonth) {
		this.checkOutMonth = checkOutMonth;
	}

	/**
	 * @param checkOutDate the checkOutDate to set
	 */
	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	@Override
	public String toString() {
		return "HomePageBO [childrenAge=" + childrenAge + ", totalAdults=" + totalAdults + ", checkInYear="
				+ checkInYear + ", checkInMonth=" + checkInMonth + ", checkInDate=" + checkInDate + ", checkOutYear="
				+ checkOutYear + ", checkOutMonth=" + checkOutMonth + ", checkOutDate=" + checkOutDate + "]";
	}

}
