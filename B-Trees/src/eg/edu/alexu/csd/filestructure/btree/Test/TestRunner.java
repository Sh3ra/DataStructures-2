package eg.edu.alexu.csd.filestructure.btree.Test;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;


public class TestRunner {

	private static Class<?> implementation;
	private static boolean Debug = false;

	public static Object getImplementationInstanceForInterface(Class<?> interfaceToTest, Object[] params) {
		initaiteforInterface(interfaceToTest);
		try {
			for (Constructor<?> constructor : implementation.getDeclaredConstructors()) {
				if (constructor.getParameterTypes().length == 1) {
					constructor.setAccessible(true);
					return constructor.newInstance(params);
				}
			}
		} catch (Throwable e) {
			if (e.getCause().toString().equals("javax.management.RuntimeErrorException"))
				throw new RuntimeErrorException(null);
		}
		return null;
	}

	public static void initaiteforInterface(Class<?> interfaceToTest) {
		List<Class<?>> candidateClasses = ReflectionHelper.findClassesImplementing(interfaceToTest,
				interfaceToTest.getPackage());

		Class<?> studentClass = candidateClasses.get(0);
		implementation = studentClass;
	}

	public static void fail(String message, Throwable throwable) {
		try {
			StringBuffer log = new StringBuffer();
			if (message != null)
				log.append(message).append("\n");
			if (throwable != null) {
				/*
				 * Throwable cause = throwable.getCause(); if(cause!=null)
				 * log.append(showError(cause)); //
				 */
				log.append(showError(throwable));
			}
			Assert.fail(log.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String showError(Throwable e) throws IOException {
		if (e == null)
			return "Error!";
		e.printStackTrace();
		StringBuffer buffer = new StringBuffer();
		if (Debug)
			buffer.append("\t\t\tError: " + e + " " + e.getMessage());
		else
			buffer.append("\t\t\tError: " + e);
		if (Debug) {
			for (StackTraceElement trace : e.getStackTrace()) {
				buffer.append("\n" + trace.getClassName() + "." + trace.getMethodName() + "(): Line "
						+ trace.getLineNumber());
			}
		} else {
			if (implementation != null) {
				for (StackTraceElement trace : e.getStackTrace()) {
					if (trace.getClassName().equals(implementation.getName())) {
						buffer.append("\n" + trace.getClassName() + "." + trace.getMethodName() + "(): Line "
								+ trace.getLineNumber());
					}
				}
			}
		}
		return buffer.toString().replaceAll("\\n", "\n\t\t\t\t");
	}

}
