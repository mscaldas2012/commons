package com.msc.utils;

/**
 * <P>This class has helper methods for boolean manipulation.
 * </P>
 * <p/>
 * <P>Date: Jan 5, 2005 - 1:13:44 PM</P>
 *
 * @author <a href="mailto:mscaldas@gmail.com">Marcelo Caldas</a>
 */
public class BooleanUtils {
	//If Booleans should be identified by char values on the DB, developers can use these constants
	//instead of trying to remember what is what.
	/** Constant <code>INFO_NOT_AVAILABLE="2"</code> */
	public static final String INFO_NOT_AVAILABLE = "2";
	/** Constant <code>TRUE="1"</code> */
	public static final String TRUE = "1";
	/** Constant <code>FALSE="0"</code> */
	public static final String FALSE = "0";
	/** Constant <code>VALID_INDICATORS="new String[]{FALSE, TRUE, INFO_NOT_AVAI"{trunked}</code> */
	public static final String[] VALID_INDICATORS = new String[]{FALSE, TRUE, INFO_NOT_AVAILABLE};


	/** Constant <code>VALID_TRUE_VALUES="new String[] {yes, true, on, 1, y, t}"</code> */
	public static final String[] VALID_TRUE_VALUES = new String[] {"yes", "true", "on", "1", "y", "t"};
	/** Constant <code>VALID_FALSE_VALUES="new String[]{no, false, off, 0, n, f}"</code> */
	public static final String[] VALID_FALSE_VALUES = new String[]{"no", "false", "off", "0", "n", "f"};
	private static final String TRUE_STR = "true";
	private static final String FALSE_STR = "false";

	/**
	 * Performs a XOR boolean operation between the two boolean objects passed as parameter.
	 *
	 * @param a the first boolean
	 * @param b the second boolean
	 * @return True if the XOR operation returns true (t-f;f-t) or false otherwise (t-t;f-f)
	 */
	public static boolean xor(boolean a, boolean b) {
		return a ^ b;
	}

	/**
	 * Performs a XNOR boolean operation between the two boolean objects passed as parameter.
	 *
	 * @param a the first boolean
	 * @param b the second boolean
	 * @return True if the XOR operation returns true (t-t;f-f) or false otherwise (t-f;f-t)
	 */
	public static boolean xnor(boolean a, boolean b) {
		return !xor(a, b);
	}
	/**
	 * This method verifies if the String passed as parameter is a boolean according to the definitions
	 * on this class for TRUE, FALSE or INFO_NOT_AVAILABLE
	 *
	 * @param value The String we want to check if is a valid boolean representation.
	 * @return True if the parameter value is a valid boolean representation; false otherwise.
	 */
	public static boolean isCharABooleanValue(String value) {
		boolean found = false;
		for (int i = 0; i < VALID_INDICATORS.length && !found; i++) {
			found = VALID_INDICATORS[i].equals(value);
		}
		return found;
	}
	/**
	 * this method returns true if the string parameter is any of the following values:
	 * "yes", "true", "on", "1". It will return false if the string is null or not one of the previous values.
	 *
	 * @param value The string we're trying to identify as a true boolean value.
	 * @return true if the value represents one of the above values. False if not or null.
	 */
	public static boolean isValueTrue(String value) {
        if (StringUtils.isEmpty(value))
            return false;
		boolean found = false;
		for (int i = 0; i < VALID_TRUE_VALUES.length && !found; i++) {
			found = VALID_TRUE_VALUES[i].equalsIgnoreCase(value.trim());
		}
		return found;
	}

	/**
	 * this method returns true if the string parameter is any of the following values:
	 * "no", "false", "off", "0". It will return false if the string is null or not one of the previous values.
	 *
	 * @param value The string we're trying to identify as a true boolean value.
	 * @return true if the value represents one of the above values. False if not or null.
	 */
	public static boolean isValueFalse(String value) {
        if (StringUtils.isEmpty(value))
            return false;
		boolean found = false ;
		for (int i = 0; i < VALID_FALSE_VALUES.length && !found; i++) {
			found = VALID_FALSE_VALUES[i].equalsIgnoreCase(value.trim());
		}
		return found;
	}

	/**
	 * <p>getStringValue.</p>
	 *
	 * @param value a boolean.
	 * @return a {@link java.lang.String} object.
	 */
	public static String getStringValue(boolean value) {
		return value?TRUE_STR:FALSE_STR;
	}


}
