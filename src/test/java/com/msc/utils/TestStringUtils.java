package com.msc.utils;


import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * <P>Test Case for {@link com.msc.utils.StringUtils class}
 * </P>
 * Date: Apr 8, 2004 - 11:32:01 AM
 *
 * @author <a href="mailto:mscaldas@gmail.com">Marcelo Caldas</a>
 */
public class TestStringUtils  {

    @Test
	public void testParseTitleCaseWords() {
		System.out.println("Test Parse Title case Words");
		String test1 = "";
		String[] test1Result = StringUtils.splitCapitalWords(test1);
		assertTrue(Arrays.equals(test1Result, new String[]{""}));

		String test2 = "fooBar";
		String[] test2Result = StringUtils.splitCapitalWords(test2);
		assertTrue(Arrays.equals(test2Result, new String[]{"foo", "Bar"}));

		String test3 = "FooBar";
		String[] test3Result = StringUtils.splitCapitalWords(test3);
		assertTrue(Arrays.equals(test3Result, new String[]{"Foo", "Bar"}));

		String test4 = "FOOBar";
		String[] test4Result = StringUtils.splitCapitalWords(test4);
		assertTrue(Arrays.equals(test4Result, new String[]{"FOOBar"}));

		String test5 = "foo";
		String[] test5Result = StringUtils.splitCapitalWords(test5);
		assertTrue(Arrays.equals(test5Result, new String[]{"foo"}));

		String test6 = "Foo";
		String[] test6Result = StringUtils.splitCapitalWords(test6);
		assertTrue(Arrays.equals(test6Result, new String[]{"Foo"}));

		String test7 = "fooBarBaz";
		String[] test7Result = StringUtils.splitCapitalWords(test7);
		assertTrue(Arrays.equals(test7Result, new String[]{"foo", "Bar", "Baz"}));

		String test8 = "foobarBaz";
		String[] test8Result = StringUtils.splitCapitalWords(test8);
		assertTrue(Arrays.equals(test8Result, new String[]{"foobar", "Baz"}));

		String test9 = "FooBARBaz";
		String[] test9Result = StringUtils.splitCapitalWords(test9);
		assertTrue(Arrays.equals(test9Result, new String[]{"Foo", "BARBaz"}));

		String test10 = "fooBarBAZ";
		String[] test10Result = StringUtils.splitCapitalWords(test10);
		assertTrue(Arrays.equals(test10Result, new String[]{"foo", "Bar", "BAZ"}));

		String test11 = "FOOBarBaz";
		String[] test11Result = StringUtils.splitCapitalWords(test11);
		assertTrue(Arrays.equals(test11Result, new String[]{"FOOBar", "Baz"}));

		String test12 = "FOO";
		String[] test12Result = StringUtils.splitCapitalWords(test12);
		assertTrue(Arrays.equals(test12Result, new String[]{"FOO"}));

	}
    @Test
	public void testEmptyString() {
		System.out.println("Test Empty String");
		String test1 = "";
		assertTrue(StringUtils.isEmpty(test1));
		String test2 = " ";
		assertTrue(StringUtils.isEmpty(test2));
		String test3 = "         ";
		assertTrue(StringUtils.isEmpty(test3));
		String test4 = "NotEmpty";
		assertFalse(StringUtils.isEmpty(test4));
		String test5 = "       Still Not Empty";
		assertFalse(StringUtils.isEmpty(test5));
		String test6 = "   Yet not empty   ";
		assertFalse(StringUtils.isEmpty(test6));
	}
    @Test
	public void testCapitalizeString() {
		System.out.println("Test Capitalize");
		String test1 = "normal text";
		assertTrue("Normal text".equals(StringUtils.capitalizeAndLower(test1)));
		String test2 = "Capital Letter First";
		assertTrue("Capital letter first".equals(StringUtils.capitalizeAndLower(test2)));

		try {
			StringUtils.capitalizeEachWord(null);
			StringUtils.capitalizeAndLower(null);
			StringUtils.capitalize(null);
			StringUtils.capitalizeEachWord("");
			StringUtils.capitalizeAndLower("");
			StringUtils.capitalize("");
			StringUtils.capitalize(" ");
			assertTrue("NO NPE Thrown", true);
		} catch (NullPointerException npe) {
			fail("NPE thrown: " + npe.getMessage());
			npe.printStackTrace();
		}
	}
    @Test
    public void testPad() {
		System.out.println("Test Pad");
		String test1 = "4";
		String test1Result = StringUtils.pad(test1, '0', 2, StringUtils.PAD_LEFT);
		assertTrue("004".equals(test1Result));
		test1Result = StringUtils.pad(test1, '-', 2, StringUtils.PAD_RIGHT);
		assertTrue("4--".equals(test1Result));
		test1Result = StringUtils.pad(test1, '0', 1, StringUtils.PAD_LEFT);
		assertTrue("04".equals(test1Result));

		String test2 = "44";
		assertTrue("0000044".equals(StringUtils.pad(test2, '0', 5, StringUtils.PAD_LEFT)));
		assertTrue("44-----".equals(StringUtils.pad(test2, '-', 5, StringUtils.PAD_RIGHT)));
		assertTrue("4400".equals(StringUtils.pad(test2, '0', 2, StringUtils.PAD_RIGHT)));

	}

