package cn.qtone.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 队列任务
 * @param <DATA> 泛型,process处理的对象类型
 */
public class QueueTask<DATA> {
	private static final Logger logger = LoggerFactory.getLogger(QueueTask.class);

	private Queue<DATA> queue = new ConcurrentLinkedQueue<DATA>();
	private ExecutorService threadPool;
	private Handler<DATA> handler;

	/** 是否异步处理,默认:false */
	private boolean async = false;

	/** 任务执行时间间隔,单位:秒, 默认:1 */
	private int interval = 1;

	/** 每次处理数据最小记录数, 默认:1 */
	private int minBatchSize = 1;

	/** 每次处理数据最大记录数, 默认:无限制 */
	private int maxBatchSize = Integer.MAX_VALUE;

	/** 连续异常界限, 默认:10; 达到该值后休眠{@link #errSleepSeconds}秒 */
	private int errLimit = 10;

	/** 连续异常达到 {@link #errLimit}后休眠时间,单位:秒, 默认:300 */
	private int errSleepSeconds = 300;

	/** 线程是否暂停 */
	private boolean paused = false;

	/** 线程是否已停止 */
	private boolean stoped = false;

	private QueueTask() {
	}

	private Thread processthread = new Thread(new Runnable() {
		@Override
		public void run() {
			try {
				int errCount = 0;
				while (!stoped) {
					_sleep(interval);
					try {
						while (queue.size() >= minBatchSize) {
							List<DATA> datas = new ArrayList<DATA>();
							while (true) {
								DATA data = null;
								if (datas.size() < maxBatchSize && (data = queue.poll()) != null) {
									datas.add(data);
								} else {
									break;
								}
							}
							_process(datas);
							errCount = 0;
						}
					} catch (Exception e) {
						logger.error("处理数据异常!", e);
						errCount++;
						if (errCount >= errLimit) {
							_sleep(errSleepSeconds);
						}
					}
				}
			} catch (InterruptedException e) {
				logger.error("队列任务执行异常!", e);
			}
		}
	});

	private void _process(final List<DATA> datas) {
		if (datas != null && !datas.isEmpty()) {
			if (async) {
				threadPool.execute(new Runnable() {
					@Override
					public void run() {
						handler.process(datas);
					}
				});
			} else {
				handler.process(datas);
			}
		}
	}

	private void _sleep(long seconds) throws InterruptedException {
		if (paused) {
			synchronized (processthread) {
				processthread.wait();
			}
		} else if (seconds > 0) {
			Thread.sleep(1000 * seconds);
		}
	}

	public void start() {
		processthread.start();
	}

	/**
	 * 重新启动线程处理数据
	 */
	public void restart() {
		if (paused) {
			synchronized (processthread) {
				paused = false;
				processthread.notify();
			}
		}
	}

	/**
	 * 暂停任务,同时也暂停向列队中添加数据
	 */
	public void pause() {
		if (!paused) {
			paused = true;
		}
	}

	/**
	 * 销毁任务,处理队列中剩余数据
	 */
	public synchronized void destroy() {
		stoped = true;
		while (!queue.isEmpty()) {
			List<DATA> datas = new ArrayList<DATA>();
			while (true) {
				DATA data = null;
				if (datas.size() < maxBatchSize && (data = queue.poll()) != null) {
					datas.add(data);
				} else {
					break;
				}
			}
			_process(datas);
		}
	}

	/**
	 * 向列队中添加待处理数据,任务暂时将添加失败
	 * @param data 待处理数据对象
	 * @return boolean
	 */
	public boolean add(DATA data) {
		boolean result = false;
		if (!stoped && !paused) {
			result = queue.add(data);
		}
		return result;
	}

	/** 队列中待处理数据项 **/
	public int size() {
		return queue.size();
	}

	/** 是否异步处理 */
	public boolean async() {
		return async;
	}

