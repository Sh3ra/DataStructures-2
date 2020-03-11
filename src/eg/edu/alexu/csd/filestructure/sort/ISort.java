package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;

public interface ISort<T extends Comparable<T>> {
    IHeap<T> heapSort(ArrayList<T> var1);

    void sortSlow(ArrayList<T> var1);

    void sortFast(ArrayList<T> var1);
}
