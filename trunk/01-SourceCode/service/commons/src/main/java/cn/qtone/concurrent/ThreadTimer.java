package cn.qtone.concurrent;

import java.text.MessageFormat;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadTimer<T extends Runnable> implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ThreadTimer.class);

	private int timeoutSec;
	private String taskName;
	private T task;
	private Future<?> future;
	private Callback<T> callback;
	private ErrorHandler<T> errorHandler;
	private Result result = Result.ok;

	public ThreadTimer(T task, Future<?> future) {
		this.task = task;
		this.future = future;
	}

	public ThreadTimer(T task, Future<?> future, Callback<T> callback) {
		this.task = task;
		this.future = future;
		this.callback = callback;
	}

	public ThreadTimer(T task, Future<?> future, ErrorHandler<T> errorHandler) {
		this.task = task;
		this.future = future;
		this.errorHandler = errorHandler;
	}

	public ThreadTimer(T task, Future<?> future, Callback<T> callback, ErrorHandler<T> errorHandler) {
		this.task = task;
		this.future = future;
		this.callback = callback;
		this.errorHandler = errorHandler;
	}

	public ThreadTimer(String taskName, T task, Future<?> future) {
		this.taskName = taskName;
		this.task = task;
		this.future = future;
	}

	public ThreadTimer(String taskName, T task, Future<?> future, Callback<T> callback) {
		this.taskName = taskName;
		this.task = task;
		this.future = future;
		this.callback = callback;
	}

	public ThreadTimer(String taskName, T task, Future<?> future, ErrorHandler<T> errorHandler) {
		this.taskName = taskName;
		this.task = task;
		this.future = future;
		this.errorHandler = errorHandler;
	}

	public ThreadTimer(String taskName, T task, Future<?> future, Callback<T> callback, ErrorHandler<T> errorHandler) {
		this.taskName = taskName;
		this.task = task;
		this.future = future;
		this.callback = callback;
		this.errorHandler = errorHandler;
	}

	public ThreadTimer(int timeoutSec, T task, Future<?> future) {
		this.timeoutSec = timeoutSec;
		this.task = task;
		this.future = future;
	}

	public ThreadTimer(int timeoutSec, T task, Future<?> future, Callback<T> callback) {
		this.timeoutSec = timeoutSec;
		this.task = task;
		this.future = future;
		this.callback = callback;
	}

	public ThreadTimer(int timeoutSec, T task, Future<?> future, ErrorHandler<T> errorHandler) {
		this.timeoutSec = timeoutSec;
		this.task = task;
		this.future = future;
		this.errorHandler = errorHandler;
	}

	public ThreadTimer(int timeoutSec, T task, Future<?> future, Callback<T> callback, ErrorHandler<T> errorHandler) {
		this.timeoutSec = timeoutSec;
		this.task = task;
		this.future = future;
		this.callback = callback;
		this.errorHandler = errorHandler;
	}

	public ThreadTimer(int timeoutSec, String taskName, T task, Future<?> future) {
		this.timeoutSec = timeoutSec;
		this.taskName = taskName;
		this.task = task;
		this.future = future;
	}

	public ThreadTimer(int timeoutSec, String taskName, T task, Future<?> future, Callback<T> callback) {
		this.timeoutSec = timeoutSec;
		this.taskName = taskName;
		this.task = task;
		this.future = future;
		this.callback = callback;
	}

	public ThreadTimer(int timeoutSec, String taskName, T task, Future<?> future, ErrorHandler<T> errorHandler) {
		this.timeoutSec = timeoutSec;
		this.taskName = taskName;
		this.task = task;
		this.future = future;
		this.errorHandler = errorHandler;
	}

	public ThreadTimer(int timeoutSec, String taskName, T task, Future<?> future, Callback<T> callback, ErrorHandler<T> errorHandler) {
		this.timeoutSec = timeoutSec;
		this.taskName = taskName;
		this.task = task;
		this.future = future;
		this.callback = callback;
		this.errorHandler = errorHandler;
	}

	@Override
	public void run() {
		if (taskName == null || "".equals(taskName.trim())) {
			taskName = Thread.currentThread().getName();
		}
		try {
			if (timeoutSec > 0) {
				future.get(timeoutSec, TimeUnit.SECONDS);
			} else {
				future.get();
			}
		} catch (Exception e) {
			future.cancel(true);
			if (e.getClass().equals(TimeoutException.class)) {
				result = Result.timeout;
				LOGGER.warn(MessageFormat.format("Timeout: TaskName:{0}, Timeout:{1} Sec", taskName, timeoutSec), e);
			} else {
				result = Result.error;
				if (errorHandler != null) {
					errorHandler.caught(taskName, task, e);
				} else {
					LOGGER.error(MessageFormat.format("Error: TaskName:{0}, Timeout:{1} Sec", taskName, timeoutSec), e);
				}
			}
		} finally {
			if (callback != null) {
				callback.callback(taskName, task, result);
			}
		}
	}

}
