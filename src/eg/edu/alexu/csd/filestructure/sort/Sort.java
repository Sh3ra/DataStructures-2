package eg.edu.alexu.csd.filestructure.sort;


import eg.edu.alexu.csd.filestructure.sort.SortingAlgorithms.QuickSort;

import java.util.ArrayList;

public class Sort implements ISort {
    private QuickSort quickSort = new QuickSort();
    @Override
    public IHeap heapSort(ArrayList var1) {
        return null;
    }

    @Override
    public void sortSlow(ArrayList var1) {

    }

    @Override
    public void sortFast(ArrayList var1){
        quickSort.sort(var1);
    }
}
