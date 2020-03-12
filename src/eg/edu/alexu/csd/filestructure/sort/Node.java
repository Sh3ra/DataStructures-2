package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;

public class Node <T extends Comparable<T>> implements INode {
    private Comparable value;
    private int index;
    private ArrayList<INode> heapArray;
    public Node (Comparable value, int index, ArrayList<INode> heapArray){
        this.value = value;
        this.index = index;
        this.heapArray = heapArray;
    }
    @Override
    public INode getLeftChild() {
        if(heapArray.size()>index<<1) {
            return heapArray.get(index << 1);
        }
        return null;
    }

    public int getIndex(){
        return index;
    }

    @Override
    public INode getRightChild() {
        if(heapArray.size()>((index<<1) + 1)) {
            return heapArray.get(((index << 1) + 1));
        }
        return null;
    }


    @Override
    public INode getParent() {
        if(index != 1) {
            return heapArray.get(index >> 1);
        }
        return null;
    }

    @Override
    public Comparable getValue() {
        return value;
    }

    @Override
    public void setValue(Comparable var1) {
        value = var1;
    }
}