	/** 任务执行时间间隔,单位:秒, 默认:1 */
	public int interval() {
		return interval;
	}

	/** 每次处理数据最小记录数, 默认:1 */
	public int minBatchSize() {
		return minBatchSize;
	}

	/** 每次处理数据最大记录数, 默认:无限制 */
	public int maxBatchSize() {
		return maxBatchSize;
	}

	/** 连续异常界限, 默认:10; 达到该值后休眠{@link #errSleepSeconds()}秒 */
	public int errLimit() {
		return errLimit;
	}

	/** 连续异常达到 {@link #errLimit()}后休眠时间,单位:秒 */
	public int errSleepSeconds() {
		return errSleepSeconds;
	}

	public static interface Handler<DATA> {
		public void process(List<DATA> datas);
	}

	public static class Builder<DATA> {
		private Handler<DATA> handler;
		private boolean async;
		private int interval;
		private int minBatchSize;
		private int maxBatchSize;
		private int errLimit;
		private int errSleepSeconds;

		private Builder() {
		}

		public static <DATA> Builder<DATA> start(Class<DATA> clazz) {
			return new Builder<DATA>();
		}

		public QueueTask<DATA> build() {
			if (handler == null) {
				throw new IllegalArgumentException("handler has not been setted!");
			}
			if (maxBatchSize > 0 && minBatchSize > maxBatchSize) {
				throw new IllegalArgumentException("minBatchSize can not be greater then maxBatchSize!");
			}
			QueueTask<DATA> task = new QueueTask<DATA>();
			task.handler = handler;
			if (async) {
				task.async = true;
				task.threadPool = Executors.newCachedThreadPool();
			}
			if (interval > 0) {
				task.interval = interval;
			}
			if (minBatchSize > 0) {
				task.minBatchSize = minBatchSize;
			}
			if (maxBatchSize > 0) {
				task.maxBatchSize = maxBatchSize;
			}
			if (errLimit > 0) {
				task.errLimit = errLimit;
			}
			if (errSleepSeconds > 0) {
				task.errSleepSeconds = errSleepSeconds;
			}
			return task;
		}

		public Builder<DATA> handler(Handler<DATA> handler) {
			this.handler = handler;
			return this;
		}

		/** 是否异步处理,默认:false */
		public Builder<DATA> async(boolean async) {
			this.async = async;
			return this;
		}

		/** 任务执行时间间隔,单位:秒 */
		public Builder<DATA> interval(int interval) {
			this.interval = interval;
			return this;
		}

		/** 每次处理数据最小记录数 */
		public Builder<DATA> minBatchSize(int minBatchSize) {
			this.minBatchSize = minBatchSize;
			return this;
		}

		/** 每次处理数据最大记录数 */
		public Builder<DATA> maxBatchSize(int maxBatchSize) {
			this.maxBatchSize = maxBatchSize;
			return this;
		}

		/** 连续异常界限, 达到该值后休眠{@link #errSleepSeconds()}秒 */
		public Builder<DATA> errLimit(int errLimit) {
			this.errLimit = errLimit;
			return this;
		}

		/** 连续异常达到 {@link #errLimit()}后休眠时间,单位:秒 */
		public Builder<DATA> errSleepSeconds(int errSleepSeconds) {
			this.errSleepSeconds = errSleepSeconds;
			return this;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Handler<Long> handler = new Handler<Long>() {
			@Override
			public void process(List<Long> datas) {
				System.out.print(datas.size() + " ==> ");
				for (Long data : datas) {
					System.out.print(data + "	");
				}
				System.out.println();
			}
		};

		QueueTask<Long> tastTask = Builder.start(Long.class).handler(handler).interval(3).maxBatchSize(5).build();
		tastTask.start();

		while (true) {
			Thread.sleep(300);
			for (int i = 0; i < 3; i++) {
				tastTask.add(System.currentTimeMillis() );
			}
		}
	}

}
