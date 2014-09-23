package com.star.cloud.domain.qos;

public class DateTimeTemplate {
	
	private String year = "*";
	
	private String month = "*";
	
	private String day = "*";
	
	private String hour = "*";
	
	private String minute = "*";
	
	private String second = "*";
	
	public boolean isMatched(DateTimeTemplate dt) {
		return isYearMatched(dt) && isMonthMatched(dt) && isDayMatched(dt)
				&& isHourMatched(dt) && isMinuteMatched(dt) && isSecondMatched(dt);
	}
	
	public boolean isYearMatched(DateTimeTemplate dt) {
		return this.year.equals("*") || this.year.equals(dt.getYear());
	}
	
	public boolean isMonthMatched(DateTimeTemplate dt) {
		return this.month.equals("*") || this.month.equals(dt.getMonth());
	}
	
	public boolean isDayMatched(DateTimeTemplate dt) {
		return this.day.equals("*") || this.day.equals(dt.getDay());
	}
	
	public boolean isHourMatched(DateTimeTemplate dt) {
		return this.hour.equals("*") || this.hour.equals(dt.getHour());
	}
	
	public boolean isMinuteMatched(DateTimeTemplate dt) {
		return this.minute.equals("*") || this.minute.equals(dt.getMinute());
	}
	
	public boolean isSecondMatched(DateTimeTemplate dt) {
		return this.second.equals("*") || this.second.equals(dt.getSecond());
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {
		this.second = second;
	}
	
}
