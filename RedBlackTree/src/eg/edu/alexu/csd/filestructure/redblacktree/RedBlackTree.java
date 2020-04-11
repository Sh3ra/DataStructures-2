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
        if(isEmpty())return null;
        return getNodeWithKey(key).getValue();
    }

    INode getNodeWithKey(Comparable key){
        INode curr=getRoot();
        while (!curr.isNull()&&curr.getKey()!=key)
        {
            if(key.compareTo(curr.getKey())>0)
            {
                curr=curr.getRightChild();
            }
            else
                curr=curr.getLeftChild();
        }
        return curr;
    }

    @Override
    public boolean contains(Comparable key) { return search(key)!=null; }

    @Override
    public void insert(Comparable key, Object value) {
        INode newNode=getNodeWithKey(key);
        if(!newNode.isNull())
        {
            newNode.setValue(value);
            return;
        }
        newNode.setKey(key);
        newNode.setValue(value);
        if (!isEmpty()){

        }
        rebalancedInsert(newNode);
    }

    private void rebalancedInsert(INode newNode) {
        if (isEmpty())
        {
            this.root=newNode;
            root.setColor(INode.BLACK);
        }
        else {
            newNode.setColor(INode.RED);
            INode y=newNode.getParent();
            INode z=y.getParent();
            if(y.getColor())
            {
                INode s;
                boolean yIsLeft=false;
                if(y==z.getLeftChild())
                    yIsLeft=true;
                if(yIsLeft)s=z.getRightChild();
                else s=z.getLeftChild();
                if(s.getColor()==INode.BLACK)
                {
                    y.setParent(z.getParent());
                    INode p=z.getParent();
                    z.setParent(y);
                    if(p.getLeftChild()==z){
                        p.setLeftChild(y);
                    }
                    else p.setRightChild(y);
                    INode t;
                    if(newNode==y.getLeftChild()){
                        t=y.getRightChild();
                        t.setParent(z);
                        y.setRightChild(z);
                    }
                    else {
                        t=y.getLeftChild();
                        t.setParent(z);
                        y.setLeftChild(z);
                    }
                    if(yIsLeft)z.setLeftChild(t);
                    else z.setRightChild(t);
                    y.setColor(INode.BLACK);
                    z.setColor(INode.RED);
                }
                else{
                    y.setColor(INode.BLACK);
                    s.setColor(INode.BLACK);
                    z.setColor(INode.RED);
                    rebalancedInsert(z);
                }
            }
        }
    }

    @Override
    public boolean delete(Comparable key) {
        return false;
    }
}
