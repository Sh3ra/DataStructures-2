package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Heap implements IHeap {
    private ArrayList heapArray = new ArrayList<>(2);
    @Override
    public INode getRoot() {
        if(heapArray.size()>2) {
            return new Node((Comparable) heapArray.get(1),1,heapArray);
        }
        return null;
    }

    @Override
    public int size() {
        return heapArray.size();
    }

    @Override
    public void heapify(INode var1){

    }

    private void decreaseKey(INode var1){
        if(var1.getParent() != null) {
            if(var1.getValue().compareTo(var1.getParent().getValue()) > 0)
                swap(var1,var1.getParent());
            decreaseKey(var1.getParent());
        }
    }

    @Override
    public Comparable extract() {
        return null;
    }

    @Override
    public void insert(Comparable var1) {
        INode newNode = new Node(var1, heapArray.size()-1, heapArray);
        heapArray.add(newNode);
        decreaseKey(newNode);
    }

    @Override
    public void build(Collection var1) {
        heapArray=(ArrayList)var1;
        int index=heapArray.size()-1;
        if(index<0)return;
        while (index>=0)
        {
            Node parent=new Node((Comparable) heapArray.get(index),index,heapArray);
            parent= (Node) parent.getParent();
            Node right= (Node) parent.getRightChild();
            Node left=(Node)parent.getLeftChild();
            if(parent.getValue().compareTo(left.getValue())<0)
            {
                swap(left,parent);
            }
            if(parent.getValue().compareTo(right.getValue())<0)
            {
                swap(right,parent);
            }
            index--;
            heapArray=parent.heapArray;
        }

    }

    public static void main(String[] args) {
        ArrayList arr=new ArrayList();
        arr.add(7);
        arr.add(2);
        arr.add(4);
        arr.add(5);
        arr.add(10);
        arr.add(8);
        Heap heap=new Heap();
        heap.build(arr);
    }
    private void swap(INode child, INode parent){
        Comparable tempValue = parent.getValue();
        parent.setValue(child.getValue());
        child.setValue(tempValue);
    }

}
