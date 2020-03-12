package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;
import java.util.Collection;

public class Heap implements IHeap {
    private ArrayList<INode> heapArray = new ArrayList<>(2);
    @Override
    public INode getRoot() {
        if(heapArray.size()>2) {
            return heapArray.get(1);
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

    }

    private void swap(INode child, INode parent){
        Comparable tempValue = parent.getValue();
        parent.setValue(child.getValue());
        child.setValue(tempValue);
    }

}
