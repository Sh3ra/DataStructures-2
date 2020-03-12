package eg.edu.alexu.csd.filestructure.sort.SortingAlgorithms;

import java.util.ArrayList;

public class QuickSort <T extends Comparable<T>>{
    public void sort(ArrayList<T> ar){
        int start = 0;
        int end = ar.size()-1;
        quickSort(ar,start,end);
    }

    private void quickSort(ArrayList <T> ar,int start, int end){
        if(end - start <= 0) return;
        int index = partition(ar,start, end);
        quickSort(ar,index+1,end);
        quickSort(ar, start, index-1);
    }

    private int partition(ArrayList<T> ar, int start, int end){
        int random = getRandomNumber(start, end);
        swap(ar,start, random);
        T pivot = ar.get(start);
        int separator = start;
        for(int i = start+1;i <= end;i++){
            if(ar.get(i).compareTo(pivot) >= 0){
                continue;
            } else {
                separator++;
                swap(ar,i,separator);
            }
        }
        swap(ar,start,separator);
        return separator;
    }
    private void swap(ArrayList<T> ar, int i1, int i2){
        T temp = ar.get(i1);
        ar.set(i1,ar.get(i2));
        ar.set(i2,temp);
    }

    private int getRandomNumber(int start,int end){
        return (int)(Math.random()*(end-start+1)) + start;
    }
}
