package eg.edu.alexu.csd.filestructure.sort;


import eg.edu.alexu.csd.filestructure.sort.SortingAlgorithms.BubbleSort;
import eg.edu.alexu.csd.filestructure.sort.SortingAlgorithms.QuickSort;

import java.util.ArrayList;

public class Sort implements ISort {
    private QuickSort quickSort = new QuickSort();
    @Override
    public IHeap heapSort(ArrayList var1) {
        IHeap heap =new Heap();
        heap.build(var1);
        for(int i=0;i<var1.size();i++)
        {
            Comparable t=heap.extract();
        }
        return heap;
    }

    @Override
    public void sortSlow(ArrayList var1) {
        BubbleSort bubbleSort=new BubbleSort();
        bubbleSort.sort(var1);
    }

    @Override
    public void sortFast(ArrayList var1){
        quickSort.sort(var1);
    }
}
