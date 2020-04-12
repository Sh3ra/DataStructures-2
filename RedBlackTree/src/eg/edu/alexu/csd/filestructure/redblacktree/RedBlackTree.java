package eg.edu.alexu.csd.filestructure.redblacktree;

import javafx.util.Pair;

public class RedBlackTree implements IRedBlackTree {
    INode root;
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
        return getNodeWithKey(key).getKey().getValue();
    }

    Pair<INode,INode> getNodeWithKey(Comparable key){
        INode curr=getRoot();
        if(curr==null)return new Pair<>(null,null);
        INode prev=curr;
        while (curr!=null&&!curr.isNull()&&curr.getKey()!=key)
        {
            prev=curr;
            if(key.compareTo(curr.getKey())>0)
            {
                curr=curr.getRightChild();
            }
            else{
                curr=curr.getLeftChild();
            }
        }
        return new Pair<INode,INode>(curr,prev);
    }

    @Override
    public boolean contains(Comparable key) { return search(key)!=null; }

    public static void main(String[] args) {
        RedBlackTree redBlackTree=new RedBlackTree();
        redBlackTree.insert(409,1);
        redBlackTree.insert(6287,1);
        redBlackTree.insert(8166,1);
    }
    @Override
    public void insert(Comparable key, Object value) {
        Pair<INode,INode> temp=getNodeWithKey(key);
        INode newNode=temp.getKey();
        INode p = temp.getValue();
        if(newNode!=null&&!newNode.isNull())
        {
            newNode.setValue(value);
            return;
        }
        else newNode =new Node();
        newNode.setKey(key);
        newNode.setValue(value);
        if (!isEmpty()){
            if(p.getKey().compareTo(key)>0)p.setLeftChild(newNode);
            else p.setRightChild(newNode);
            newNode.setParent(p);
        }
        rebalancedInsert(newNode);
        //if(newNode.getLeftChild()==null&&newNode.getRightChild()==null)newNode.setColor(INode.BLACK);
    }

    private void rebalancedInsert(INode newNode) {
        if (isEmpty())
        {
            this.root=newNode;
            root.setColor(INode.BLACK);
        }
        /*
        else {
            if(newNode==getRoot()&&newNode.getColor()==INode.BLACK)
            {
                return;
            }
            newNode.setColor(INode.RED);
            INode y=newNode.getParent();
            //if(y==getRoot())return;
            INode z=y.getParent();
            if(y.getColor())
            {
                INode s;
                boolean yIsLeft=false;
                if(y==z.getLeftChild())
                    yIsLeft=true;
                if(yIsLeft)s=z.getRightChild();
                else s=z.getLeftChild();
                if(s!=null&&s.getColor()==INode.BLACK)
                {
                    y.setParent(z.getParent());
                    INode p=z.getParent();
                    z.setParent(y);
                    if(p!=null) {
                        if (p.getLeftChild() == z) {
                            p.setLeftChild(y);
                        } else p.setRightChild(y);
                    }
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
                    if(s!=null)s.setColor(INode.BLACK);
                    z.setColor(INode.RED);
                    rebalancedInsert(z);
                }
            }
        }
        */
    }

    @Override
    public boolean delete(Comparable key) {
        return false;
    }
}
