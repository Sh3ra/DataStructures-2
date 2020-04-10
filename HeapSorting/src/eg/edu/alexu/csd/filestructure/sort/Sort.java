package eg.edu.alexu.csd.filestructure.sort;


import eg.edu.alexu.csd.filestructure.sort.SortingAlgorithms.BubbleSort;
import eg.edu.alexu.csd.filestructure.sort.SortingAlgorithms.QuickSort;

import java.util.ArrayList;

public class Sort<T extends Comparable<T>> implements ISort {
    private QuickSort quickSort = new QuickSort();
    @Override
    public IHeap heapSort(ArrayList var1) {
        IHeap heap =new Heap();
        heap.build(var1);
        ArrayList<INode> sorted = new ArrayList<>();
        Heap v = new Heap();
        int index = 1;
        while (heap.size()>0) sorted.add(new Node(heap.extract(),index++,sorted,v));
        reverseArraylist(sorted,v);
        sorted.add(0,new Node(-7,0,sorted,v));
        v.setHeapArray(sorted);
        v.setSize(sorted.size());
        return v;
    }

    private void reverseArraylist(ArrayList<INode> sorted,Heap v){
        for(int i = 0;i<sorted.size()/2;i++){
            INode temp = new Node(sorted.get(i).getValue(),sorted.size()-i,sorted,v);
            sorted.set(i,new Node(sorted.get(sorted.size()-1-i).getValue(),i+1,sorted,v));
            sorted.set(sorted.size()-1-i,temp);
        }
    }

    @Override
    public void sortSlow(ArrayList var1) {
        if(var1 == null) return;
        BubbleSort bubbleSort=new BubbleSort();
        bubbleSort.sort(var1);
    }

    @Override
    public void sortFast(ArrayList var1){
        if(var1 == null) return;
        quickSort.sort(var1);
    }
}
