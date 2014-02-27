package com.msc.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * <P>Utility class for collection manipulation.
 * </P>
 *
 * <P>Date: Apr 13, 2004 - 10:28:15 AM</P>
 *
 * @author <a href="mailto:mscaldas@gmail.com">Marcelo Caldas</a>
 */
public class CollectionUtils {

	/**
	 * <P>This method converts an array into an ArrayList. (The reverse of a Collection.toArray() method).</P>
	 *
	 * @param array The array of objects we want within an ArrayList.
	 * @return the collection of objects originally contained on the array parameter.
	 */
	public static Collection<Object> toArrayList(Object[] array) {
		if (array != null && array.length > 0) {
			ArrayList<Object> result = new ArrayList<Object>(array.length);
            Collections.addAll(result, array);
			return result;
		}
		return null;
	}
	/**
	 * Method to verify if a collection is empty (including null).
	 *
	 * @param col a {@link java.util.Collection} object.
	 * @return a boolean.
	 */
	public static boolean isEmpty(Collection col) {
		return (col == null || col.isEmpty());
	}
	/**
	 * Method to verify if a collection is Not empty (including null)
	 *
	 * @param col a {@link java.util.Collection} object.
	 * @return a boolean.
	 */
	public static boolean isNotEmpty(Collection col) {
		return !CollectionUtils.isEmpty(col);
	}
}
