
package eg.edu.alexu.csd.filestructure.test;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.List;
import org.junit.Assert;

public class TestRunner {
    private static Class<?> implementation;
    private static boolean Debug = false;

    public TestRunner() {
    }

    public static Object getImplementationInstanceForInterface(Class<?> interfaceToTest) {
        initaiteforInterface(interfaceToTest);

        try {
            Constructor[] var4;
            int var3 = (var4 = implementation.getDeclaredConstructors()).length;

            for(int var2 = 0; var2 < var3; ++var2) {
                Constructor<?> constructor = var4[var2];
                if (constructor.getParameterTypes().length == 0) {
                    constructor.setAccessible(true);
                    return constructor.newInstance((Object[])null);
                }
            }
        } catch (Throwable var5) {
        }

        return null;
    }

    public static void initaiteforInterface(Class<?> interfaceToTest) {
        List<Class<?>> candidateClasses = ReflectionHelper.findClassesImplementing(interfaceToTest, interfaceToTest.getPackage());
        Class<?> studentClass = (Class)candidateClasses.get(0);
        implementation = studentClass;
    }

    public static void fail(String message, Throwable throwable) {
        try {
            StringBuffer log = new StringBuffer();
            if (message != null) {
                log.append(message).append("\n");
            }

            if (throwable != null) {
                log.append(showError(throwable));
            }

            Assert.fail(log.toString());
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    private static String showError(Throwable e) throws IOException {
        if (e == null) {
            return "Error!";
        } else {
            e.printStackTrace();
            StringBuffer buffer = new StringBuffer();
            if (Debug) {
                buffer.append("\t\t\tError: " + e + " " + e.getMessage());
            } else {
                buffer.append("\t\t\tError: " + e);
            }

            StackTraceElement trace;
            int var3;
            int var4;
            StackTraceElement[] var5;
            if (Debug) {
                var4 = (var5 = e.getStackTrace()).length;

                for(var3 = 0; var3 < var4; ++var3) {
                    trace = var5[var3];
                    buffer.append("\n" + trace.getClassName() + "." + trace.getMethodName() + "(): Line " + trace.getLineNumber());
                }
            } else if (implementation != null) {
                var4 = (var5 = e.getStackTrace()).length;

                for(var3 = 0; var3 < var4; ++var3) {
                    trace = var5[var3];
                    if (trace.getClassName().equals(implementation.getName())) {
                        buffer.append("\n" + trace.getClassName() + "." + trace.getMethodName() + "(): Line " + trace.getLineNumber());
                    }
                }
            }

            return buffer.toString().replaceAll("\\n", "\n\t\t\t\t");
        }
    }
}
