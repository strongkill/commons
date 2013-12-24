package cn.qtone.concurrent;

/**
 * 多线程任务回调对象
 * 
 * @author 卢俊生, 2013-12-24
 * @param <T> 任务类,Runnable子类
 */
public interface Callback<T extends Runnable> {

	/**
	 * 回调方法
	 * 
	 * @param taskName 任务名称
	 * @param task 任务实例
	 * @param result 任务结果
	 * @see Result
	 */
	public abstract void callback(String taskName, T task, Result result);
}
