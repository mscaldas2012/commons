package com.msc.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <P>This class is a utility for manipulating regular expressions. Its most important value is to hold the
 * constants for well known regular expressions validation, like e-mail, URL, telephones, zip codes, etc.
 * <p/>
 * </P>
 * <p/>
 * <P>Date: Jan 13, 2005 - 11:32:07 AM</P>
 * <p/>
 * For Regular Expression help, go <a href="http://www.regexlib.com">Here</a>
 *
 * @author <a href="mailto:mscaldas@gmail.com">Marcelo Caldas</a>
 */
public class RegExpressionUtils {
	/**
	 * The most complete email validation routine I could come up with. It verifies that:
	 * - Only letters, numbers and email acceptable symbols (+, _, -, .) are allowed
	 * - No two different symbols may follow each other
	 * - Cannot begin with a symbol
	 * - Ending domain ...
	 * - MSC: Added the capability to have ( ' ) as part of the e-mail before the @ sign.
	 */
	public static final String EMAL_REG_EXPR = "^(([A-Za-z0-9]+_+)|([A-Za-z0-9]+\\-+)|([A-Za-z0-9]+\\.+)|([A-Za-z0-9]+\\++))*[A-Za-z0-9']+@((\\w+\\-+)|(\\w+\\.))*\\w{1,63}\\.[a-zA-Z0-9]{1,6}$";
//	public static final String EMAL_REG_EXPR = "^(([A-Za-z0-9]+_+)|([A-Za-z0-9]+\\-+)|([A-Za-z0-9]+\\.+)|([A-Za-z0-9]+\\++))*[A-Za-z0-9]+@((\\w+\\-+)|(\\w+\\.))*\\w{1,63}\\.[a-zA-Z]{2,6}$";
	/**
	 * Zip code Regular Expression.
	 * Valid matches:
	 * <UL>
	 * 	<LI>12345</LI>
	 * 	<LI>12345-1234</LI>
	 * </UL>
	 * Invalid Matches:
	 * <UL>
	 *  	<LI>1234</LI>
	 * 		<LI>123451234</LI>
	 * 		<LI>12345-6</LI>
	 * </UL>
	 */
	public static final String US_ZIP_CODE_REG_EXPR = "^\\d{5}(-\\d{4})?$";
	/**
	 * Basic Telephone Number Expression for US: with area code required and only dashes accepted.
	 */
	public static final String US_TELEPHONE_REG_EXPR = "^[0-9]\\d{2}-\\d{3}-\\d{4}$";
    /**
	 * Cheap and cheerful URL checker. Requires a http/https/ftp at the start and will then allow anything starting with at least a &lt;something&gt;.&lt;something&gt;.&lt;something&gt then valid characters separated by dots and slashes
	 * Valid matches:
	 * <UL>
	 * 	<LI>http://www.thedaddy.org</LI>
	 * 	<LI>http://forum.thedaddy.org/index.html</LI>
	 * 	<LI>ftp://hows.it.going_buddy/checkit/o</LI>
	 * </UL>
	 * Invalid Matches:
	 * <UL>
	 *  	<LI>www.thedaddy.org</LI>
	 * 		<LI>http://hello</LI>
	 * 		<LI>ftp://check.it</LI>
	 * </UL>
	 */
	public static final String URL_REG_EXPR = "^(ht|f)tp(s?)\\:\\/\\/[a-zA-Z0-9\\-\\._]+(\\.[a-zA-Z0-9\\-\\._]+){2,}(\\/?)([a-zA-Z0-9\\-\\.\\?\\,\\'\\/\\\\\\+&%\\$#_]*)?$";
	/**
	 * Helper method that compiles the expression pattern, gets the matcher and verifies if a specific string does
	 * match the expression or not.
	 *
	 * @param str The String we want to verify/valdiate
	 * @param expr The regular expression we're validating against.
	 * @return true if <code>str</code> does matches <code>expr</code>. false otherwise
	 */
	public static boolean isMatch(String str, String expr) {
		Pattern p = Pattern.compile(expr);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	public static boolean validateEmail(String eMail) {
		return RegExpressionUtils.isMatch(eMail, EMAL_REG_EXPR);
	}
	public static boolean validateUSTelephone(String phone) {
		return RegExpressionUtils.isMatch(phone, US_TELEPHONE_REG_EXPR);
	}
	public static boolean validateUSZipCode(String zipCode) {
		return RegExpressionUtils.isMatch(zipCode, US_ZIP_CODE_REG_EXPR);
	}
}
