package com.msc.utils;


import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * <P>
 * </P>
 * 
 * <P>Date: Apr 13, 2004 - 10:07:16 AM</P>
 *
 * @author <a href="mailto:mscaldas@gmail.com">Marcelo Caldas</a>
 */
public class TestClassUtils {

    @Test
	public void testGetClassName() {
		String className = ClassUtils.getClassNameNoPackage(MockClass.class);
		System.out.println("className = " + className);
		assertTrue(className.equals("MockClass"));
		className = ClassUtils.getPackageName(MockClass.class);
		System.out.println("package = " + className);
		assertTrue(className.equals("com.msc.utils"));
	}


 	public void testGetProperty() {
		//this test is extremely dependant on how the app is running...
		//we need to set properties on the -D and properties on files to make sure it works
		//One file named app.properties and another file custom.properties with -Dgov.properties.fileName=custom.properties
		String value1 = ClassUtils.getProperty("gov.test.myProperty");
		System.out.println("value1 = " + value1);
		assertTrue("abc123XYZ".equals(value1));

		String value2 = ClassUtils.getProperty("gov.test.propertyInAFile");
		System.out.println("value2 = " + value2);
		assertTrue("I am here".equals(value2));

		String value3 = ClassUtils.getProperty("gov.customFile.properties");
		System.out.println("value3 = " + value3);
		assertTrue("This is another test".equals(value3));

		String value4 = ClassUtils.getProperty("gov.test.sameProperty");
		System.out.println("value4 = " + value4);
		assertTrue("This property is in custom.properties".equals(value4));

		String value5 = ClassUtils.getProperty("system.is.prefered");
		System.out.println("value5 = " + value5);
		assertTrue("System Property".equals(value5));
	}
}
/**
 * Mock Object to test the ClassUtils
 */
class MockClass {

}

