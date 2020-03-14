package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Heap implements IHeap {
    private int size=1;
    private ArrayList<INode> heapArray = new ArrayList<>();

    public void setHeapArray(ArrayList<INode> heapArray){
        this.heapArray = heapArray;
    }

    public void setSize(int size){
        this.size = size;
    }
    @Override
    public INode getRoot() {
        if(size>1)
        return heapArray.get(1);
        else return null;
    }

    @Override
    public int size() {
        return size-1;
    }

    @Override
    public void heapify(INode var1) {
        if(var1 == null) return;
        int greatest = 0;
        Comparable value = 0;
        if(var1.getLeftChild() != null && var1.getLeftChild().getValue().compareTo(var1.getValue()) > 0) {
            greatest = 1;
            value = var1.getLeftChild().getValue();
        }
        if(var1.getRightChild() != null && var1.getRightChild().getValue().compareTo(var1.getValue()) > 0){
            if(greatest == 1){
                if(var1.getRightChild().getValue().compareTo(value) > 0){
                    greatest = 2;
                }
            } else {
                greatest = 2;
            }
        }
        if(greatest == 1){
            swap(var1,var1.getLeftChild());
            heapify(var1.getLeftChild());
        } else if (greatest == 2){
            swap(var1,var1.getRightChild());
            heapify(var1.getRightChild());
        }
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
        if(getRoot() == null) return null;
        Comparable t=getRoot().getValue();
        swap(getRoot(),heapArray.get(size()));
        heapArray.remove(size());
        size--;
        heapify(getRoot());
        return  t;
    }

    @Override
    public void insert(Comparable var1) {
        if(var1 == null) return;
        if(heapArray.size() == 0) heapArray.add(new Node(-7,0,heapArray,this));
        INode newNode = new Node(var1, size, heapArray,this);
        heapArray.add(newNode);
        size++;
        decreaseKey(newNode);
    }

    @Override
    public void build(Collection var1) {
        heapArray.add(new Node(-7,0,heapArray,this));
        if(var1 == null) return;
        ArrayList temp= (ArrayList) var1;
        for(int i=0;i<temp.size();i++)
        {
            insert((Comparable)temp.get(i));
        }
        size = temp.size()+1;
        for(int index=heapArray.size()/2;index>0;index--)
        {
            heapify(heapArray.get(index));
        }
    }

    private void swap(INode child, INode parent){
        Comparable tempValue = parent.getValue();
        parent.setValue(child.getValue());
        child.setValue(tempValue);
    }

}
