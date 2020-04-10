package eg.edu.alexu.csd.filestructure.test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class ReflectionHelper {
    public ReflectionHelper() {
    }

    public static List<Class<?>> findClassesImplementing(Class<?> interfaceClass, Package fromPackage) {
        if (interfaceClass == null) {
            System.out.println("Unknown subclass.");
            return null;
        } else if (fromPackage == null) {
            System.out.println("Unknown package.");
            return null;
        } else {
            ArrayList rVal = new ArrayList();

            try {
                Class[] targets = getAllClassesFromPackage(fromPackage.getName());
                if (targets != null) {
                    Class[] var7 = targets;
                    int var6 = targets.length;

                    for(int var5 = 0; var5 < var6; ++var5) {
                        Class<?> aTarget = var7[var5];
                        if (aTarget != null && !aTarget.equals(interfaceClass) && interfaceClass.isAssignableFrom(aTarget)) {
                            rVal.add(aTarget);
                        }
                    }
                }
            } catch (ClassNotFoundException var8) {
                System.out.println("Error reading package name.");
            } catch (IOException var9) {
                System.out.println("Error reading classes in package.");
            }

            return rVal;
        }
    }

    private static Class<?>[] getAllClassesFromPackage(String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        assert classLoader != null;

        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        ArrayList dirs = new ArrayList();

        while(resources.hasMoreElements()) {
            URL resource = (URL)resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }

        ArrayList<Class<?>> classes = new ArrayList();
        Iterator var7 = dirs.iterator();

        while(var7.hasNext()) {
            File directory = (File)var7.next();
            classes.addAll(findClasses(directory, packageName));
        }

        return (Class[])classes.toArray(new Class[classes.size()]);
    }

    private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList();
        if (!directory.exists()) {
            return classes;
        } else {
            File[] files = directory.listFiles();
            File[] var7 = files;
            int var6 = files.length;

            for(int var5 = 0; var5 < var6; ++var5) {
                File file = var7[var5];
                if (file.isDirectory()) {
                    assert !file.getName().contains(".");

                    classes.addAll(findClasses(file, packageName + "." + file.getName()));
                } else if (file.getName().endsWith(".class")) {
                    classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
                }
            }

            return filterConcerteClasses(classes);
        }
    }

    private static List<Class<?>> filterConcerteClasses(List<Class<?>> classes) {
        List<Class<?>> filteredClasses = null;
        Iterator var3 = classes.iterator();

        while(var3.hasNext()) {
            Class<?> fetchedClass = (Class)var3.next();
            Integer modifiers = fetchedClass.getModifiers();
            if (!Modifier.isInterface(modifiers) && !Modifier.isAbstract(modifiers) && Modifier.isPublic(modifiers)) {
                if (filteredClasses == null) {
                    filteredClasses = new ArrayList();
                }

                filteredClasses.add(fetchedClass);
            }
        }

        return filteredClasses;
    }
}
