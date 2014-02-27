package com.msc.utils;


import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * <P>
 * </P> 
 * Date: Apr 9, 2004 - 11:08:27 AM
 *
 * @author <a href="mailto:mscaldas@gmail.com">Marcelo Caldas</a>
 */
public class TestDateUtils  {

    @Test
	public void testISODates() {
		this.testDate("1997-07-16T19:20:30.45-02:00");
		this.testDate("1997-07-16T19:20:30+01:00");
		this.testDate("1997-07-16T19:20:30+01:00");
		this.testDate("1997-07-16T19:20");
		this.testDate("1997-07-16");
		this.testDate("1997-07");
		this.testDate("1997");
		this.testDate(new Date());
		DateUtils.getCurrentIsoDate();
	}
	protected void testDate(Date date) {
		String isodate = null;
		System.out.println("----------------------------------");
		try {
			System.out.println(">> " + date.toString() + " [" + date.getTime() + "]");
			isodate = DateUtils.getIsoDate(date);
			System.out.println(">> " + isodate);
			date = DateUtils.parseIsoDate(isodate);
			System.out.println(">> " + date.toString() + " [" + date.getTime() + "]");
		} catch (InvalidDateException ex) {
			System.err.println(isodate + " is invalid");
			System.err.println(ex.getMessage());
		}
		System.out.println("----------------------------------");

		Calendar test = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		test.setTime(date);
		DateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss.S'Z'");
		String dateFormated = isoFormat.format(test.getTime());
		System.out.println(">> W/ DateFormat: " + dateFormated);


	}

	protected void testDate(String isodate) {
		System.out.println("----------------------------------");
		try {
			Date date = DateUtils.parseIsoDate(isodate);
			System.out.println(">>" + isodate);
			System.out.println(">>" + date.toString() + " [" + date.getTime() + "]");
			System.out.println(">>" + DateUtils.getIsoDate(date));
		} catch (InvalidDateException ex) {
			System.err.println(isodate + " is invalid");
			System.err.println(ex.getMessage());
		}
		System.out.println("----------------------------------");
	}
}
