package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Heap implements IHeap {
    private int size=0;
    private ArrayList<INode> heapArray = new ArrayList<>();
    private ArrayList<INode> sortedHeap = new ArrayList<>();
    @Override
    public INode getRoot() {
        if(size()>0)
        return heapArray.get(0);
        else return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void heapify(INode var1) {
        if((var1.getLeftChild()==null&&var1.getRightChild()==null)||(var1==null))
            return;
        INode right=var1.getRightChild();
        if(sortedHeap.contains(right))right=null;
        INode left=var1.getLeftChild();
        if(sortedHeap.contains(left))left=null;
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
        Comparable t=getRoot().getValue();
        swap(getRoot(),heapArray.get(size()-1));
        sortedHeap.add(heapArray.get(size()-1));
        size--;
        if(size()>0)
            heapify(getRoot());
        return  t;
    }

    @Override
    public void insert(Comparable var1) {
        INode newNode = new Node(var1, heapArray.size()-1, heapArray);
        heapArray.add(newNode);
        size++;
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
        size=heapArray.size();
        //heapArray=(ArrayList<INode>)var1;
        for(int index=size()/2;index>=0;index--)
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
        Heap heap=new Heap();
        heap.insert(7);
        heap.insert(8);
        heap.insert(10);
        heap.insert(7);
        heap.insert(1);
        heap.insert(15);
        heap.insert(20);
        heap.insert(8);
        int s=heap.size();
        for (int i = 0; i <heap.heapArray.size(); i++){
            System.out.println(heap.heapArray.get(i).getValue());
        }
    }
    private void swap(INode child, INode parent){
        Comparable tempValue = parent.getValue();
        parent.setValue(child.getValue());
        child.setValue(tempValue);
    }

}
