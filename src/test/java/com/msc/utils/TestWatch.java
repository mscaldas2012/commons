package com.msc.utils;


import com.msc.utils.debug.StopWatch;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * <P>
 * </P>
 * <p/>
 * <P>Date: Mar 8, 2005 - 1:29:36 PM</P>
 *
 * @author <a href="mailto:mscaldas@gmail.com">Marcelo Caldas</a>
 */
public class TestWatch {

    @Test
	public void testStopNotRunning() {
		StopWatch watch = new StopWatch();
		watch.stop();
		String stoppedTime = watch.read();
		System.out.println(stoppedTime);
		assertTrue(StringUtils.isEmpty(stoppedTime));
	}
}
