package com.msc.utils;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * <P>Utility class for Class manipulation.
 * </P>
 *
 * <P>Date: Apr 13, 2004 - 9:20:53 AM</P>
 *
 * @author <a href="mailto:mscaldas@gmail.com">Marcelo Caldas</a>
 *
 */
public class ClassUtils {
	/** Constant <code>PROPERTY_FILE_NAME="gov.properties.fileName"</code> */
	public static final String PROPERTY_FILE_NAME  = "gov.properties.fileName";
	private static final String HARDCODED_FILE_NAME = "app.properties";
	/**
	 * Helper method to extract the package name based on a Class
	 *
	 * @param clazz The class within the package you want.
	 * @return The string representation of the package for the given class.
	 */
	public static String getPackageName(Class clazz) {
		return ClassUtils.getPackageName(clazz.getName());
	}
	/**
	 * Helper method to extract the package name based on a Class
	 *
	 * @param clazz The class within the package you want.
	 * @return The string representation of the package for the given class.
	 */
	public static String getPackageName(String clazz) {
        if (clazz.indexOf(".") > 0) {
		    return clazz.substring(0, clazz.lastIndexOf("."));
        }
		return null;
	}
	/**
	 * Helper method to get the Class name without the package declaration:
	 *
	 * @param clazz a {@link java.lang.Class} object.
	 * @return the name of a class without the package declaration.
	 */
	public static String getClassNameNoPackage(Class clazz) {
		return ClassUtils.getClassNameNoPackage(clazz.getName());
	}
	/**
	 * Helper method to get the Class name without the package declaration:
	 *
	 * @param clazz a {@link java.lang.String} object.
	 * @return the name of a class without the package declaration.
	 */
	public static String getClassNameNoPackage(String clazz) {
		String className = clazz.substring(clazz.lastIndexOf(".") + 1, clazz.length());
		return className;
	}

	/**
	 * This method tries to get the value of a property from any possible place. The method tries to be full proof to
	 * find a property no matter what.
	 * The order, though is hardcoded as follow:
	 * <UL>
	 * <LI>First, we check a system property (java -Dxxx).</LI>
	 * <LI>Then, we check for a file name. The name of the file is recursively tried to be found using this same
	 * mechanism with the specific property named: "gov.properties.fileName". If this property does not exist,
	 * then we try to find a file named application.properties as a resource. If not found, we give up trying to
	 * find the file.</LI>
	 * <LI>
	 * </UL>
	 *
	 * @param property a {@link java.lang.String} object.
	 * @return a {@link java.lang.String} object.
	 */
	public static String getProperty(String property) {
		String value = System.getProperty(property);
		if (StringUtils.isEmpty(value)) { //Didn't found property as -D
			String fileName = property.equals(PROPERTY_FILE_NAME)?HARDCODED_FILE_NAME:null; //Stop recursivity.
			if (StringUtils.isEmpty(fileName)) {
				fileName = ClassUtils.getProperty(PROPERTY_FILE_NAME);
				if (StringUtils.isEmpty(fileName)) {
					fileName = HARDCODED_FILE_NAME;
				}
			}
			Properties props = new Properties();
			try {
				InputStream is = ClassUtils.class.getClassLoader().getResourceAsStream(fileName);
				//If the file is not there, we reset to default file
				if (is == null && !HARDCODED_FILE_NAME.equals(fileName)) {
					is = ClassUtils.class.getClassLoader().getResourceAsStream(HARDCODED_FILE_NAME);
				}
				if (is != null) {
					props.load(is);
					value = props.getProperty(property);
					//If not found we stil try to find on the default file
					if (StringUtils.isEmpty(value) && !HARDCODED_FILE_NAME.equals(fileName)) {
						is = ClassUtils.class.getClassLoader().getResourceAsStream(HARDCODED_FILE_NAME);
						props.load(is);
						value = props.getProperty(property);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
			}
		}
		return value;
	}

}
