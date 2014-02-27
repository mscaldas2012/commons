package com.msc.logging;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * This is a simple minimalistic logging formatting for development and debug purposes.
 * 
 * This code was written by Marcelo Caldas.
 * e-Mail: mscaldas@gmail.com
 * <p/>
 * Date: 5/1/13
 * <p/>
 * 
 * 
 */
public class TinyFormatter extends Formatter {
    private static final Logger logger = Logger.getLogger(TinyFormatter.class.getName());


    @Override
    public String format(LogRecord logRecord) {
        return logRecord.getLevel() + ":\t" + logRecord.getMessage() + "\n";
    }
}

