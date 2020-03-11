package eg.edu.alexu.csd.filestructure.sort;

public interface INode<T extends Comparable<T>> {
    INode<T> getLeftChild();

    INode<T> getRightChild();

    INode<T> getParent();

    T getValue();

    void setValue(T var1);
}