    @Test
	public void testPadGrow() {
		System.out.println("Test Pad Grow");
		String test1 = "4";
		String test1Result = StringUtils.padGrow(test1, '0', 2, StringUtils.PAD_LEFT);
		assertTrue("04".equals(test1Result));
		test1Result = StringUtils.padGrow(test1, '-', 2, StringUtils.PAD_RIGHT);
		assertTrue("4-".equals(test1Result));
		test1Result = StringUtils.padGrow(test1, '0', 1, StringUtils.PAD_LEFT);
		assertTrue("4".equals(test1Result));

		String test2 = "44";
		assertTrue("00044".equals(StringUtils.padGrow(test2, '0', 5, StringUtils.PAD_LEFT)));
		assertTrue("44---".equals(StringUtils.padGrow(test2, '-', 5, StringUtils.PAD_RIGHT)));
		assertTrue("44".equals(StringUtils.padGrow(test2, '0', 2, StringUtils.PAD_RIGHT)));
	}

    @Test
	public void testTruncation() {
		String descrip = "This is a description of 43 characters long";
		String trunc10 = StringUtils.truncateStr(descrip, 10);
		System.out.println("trunc10: " + trunc10);
		assertTrue(trunc10.length() == 10);
		String trunc50 = StringUtils.truncateStr(descrip, 50);
		System.out.println("trunc50: " + trunc50);
		assertTrue(trunc50.length() == descrip.length());
		String trunc43 = StringUtils.truncateStr(descrip, 43);
		System.out.println("trunc43: " + trunc43);
		assertTrue(trunc43.length() == descrip.length());
		String trunc42 = StringUtils.truncateStr(descrip, 42);
		System.out.println("trunc42: " + trunc42);
		assertTrue(trunc42.length() <= descrip.length() - 1);

		String trunc2 = StringUtils.truncateStr(descrip, 2);
		System.out.println("trunc2: " + trunc2);
		assertTrue(trunc2 == null);

	}
    @Test
	public void testConcatenation() {
		String[] test =  new String[3];
		System.out.println("Going back and forth...");
		test[0] = "A";
		test[1] = "B";
		test[2] = "C";
		String s = StringUtils.concatWithDelimiter(test, "^", false);
		System.out.println("result = " + s);
		String[] result = StringUtils.toArrayWithDelimiters(s, "^");
        for (String aResult : result) {
            System.out.println("i: " + aResult);

        }
		System.out.println("Using full words and NULL - Don't skip nulls");
		test[0] = "WordOne";
		test[2] = "wordTwo";
		test[1] = null;
		String s1 = StringUtils.concatWithDelimiter(test, "^", false);
		System.out.println("result = " + s1);
		result = StringUtils.toArrayWithDelimiters(s1, "^");
        for (String aResult : result) {
            System.out.println("i: " + aResult);

        }
		System.out.println("Skip nulls.");
		s = StringUtils.concatWithDelimiter(test, "^", true);
		System.out.println("result = " + s);
		result = StringUtils.toArrayWithDelimiters(s, "^");
        for (String aResult : result) {
            System.out.println("i: " + aResult);

        }


	}

}
