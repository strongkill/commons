package cn.qtone.concurrent;

/**
 * 多线程任务错误处理对象
 * 
 * @author 卢俊生, 2013-12-24
 * @param <T> 任务类,Runnable子类
 */
public interface ErrorHandler<T extends Runnable> {

	/**
	 * 错误处理方法
	 * 
	 * @param taskName 任务名称
	 * @param task 任务实例
	 * @param cause 异常对象
	 */
	public abstract void caught(String taskName, T task, Throwable cause);
}
