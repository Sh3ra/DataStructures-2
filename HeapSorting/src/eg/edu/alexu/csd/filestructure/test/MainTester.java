package eg.edu.alexu.csd.filestructure.test;

import java.util.ArrayList;
import java.util.Iterator;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class MainTester {
    public MainTester() {
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(new Class[]{IntegrationTest.class});
        Result result2 = JUnitCore.runClasses(new Class[]{UnitTest.class});
        int totalNumOfTests = result.getRunCount() + result2.getRunCount();
        int totalFailures = result.getFailureCount() + result2.getFailureCount();
        System.out.println("Total tests passed: " + (totalNumOfTests - totalFailures) + "/" + totalNumOfTests);
        ArrayList<Failure> failures = new ArrayList();
        failures.addAll(result.getFailures());
        failures.addAll(result2.getFailures());
        Iterator var7 = failures.iterator();

        while(var7.hasNext()) {
            Failure failure = (Failure)var7.next();
            System.out.println(failure.toString());
        }

    }
}
