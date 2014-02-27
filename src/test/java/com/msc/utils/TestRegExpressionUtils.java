package com.msc.utils;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * <P>
 * </P>
 * <p/>
 * <P>Date: Jan 13, 2005 - 11:56:17 AM</P>
 *
 * @author <a href="mailto:mscaldas@gmail.com">Marcelo Caldas</a>
 */
public class TestRegExpressionUtils  {

     @Test
	public void testRegExpressions() {
		//E-mails:
        assertFalse(!RegExpressionUtils.validateEmail("'1abcd@123.com"));

		assertTrue(RegExpressionUtils.validateEmail("1_abcd@123.com"));
		assertTrue(RegExpressionUtils.validateEmail("mscaldas@gmail.com"));
		assertTrue(!RegExpressionUtils.validateEmail("mscaldas@gmail"));
		assertTrue(!RegExpressionUtils.validateEmail("mscaldasgmail.com"));
		assertTrue(!RegExpressionUtils.validateEmail("mscalds@"));
		assertTrue(!RegExpressionUtils.validateEmail("@gmai.com"));
		assertTrue(RegExpressionUtils.validateEmail("mscaldas@gmail.com.18"));

		assertTrue(RegExpressionUtils.validateEmail("DO'Keefe@gmail.co.uk"));
		//us zip codes:
		assertTrue(RegExpressionUtils.validateUSZipCode("30144"));
		assertTrue(RegExpressionUtils.validateUSZipCode("30144-1234"));
		assertTrue(!RegExpressionUtils.validateUSZipCode("1"));
		assertTrue(!RegExpressionUtils.validateUSZipCode("12345-4"));
		assertTrue(!RegExpressionUtils.validateUSZipCode("123451234"));
		//telephones:
		assertTrue(RegExpressionUtils.validateUSTelephone("770-123-1234"));
		assertTrue(!RegExpressionUtils.validateUSTelephone("123-1234"));
		assertTrue(!RegExpressionUtils.validateUSTelephone("7701231234"));
	}

    @Test
	public void testVARegExpression() {
		//3 or 4 digits, followed by 2 chars or 1 char+1 number
		String[] valids = {"333AA", "123B2", "4444BZ", "5555A3"};
		String[] invalids = {"a", "1", "333aa", "333422", "33322", "AA3334", "3333BBB"};
		String vaCode = "^\\d{3,4}([A-Z]{2}|[A-Z][0-9])$";
		//String vaCode = "^\\d{3}(([0-9]|[A-Z])([A-Z]){2})$";

        for (String valid : valids) {
            assertTrue(RegExpressionUtils.isMatch(valid, vaCode));
            System.out.println("Success for " + valid);
        }
        for (String invalid : invalids) {
            assertFalse(RegExpressionUtils.isMatch(invalid, vaCode));
            System.out.println("Success for " + invalid);
        }

	}
}
