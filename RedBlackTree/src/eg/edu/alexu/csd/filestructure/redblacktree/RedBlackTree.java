package eg.edu.alexu.csd.filestructure.redblacktree;

public class RedBlackTree implements IRedBlackTree {
    INode root=null;
    @Override
    public INode getRoot() { return root; }

    @Override
    public boolean isEmpty() { return root==null; }

    @Override
    public void clear() {
        root=null;
    }

    @Override
    public Object search(Comparable key) {

        return null;
    }

    @Override
    public boolean contains(Comparable key) { return search(key)!=null; }

    @Override
    public void insert(Comparable key, Object value) {

    }

    @Override
    public boolean delete(Comparable key) {
        return false;
    }
}
