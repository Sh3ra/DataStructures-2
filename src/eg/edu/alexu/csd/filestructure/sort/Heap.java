package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Heap implements IHeap {
    private ArrayList<INode> heapArray = new ArrayList<>(2);
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
        if(var1.getLeftChild()==null&&var1.getRightChild()==null)
            return;
        INode right=var1.getRightChild();
        INode left=var1.getLeftChild();
        INode tempN;
        if(left!=null&&var1.getValue().compareTo(left.getValue())<0)
        {
            tempN=left;
        }
        else tempN=var1;
        if(right!=null&&tempN.getValue().compareTo(right.getValue())<0)
        {
            tempN=right;
        }
        if(tempN!=var1){

            swap(tempN,var1);
            heapify(tempN);
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
        return (Comparable) getRoot();
    }

    @Override
    public void insert(Comparable var1) {
        INode newNode = new Node(var1, heapArray.size()-1, heapArray);
        heapArray.add(newNode);
        decreaseKey(newNode);
    }

    @Override
    public void build(Collection var1) {
        ArrayList temp= (ArrayList) var1;
        for(int i=0;i<temp.size();i++)
        {
            INode iNode=new Node((Comparable) temp.get(i),i,heapArray);
            heapArray.add(iNode);
        }
        //heapArray=(ArrayList<INode>)var1;
        for(int index=heapArray.size()/2;index>0;index--)
        {
            heapify(heapArray.get(index));
        }
        int index=0;
      /*
      while (index<heapArray.size())
        {
            INode p=heapArray.get(index);
            INode r=p.getRightChild();
            INode l=p.getLeftChild();
            index++;
        }
        */
    }

    public static void main(String[] args) {
        ArrayList arr=new ArrayList();
        arr.add(2);
        arr.add(8);
        arr.add(14);
        arr.add(3);
        arr.add(9);
        arr.add(1);
        arr.add(16);
        arr.add(10);
        arr.add(4);
        arr.add(7);
        Heap heap=new Heap();
        heap.build(arr);
    }
    private void swap(INode child, INode parent){
        Comparable tempValue = parent.getValue();
        parent.setValue(child.getValue());
        child.setValue(tempValue);
    }

}
