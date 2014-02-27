// DateUtils.java
// $Id: DateUtils.java,v 1.3 2001/01/04 13:26:19 bmahe Exp $
// (c) COPYRIGHT MIT, INRIA and Keio, 2000.
// Please first read the full copyright statement in file COPYRIGHT.html
package com.msc.utils;

import java.util.*;


/**
 * <P>Utility class for date manipulation.
 * </P>
 *
 * <P>Date: Apr 13, 2004 - 10:28:15 AM</P>
 *
 * @author <a href="mailto:mscaldas@gmail.com">Marcelo Caldas</a>
 *
 */
public class DateUtils {
	/**
	 * Parse the given string in ISO 8601 format and build a Date object.
	 * @param isodate the date in ISO 8601 format
	 * @return a Date instance
	 * @exception InvalidDateException if the date is not valid
	 */
	public static Date parseIsoDate(String isodate) throws InvalidDateException {
		Calendar calendar = parseIsoDateToCalendar(isodate);
		return calendar.getTime();
	}
	/**
	 * Generate a ISO 8601 date
	 * @param date a Date instance
	 * @return a string representing the date in the ISO 8601 format
	 */
	public static String getIsoDate(Date date) {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		calendar.setTime(date);
		StringBuffer buffer = new StringBuffer();
		buffer.append(calendar.get(Calendar.YEAR));
		buffer.append("-");
		buffer.append(StringUtils.padGrow(calendar.get(Calendar.MONTH) + 1 + "", '0', 2, StringUtils.PAD_LEFT));
		buffer.append("-");
		buffer.append(StringUtils.padGrow(calendar.get(Calendar.DAY_OF_MONTH) + "", '0', 2, StringUtils.PAD_LEFT));
		buffer.append("T");
		buffer.append(StringUtils.padGrow(calendar.get(Calendar.HOUR_OF_DAY) + "", '0', 2, StringUtils.PAD_LEFT));
		buffer.append(":");
		buffer.append(StringUtils.padGrow(calendar.get(Calendar.MINUTE) + "", '0', 2, StringUtils.PAD_LEFT));
		buffer.append(":");
		buffer.append(StringUtils.padGrow(calendar.get(Calendar.SECOND) + "", '0', 2, StringUtils.PAD_LEFT));
		buffer.append(".");
		buffer.append(StringUtils.padGrow((calendar.get(Calendar.MILLISECOND) / 10) + "", '0', 2, StringUtils.PAD_LEFT));
		buffer.append("Z");

		return buffer.toString();
	}
	public static String getCurrentIsoDate() {
		return DateUtils.getIsoDate(new Date());
	}
	private static boolean check(StringTokenizer st, String token) throws InvalidDateException {
		try {
			if (st.nextToken().equals(token)) {
				return true;
			} else {
				throw new InvalidDateException("Missing [" + token + "]");
			}
		} catch (NoSuchElementException ex) {
			return false;
		}
	}

	public static Calendar parseIsoDateToCalendar(String isodate) throws InvalidDateException {
		// YYYY-MM-DDThh:mm:ss.sTZD
		StringTokenizer st = new StringTokenizer(isodate, "-T:.+Z", true);

		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		calendar.clear();
		try {
			// Year
			if (st.hasMoreTokens()) {
				int year = Integer.parseInt(st.nextToken());
				calendar.set(Calendar.YEAR, year);
			} else {
				return calendar;
			}
			// Month
			if (check(st, "-") && (st.hasMoreTokens())) {
				int month = Integer.parseInt(st.nextToken()) - 1;
				calendar.set(Calendar.MONTH, month);
			} else {
				return calendar;
			}
			// Day
			if (check(st, "-") && (st.hasMoreTokens())) {
				int day = Integer.parseInt(st.nextToken());
				calendar.set(Calendar.DAY_OF_MONTH, day);
			} else {
				return calendar;
			}
			// Hour
			if (check(st, "T") && (st.hasMoreTokens())) {
				int hour = Integer.parseInt(st.nextToken());
				calendar.set(Calendar.HOUR_OF_DAY, hour);
			} else {
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MILLISECOND, 0);
				return calendar;
			}
			// Minutes
			if (check(st, ":") && (st.hasMoreTokens())) {
				int minutes = Integer.parseInt(st.nextToken());
				calendar.set(Calendar.MINUTE, minutes);
			} else {
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MILLISECOND, 0);
				return calendar;
			}

			//
			// Not mandatory now
			//

			// Secondes
			if (!st.hasMoreTokens()) {
				return calendar;
			}
			String tok = st.nextToken();
			if (tok.equals(":")) { // secondes
				if (st.hasMoreTokens()) {
					int secondes = Integer.parseInt(st.nextToken());
					calendar.set(Calendar.SECOND, secondes);
					if (!st.hasMoreTokens()) {
						return calendar;
					}
					// frac sec
					tok = st.nextToken();
					if (tok.equals(".")) {
						// bug fixed, thx to Martin Bottcher
						String nt = st.nextToken();
						while (nt.length() < 3) {
							nt += "0";
						}
						nt = nt.substring(0, 3); //Cut trailing chars..
						int millisec = Integer.parseInt(nt);
						//int millisec = Integer.parseInt(st.nextToken()) * 10;
						calendar.set(Calendar.MILLISECOND, millisec);
						if (!st.hasMoreTokens()) {
							return calendar;
						}
						tok = st.nextToken();
					} else {
						calendar.set(Calendar.MILLISECOND, 0);
					}
				} else {
					throw new InvalidDateException("No secondes specified");
				}
			} else {
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MILLISECOND, 0);
			}
			// Timezone
			if (!tok.equals("Z")) { // UTC
				if (!(tok.equals("+") || tok.equals("-"))) {
					throw new InvalidDateException("only Z, + or - allowed");
				}
				boolean plus = tok.equals("+");
				if (!st.hasMoreTokens()) {
					throw new InvalidDateException("Missing hour field");
				}
				int tzhour = Integer.parseInt(st.nextToken());
				int tzmin;
				if (check(st, ":") && (st.hasMoreTokens())) {
					tzmin = Integer.parseInt(st.nextToken());
				} else {
					throw new InvalidDateException("Missing minute field");
				}
				if (plus) {
					calendar.add(Calendar.HOUR, tzhour);
					calendar.add(Calendar.MINUTE, tzmin);
				} else {
					calendar.add(Calendar.HOUR, -tzhour);
					calendar.add(Calendar.MINUTE, -tzmin);
				}
			}
		} catch (NumberFormatException ex) {
			throw new InvalidDateException("[" + ex.getMessage() + "] is not an integer", ex);
		}
		return calendar;
	}
}
