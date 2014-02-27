package com.msc.utils.debug;

/**
 * Created by IntelliJ IDEA.
 * User: mcaldas
 * Date: Dec 13, 2003
 * Time: 8:53:35 AM
 * To change this template use Options | File Templates.
 */
public class StopWatch {
    private long startTime;
    private long endTime ;

    //maintain state:
    private static final int DEFAULT_LAPS = 100;
    private long[] lapTime;
    private long finalTime;

    //watch state:
    public static final String NIL = "nil";
    public static final String RUNNING = "running";
    public static final String STOPPED = "stopped";

	public static final long SECONDS = 1000;
	public static final long MINUTES = SECONDS*60;
	public static final long HOURS   = MINUTES*60;
	public static final long DAYS    = HOURS*24;


    private String state;
    private int currentLap = 0;

    public StopWatch() {
        this(DEFAULT_LAPS);
    }
    public StopWatch(int nbrOfLaps) {
        this.lapTime = new long[nbrOfLaps];
        this.state = NIL;
    }

    public String read() {
        if (NIL.equals(this.getState())) {
            throw new IllegalStateException("Watch has not been started");
        }
        long time = this.getEndTime();
        if (RUNNING.equals(this.getState())) { //If watch hasnt stopped, we do a quick read.
            time = System.currentTimeMillis();
        }
        time = time - this.getStartTime();
        if (time <= 0 ) {
            return null;
        }
		String result = this.formatTime(time);
        if (STOPPED.equals(this.getState())) {
            this.finalTime = time;
        } else if (RUNNING.equals(this.getState())) {
            this.lapTime[this.getCurrentLap()] = time;
        }
        return result;
    }

	public long readMillis() {
		if (NIL.equals(this.getState())) {
			throw new IllegalStateException("Watch has not been started");
		}
		long time = this.getEndTime();
		if (RUNNING.equals(this.getState())) { //If watch hasnt stopped, we do a quick read.
			time = System.currentTimeMillis();
		}
		time = time - this.getStartTime();
		if (time <= 0 ) {
			return 0;
		}
		long result = time;
		if (STOPPED.equals(this.getState())) {
			this.finalTime = time;
		} else if (RUNNING.equals(this.getState())) {
			this.lapTime[this.getCurrentLap()] = time;
		}
		return result;
	}
    public void start() {
        this.reset();
        this.state = RUNNING;
    }

    public void stop() {
		if (RUNNING.equals(this.getState())) { //If watch is not running, we don't have to stop it.
			this.endTime = System.currentTimeMillis();
			this.read();
		}
        this.state = STOPPED;
    }

    protected long getStartTime() {
        return startTime;
    }

    protected long getEndTime() {
        return endTime;
    }

    protected long getFinalTime() {
        return finalTime;
    }

    protected String getState() {
        return state;
    }
    /**
     * Everytime a currentLap is read, we increment the lap
     * @return the current lap
     */
    protected int getCurrentLap() {
        return currentLap++;
    }
	public String getLapTime(int lap) {
		if (lap == 1) {
			return this.formatTime(this.lapTime[lap -1]);
		} else {
			return this.formatTime(Math.max(this.lapTime[lap - 1] - this.lapTime[lap - 2], 0L));
		}
	}

	public String getAverageLapTime() {
		return this.formatTime(this.lapTime[this.getCurrentLap() - 1] / this.getCurrentLap());
	}
	public long getLapTimeMillis(int lap) {
		if (lap == 1) {
			return this.lapTime[lap -1];
		} else {
			return Math.max(this.lapTime[lap - 1] - this.lapTime[lap - 2], 0L);
		}
	}

	public String getCummulativeTime(int lap) {
		return this.formatTime(this.lapTime[lap - 1]);
	}
	private String formatTime(long time) {
		StringBuffer result = new StringBuffer();

		long days = time / DAYS;
		if (days > 0) {
			result.append(days).append(" days ");
			time -= days*DAYS;
		}
		long hours = time / HOURS;
		if (hours > 0) {
			result.append(hours).append(" hours ");
			time -= hours*HOURS;
		}
		long minutes = time / MINUTES;
		if (minutes > 0) {
			result.append(minutes).append(" min ");
			time -= minutes*MINUTES;
		}
		long seconds = time / SECONDS;
		if (seconds > 0) {
			result.append(seconds).append(" s ");
			time -= seconds*SECONDS;
		}
		if (time > 0 || result.length() == 0) result.append(time).append(" millis ");
		return result.toString();

	}

	public void reset() {
		this.startTime = System.currentTimeMillis();
		this.finalTime = 0;
		for (int i = 0; i < this.getCurrentLap() - 1; i++) {
			this.lapTime[i] = 0;
		}
		this.currentLap = 0;
	}

	public String toString() {
		return this.read();
	}
}
