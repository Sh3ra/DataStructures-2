package eg.edu.alexu.csd.filestructure.redblacktree;

public class Node implements INode {
    INode parent;
    INode rightChild;
    INode leftChild;
    Comparable key=null;
    Object value=null;
    boolean color=true;//red=true black=false
    @Override
    public void setParent(INode parent) {
        this.parent=parent;
    }

    @Override
    public INode getParent() {
        return parent;
    }

    @Override
    public void setLeftChild(INode leftChild) {
        this.leftChild=leftChild;
    }

    @Override
    public INode getLeftChild() {
        return leftChild;
    }

    @Override
    public void setRightChild(INode rightChild) {
        this.rightChild=rightChild;
    }

    @Override
    public INode getRightChild() {
        return rightChild;
    }

    @Override
    public Comparable getKey() {
        return key;
    }

    @Override
    public void setKey(Comparable key) {
        this.key=key;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        this.value=value;
    }

    @Override
    public boolean getColor() {
        return color;
    }

    @Override
    public void setColor(boolean color) {
        this.color=color;
    }

    @Override
    public boolean isNull() { return value==null; }
}
