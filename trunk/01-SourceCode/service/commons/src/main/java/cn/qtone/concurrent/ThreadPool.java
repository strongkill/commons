package cn.qtone.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPool {

	private static ExecutorService threadPool = Executors.newCachedThreadPool();

	public static void execute(Runnable task) {
		threadPool.execute(task);
	}

	public static Future<?> submit(Runnable task) {
		return threadPool.submit(task);
	}

	public static void submit(String taskName, Runnable task) {
		Future<?> future = threadPool.submit(task);
		threadPool.execute(new ThreadTimer(taskName, future));
	}

	public static void submit(String taskName, Runnable task, Callback callback) {
		Future<?> future = threadPool.submit(task);
		threadPool.execute(new ThreadTimer(taskName, future, callback));
	}

	public static void submit(String taskName, Runnable task, ErrorHandler errorHandler) {
		Future<?> future = threadPool.submit(task);
		threadPool.execute(new ThreadTimer(taskName, future, errorHandler));
	}

	public static void submit(String taskName, Runnable task, Callback callback, ErrorHandler errorHandler) {
		Future<?> future = threadPool.submit(task);
		threadPool.execute(new ThreadTimer(taskName, future, callback, errorHandler));
	}

	public static void submit(int timeoutSec, String taskName, Runnable task) {
		Future<?> future = threadPool.submit(task);
		threadPool.execute(new ThreadTimer(timeoutSec, taskName, future));
	}

	public static void submit(int timeoutSec, String taskName, Runnable task, Callback callback) {
		Future<?> future = threadPool.submit(task);
		threadPool.execute(new ThreadTimer(timeoutSec, taskName, future, callback));
	}

	public static void submit(int timeoutSec, String taskName, Runnable task, ErrorHandler errorHandler) {
		Future<?> future = threadPool.submit(task);
		threadPool.execute(new ThreadTimer(timeoutSec, taskName, future, errorHandler));
	}

	public static void submit(int timeoutSec, String taskName, Runnable task, Callback callback, ErrorHandler errorHandler) {
		Future<?> future = threadPool.submit(task);
		threadPool.execute(new ThreadTimer(timeoutSec, taskName, future, callback, errorHandler));
	}

	public static void main(String[] args) {
		submit(0, "testTask", new Runnable() {
			@Override
			public void run() {
				System.err.println("testTask start");
				try {
					Thread.sleep(1190L);
					System.err.println("testTask End");
				} catch (InterruptedException e) {
				}
				Thread.yield();
			}
		}, new Callback() {
			@Override
			public void callback(boolean result) {
				System.err.println("callback, result :" + result);
			}
		}, new ErrorHandler() {
			@Override
			public void caught(Throwable cause) {
				System.err.println("错误 : " + cause.toString());
			}
		});

		threadPool.shutdown();
	}
}
