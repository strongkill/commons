package cn.qtone.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程池
 * 
 * @author 卢俊生, 2013-12-24
 */
public class ThreadPool {

	private static ExecutorService threadPool = Executors.newCachedThreadPool();

	/**
	 * 在未来某个时间执行给定的命令。该命令可能在新的线程、已入池的线程或者正调用的线程中执行，这由 Executor 实现决定。
	 * 
	 * @param task 可运行的任务
	 */
	public static void execute(Runnable task) {
		threadPool.execute(task);
	}

	/**
	 * 提交一个 Runnable 任务用于执行，并返回一个表示该任务的 Future。该 Future 的 get 方法在成功 完成时将会返回 null。
	 * 
	 * @param task 要提交的任务
	 * @return 表示任务等待完成的 Future
	 */
	public static Future<?> submit(Runnable task) {
		return threadPool.submit(task);
	}

	/**
	 * 提交任务
	 * 
	 * @param task 要提交的任务
	 * @param callback 回调对象
	 */
	public static <T extends Runnable> void submit(T task, Callback<T> callback) {
		Future<?> future = threadPool.submit(task);
		threadPool.execute(new ThreadTimer<T>(task, future, callback));
	}

	/**
	 * 提交任务
	 * 
	 * @param task 要提交的任务
	 * @param errorHandler 错误处理对象
	 */
	public static <T extends Runnable> void submit(T task, ErrorHandler<T> errorHandler) {
		Future<?> future = threadPool.submit(task);
		threadPool.execute(new ThreadTimer<T>(task, future, errorHandler));
	}

	/**
	 * 提交任务
	 * 
	 * @param task 要提交的任务
	 * @param callback 回调对象
	 * @param errorHandler 错误处理对象
	 */
	public static <T extends Runnable> void submit(T task, Callback<T> callback, ErrorHandler<T> errorHandler) {
		Future<?> future = threadPool.submit(task);
		threadPool.execute(new ThreadTimer<T>(task, future, callback, errorHandler));
	}

	/**
	 * 提交任务
	 * 
	 * @param taskName 任务名称
	 * @param task 要提交的任务
	 */
	public static <T extends Runnable> void submit(String taskName, T task) {
		Future<?> future = threadPool.submit(task);
		threadPool.execute(new ThreadTimer<T>(taskName, task, future));
	}

	/**
	 * 提交任务
	 * 
	 * @param taskName 任务名称
	 * @param task 要提交的任务
	 * @param callback 回调对象
	 */
	public static <T extends Runnable> void submit(String taskName, T task, Callback<T> callback) {
		Future<?> future = threadPool.submit(task);
		threadPool.execute(new ThreadTimer<T>(taskName, task, future, callback));
	}

	/**
	 * 提交任务
	 * 
	 * @param taskName 任务名称
	 * @param task 要提交的任务
	 * @param errorHandler 错误处理对象
	 */
	public static <T extends Runnable> void submit(String taskName, T task, ErrorHandler<T> errorHandler) {
		Future<?> future = threadPool.submit(task);
		threadPool.execute(new ThreadTimer<T>(taskName, task, future, errorHandler));
	}

	/**
	 * 提交任务
	 * 
	 * @param taskName 任务名称
	 * @param task 要提交的任务
	 * @param callback 回调对象
	 * @param errorHandler 错误处理对象
	 */
	public static <T extends Runnable> void submit(String taskName, T task, Callback<T> callback, ErrorHandler<T> errorHandler) {
		Future<?> future = threadPool.submit(task);
		threadPool.execute(new ThreadTimer<T>(taskName, task, future, callback, errorHandler));
	}

	/**
	 * 提交任务
	 * 
	 * @param timeoutSec 超时时间(秒)
	 * @param task 要提交的任务
	 */
	public static <T extends Runnable> void submit(int timeoutSec, T task) {
		Future<?> future = threadPool.submit(task);
		threadPool.execute(new ThreadTimer<T>(timeoutSec, task, future));
	}

	/**
	 * 提交任务
	 * 
	 * @param timeoutSec 超时时间(秒)
	 * @param task 要提交的任务
	 * @param callback 回调对象
	 */
	public static <T extends Runnable> void submit(int timeoutSec, T task, Callback<T> callback) {
		Future<?> future = threadPool.submit(task);
		threadPool.execute(new ThreadTimer<T>(timeoutSec, task, future, callback));
	}

	/**
	 * 提交任务
	 * 
	 * @param timeoutSec 超时时间(秒)
	 * @param task 要提交的任务
	 * @param errorHandler 错误处理对象
	 */
	public static <T extends Runnable> void submit(int timeoutSec, T task, ErrorHandler<T> errorHandler) {
		Future<?> future = threadPool.submit(task);
		threadPool.execute(new ThreadTimer<T>(timeoutSec, task, future, errorHandler));
	}

	/**
	 * 提交任务
	 * 
	 * @param timeoutSec 超时时间(秒)
	 * @param task 要提交的任务
	 * @param callback 回调对象
	 * @param errorHandler 错误处理对象
	 */
	public static <T extends Runnable> void submit(int timeoutSec, T task, Callback<T> callback, ErrorHandler<T> errorHandler) {
		Future<?> future = threadPool.submit(task);
		threadPool.execute(new ThreadTimer<T>(timeoutSec, task, future, callback, errorHandler));
	}

	/**
	 * 提交任务
	 * 
	 * @param timeoutSec 超时时间(秒)
	 * @param taskName 任务名称
	 * @param task 要提交的任务
	 */
	public static <T extends Runnable> void submit(int timeoutSec, String taskName, T task) {
		Future<?> future = threadPool.submit(task);
		threadPool.execute(new ThreadTimer<T>(timeoutSec, taskName, task, future));
	}

	/**
	 * 提交任务
	 * 
	 * @param timeoutSec 超时时间(秒)
	 * @param taskName 任务名称
	 * @param task 要提交的任务
	 * @param callback 回调对象
	 */
	public static <T extends Runnable> void submit(int timeoutSec, String taskName, T task, Callback<T> callback) {
		Future<?> future = threadPool.submit(task);
		threadPool.execute(new ThreadTimer<T>(timeoutSec, taskName, task, future, callback));
	}

	/**
	 * 提交任务
	 * 
	 * @param timeoutSec 超时时间(秒)
	 * @param taskName 任务名称
	 * @param task 要提交的任务
	 * @param errorHandler 错误处理对象
	 */
	public static <T extends Runnable> void submit(int timeoutSec, String taskName, T task, ErrorHandler<T> errorHandler) {
		Future<?> future = threadPool.submit(task);
		threadPool.execute(new ThreadTimer<T>(timeoutSec, taskName, task, future, errorHandler));
	}

	/**
	 * 提交任务
	 * 
	 * @param timeoutSec 超时时间(秒)
	 * @param taskName 任务名称
	 * @param task 要提交的任务
	 * @param callback 回调对象
	 * @param errorHandler 错误处理对象
	 */
	public static <T extends Runnable> void submit(int timeoutSec, String taskName, T task, Callback<T> callback, ErrorHandler<T> errorHandler) {
		Future<?> future = threadPool.submit(task);
		threadPool.execute(new ThreadTimer<T>(timeoutSec, taskName, task, future, callback, errorHandler));
	}

	public static void main(String[] args) {
		submit(1, "testTask", new Runnable() {
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
		}, new Callback<Runnable>() {

			@Override
			public void callback(String taskName, Runnable task, Result result) {
				System.out.println(taskName);
				System.out.println(result);
			}
		});

		threadPool.shutdown();
	}
}
