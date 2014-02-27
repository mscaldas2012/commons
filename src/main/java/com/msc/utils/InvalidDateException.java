package com.msc.utils;

/**
 * <P>
 * </P> 
 * Date: Apr 9, 2004 - 11:11:11 AM
 *
 * @author <a href="mailto:mscaldas@gmail.com">Marcelo Caldas</a>
 */
public class InvalidDateException extends Exception {
	public InvalidDateException(String message) {
		super(message);
	}
	public InvalidDateException(String message, Throwable cause) {
		super(message, cause);
	}
}
