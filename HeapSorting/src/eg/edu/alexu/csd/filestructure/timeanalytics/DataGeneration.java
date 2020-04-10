package eg.edu.alexu.csd.filestructure.timeanalytics;

import com.sun.deploy.net.MessageHeader;
import eg.edu.alexu.csd.filestructure.sort.Sort;
import eg.edu.alexu.csd.filestructure.sort.SortingAlgorithms.BubbleSort;
import eg.edu.alexu.csd.filestructure.sort.SortingAlgorithms.QuickSort;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

public class DataGeneration {

    private static ArrayList<Integer> array=new ArrayList<>();

    public static ArrayList<Integer> getN2time() {
        return n2time;
    }

    public static ArrayList<Integer> getNlongntime() {
        return nlongntime;
    }

    public static ArrayList<Integer> getHeaptime() {
        return heaptime;
    }

    private static ArrayList<Integer> n2time=new ArrayList<>();
    private static ArrayList<Integer> nlongntime=new ArrayList<>();
    private static ArrayList<Integer> heaptime=new ArrayList<>();

    private static int n=5000;
    public static void generateData() {
        Random r = new Random();
        Sort sort=new Sort<>();
        for(int i=0;i<n;i++)
        {
            int val = r.nextInt(2147483647);
            array.add(val);
            Instant start = Instant.now();
            ArrayList<Integer> temp=new ArrayList<>(array);
            sort.sortSlow(temp);
            Instant finish = Instant.now();
            long timeElapsed = Duration.between(start, finish).toMillis();  //in m
            n2time.add((int) timeElapsed);
             start = Instant.now();
             temp= new ArrayList<>(array);
            sort.heapSort(temp);
             finish = Instant.now();
             timeElapsed = Duration.between(start, finish).toMillis();  //in m
            heaptime.add((int) timeElapsed);
            start = Instant.now();
             temp=new ArrayList<>(array);
            sort.sortFast(temp);
             finish = Instant.now();
             timeElapsed = Duration.between(start, finish).toMillis();  //in m
            nlongntime.add((int) timeElapsed);
        }

    }

}
