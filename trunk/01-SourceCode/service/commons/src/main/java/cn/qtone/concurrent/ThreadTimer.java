package cn.qtone.concurrent;

import java.text.MessageFormat;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadTimer implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ThreadTimer.class);

	private int timeoutSec;
	private String taskName;
	private Future<?> future;
	private Callback callback;
	private ErrorHandler errorHandler;

	public ThreadTimer(String taskName, Future<?> future) {
		this.taskName = taskName;
		this.future = future;
	}

	public ThreadTimer(String taskName, Future<?> future, Callback callback) {
		this.taskName = taskName;
		this.future = future;
		this.callback = callback;
	}

	public ThreadTimer(String taskName, Future<?> future, ErrorHandler errorHandler) {
		this.taskName = taskName;
		this.future = future;
		this.errorHandler = errorHandler;
	}

	public ThreadTimer(String taskName, Future<?> future, Callback callback, ErrorHandler errorHandler) {
		this.taskName = taskName;
		this.future = future;
		this.callback = callback;
		this.errorHandler = errorHandler;
	}

	public ThreadTimer(int timeoutSec, String taskName, Future<?> future) {
		this.timeoutSec = timeoutSec;
		this.taskName = taskName;
		this.future = future;
	}

	public ThreadTimer(int timeoutSec, String taskName, Future<?> future, Callback callback) {
		this.timeoutSec = timeoutSec;
		this.taskName = taskName;
		this.future = future;
		this.callback = callback;
	}

	public ThreadTimer(int timeoutSec, String taskName, Future<?> future, ErrorHandler errorHandler) {
		this.timeoutSec = timeoutSec;
		this.taskName = taskName;
		this.future = future;
		this.errorHandler = errorHandler;
	}

	public ThreadTimer(int timeoutSec, String taskName, Future<?> future, Callback callback, ErrorHandler errorHandler) {
		this.timeoutSec = timeoutSec;
		this.taskName = taskName;
		this.future = future;
		this.callback = callback;
		this.errorHandler = errorHandler;
	}

	@Override
	public void run() {
		boolean result = false;
		try {
			if (timeoutSec > 0) {
				result = null == future.get(timeoutSec, TimeUnit.SECONDS);
			} else {
				result = null == future.get();
			}
		} catch (Exception e) {
			future.cancel(true);
			if (errorHandler != null) {
				errorHandler.caught(e);
			} else {
				taskName = taskName == null || "".equals(taskName.trim()) ? "unknown" : taskName;
				LOGGER.error(MessageFormat.format("Execute Task Failed: TaskName:{0}, Timeout:{1} Sec", taskName, timeoutSec), e);
			}
		} finally {
			if (callback != null) {
				callback.callback(result);
			}
		}
	}

}
