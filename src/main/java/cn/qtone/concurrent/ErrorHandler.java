package cn.qtone.concurrent;

public interface ErrorHandler {

	public abstract void caught(Throwable cause);
}
