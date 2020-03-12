package eg.edu.alexu.csd.filestructure.sort.SortingAlgorithms;

import java.util.ArrayList;

public class QuickSort {
    public void sort(ArrayList<Integer> ar){
        int start = 0;
        int end = ar.size()-1;
        quickSort(ar,start,end);
    }

    private void quickSort(ArrayList <Integer> ar,int start, int end){
        if(end - start <= 0) return;
        int index = partition(ar,start, end);
        quickSort(ar,index+1,end);
        quickSort(ar, start, index-1);
    }

    private int partition(ArrayList<Integer> ar, int start, int end){
        int random = getRandomNumber(start, end);
        swap(ar,start, random);
        int pivot = ar.get(start);
        int separator = start;
        for(int i = start+1;i <= end;i++){
            if(ar.get(i) >= pivot){
                continue;
            } else {
                separator++;
                swap(ar,i,separator);
            }
        }
        swap(ar,start,separator);
        return separator;
    }
    private void swap(ArrayList<Integer> ar, int i1, int i2){
        int temp = ar.get(i1);
        ar.set(i1,ar.get(i2));
        ar.set(i2,temp);
    }

    private int getRandomNumber(int start,int end){
        return (int)(Math.random()*(end-start+1)) + start;
    }

    public static void main(String[] args){
        QuickSort x = new QuickSort();
        ArrayList<Integer> vr = new ArrayList<>();
        x.sort(vr);
        for(int i = 0;i<vr.size();i++) System.out.printf("%d ",vr.get(i));

    }
}
