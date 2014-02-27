package cn.qtone.util;

public class InstanceCreator {

	@SuppressWarnings("unchecked")
	public static final <T> T create(Class<T> clazz) {
		Object object = null;
		try {
			object = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("create new instance failed!", e);
		}

		return (T) object;
	}

	@SuppressWarnings("unchecked")
	public static final <T> T create(Class<T> clazz, Class<?> parameterType, Object initarg) {
		Object object = null;
		try {
			object = clazz.getConstructor(parameterType).newInstance(initarg);
		} catch (Exception e) {
			throw new RuntimeException("create new instance failed!", e);
		}

		return (T) object;
	}

	@SuppressWarnings("unchecked")
	public static final <T> T create(Class<T> clazz, Class<?>[] parameterTypes, Object[] initargs) {
		Object object = null;
		try {
			object = clazz.getConstructor(parameterTypes).newInstance(initargs);
		} catch (Exception e) {
			throw new RuntimeException("create new instance failed!", e);
		}

		return (T) object;
	}

	@SuppressWarnings("unchecked")
	public static final <T> T create(String className) {
		Object object = null;
		try {
			object = Class.forName(className).newInstance();
		} catch (Exception e) {
			throw new RuntimeException("create new instance failed!", e);
		}

		return (T) object;
	}

	@SuppressWarnings("unchecked")
	public static final <T> T create(String className, Class<?> parameterType, Object initarg) {
		Object object = null;
		try {
			object = Class.forName(className).getConstructor(parameterType).newInstance(initarg);
		} catch (Exception e) {
			throw new RuntimeException("create new instance failed!", e);
		}

		return (T) object;
	}

	@SuppressWarnings("unchecked")
	public static final <T> T create(String className, Class<?>[] parameterTypes, Object[] initargs) {
		Object object = null;
		try {
			object = Class.forName(className).getConstructor(parameterTypes).newInstance(initargs);
		} catch (Exception e) {
			throw new RuntimeException("create new instance failed!", e);
		}

		return (T) object;
	}

}
