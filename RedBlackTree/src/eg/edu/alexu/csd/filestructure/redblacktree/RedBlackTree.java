package eg.edu.alexu.csd.filestructure.redblacktree;

import com.sun.corba.se.impl.resolver.INSURLOperationImpl;
import com.sun.org.apache.regexp.internal.RE;
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
        Pair<INode,INode> temp=getNodeWithKey(key);
        if(temp.getKey()==null)return null;
        return temp.getKey().getValue();
    }

    Pair<INode,INode> getNodeWithKey(Comparable key){
        INode curr=getRoot();
        if(curr==null)return new Pair<>(null,null);
        INode prev=curr;
        while (curr!=null&&!curr.isNull()&&curr.getKey().compareTo(key)!=0)
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
        newNode.setRightChild(new Node());
        newNode.setLeftChild(new Node());
        rebalancedInsert(newNode);
        //if(newNode.getLeftChild()==null&&newNode.getRightChild()==null)newNode.setColor(INode.BLACK);
    }

    private void rebalancedInsert(INode newNode) {
        if (isEmpty())
        {
            this.root=newNode;
            root.setColor(INode.BLACK);
        }
        else {
            newNode.setColor(INode.RED);
            while (newNode.getParent()!=null&&newNode.getParent().getColor()==INode.RED&&newNode.getColor()==INode.RED)
            {
                INode s;
                if(newNode.getParent().getParent().getRightChild()==newNode.getParent())
                {
                    s=newNode.getParent().getParent().getLeftChild();
                }
                else s=newNode.getParent().getParent().getRightChild();
                if(s!=null&&s.getColor()==INode.RED)
                {
                    s.setColor(INode.BLACK);
                    newNode.getParent().setColor(INode.BLACK);
                    newNode.getParent().getParent().setColor(INode.RED);
                    newNode=newNode.getParent().getParent();
                }
                else if((s.getValue() == null || s.getColor() == INode.BLACK)&&newNode.getParent().getRightChild()==newNode)
                {
                    if(newNode.getParent().getParent().getRightChild()==newNode.getParent())
                    {
                        newNode=newNode.getParent();
                        rotateLeft(newNode.getParent());
                        newNode.getLeftChild().setColor(INode.RED);
                        newNode.setColor(INode.BLACK);
                    }
                    else {
                        newNode=newNode.getParent();
                        rotateLeft(newNode);
                        //newNode.getLeftChild().setColor(INode.RED);
                        //newNode.setColor(INode.BLACK);
                    }
                }
                else if((s.getValue() == null || s.getColor() == INode.BLACK)&&newNode==newNode.getParent().getLeftChild())
                {
                    if(newNode.getParent().getParent().getRightChild()==newNode.getParent())
                    {
                        newNode=newNode.getParent();
                        rotateRight(newNode);
                    }
                    else {
                        newNode.getParent().setColor(INode.BLACK);
                        newNode.getParent().getParent().setColor(INode.RED);
                        rotateRight(newNode.getParent().getParent());
                    }
                }
            }
            getRoot().setColor(INode.BLACK);
        }
    }


    private boolean getRightOrLeft(INode parent, INode node) {
        if (parent.getRightChild() == node) return true;
        return false;
    }

    private void changeParent(INode parent, INode child, INode  node){
        if(parent.getValue() == null){
            root  = child;
            child.setParent(null);
        }
        else if(getRightOrLeft(parent, node)) {
            parent.setRightChild(child);
            child.setParent(parent);
        } else {
            parent.setLeftChild(child);
            child.setParent(parent);
        }
    }

    private void rotateLeft(INode node) {
        INode child = node.getRightChild();
        INode parent = node.getParent();
        INode rlChild = node.getRightChild().getLeftChild();
        changeParent(parent,child,node);
        child.setLeftChild(node);
        node.setParent(child);
        node.setRightChild(rlChild);
        rlChild.setParent(node);
    }

    private void rotateRight(INode node) {
        INode child = node.getLeftChild();
        INode parent = node.getParent();
        INode lrChild = node.getLeftChild().getRightChild();
        changeParent(parent,child,node);
        child.setRightChild(node);
        node.setParent(child);
        node.setLeftChild(lrChild);
        lrChild.setParent(node);
    }

    private INode getNode (INode node, Comparable key) {
        while(node.getValue() != null){
            if (key.compareTo(node.getKey()) < 0) node = node.getLeftChild();
            if (key.compareTo(node.getKey()) > 0) node = node.getRightChild();
            if (key.compareTo(node.getKey()) == 0) break;
        }
        return node;
    }

    private INode getInOrderSuccessor(INode node) {
        if (node.getRightChild().getValue() != null) {
            INode successor = minValue(node.getRightChild());
            if(successor.getValue() != null) successor.getParent().setLeftChild(null);
            return successor;
        }
        return new Node();
    }

    private INode minValue(INode node) {
        INode current = node;
        while (current.getLeftChild().getValue() != null) current = current.getLeftChild();
        return current;
    }

    private INode bstDelete(INode node, Comparable key) {
        if (node.getValue() == null) return new Node();
        if (node.getRightChild().getValue() == null && node.getLeftChild().getValue() == null) {
            if(node.getParent() != null) changeParent(node.getParent(),new Node(), node);
            node = new Node();
        }
        else if (node.getRightChild().getValue() != null && node.getLeftChild().getValue() != null) {
            INode inorderSuccessor = getInOrderSuccessor(node);
            changeParent(node.getParent(), inorderSuccessor, node);
            if(inorderSuccessor != node.getLeftChild())
            inorderSuccessor.setLeftChild(node.getLeftChild());
            if(inorderSuccessor != node.getRightChild())
            inorderSuccessor.setRightChild(node.getRightChild());
            node = inorderSuccessor;
        } else {
            INode left = node.getLeftChild();
            INode right = node.getRightChild();
            if (left.getValue() != null) {
                changeParent(node.getParent(), left, node);
                node = left;
            }
            if (right.getValue() != null) {
                changeParent(node.getParent(), right, node);
                node = right;
            }
        }
        return node;
    }

    private void handleCase(INode p, INode s, boolean right, boolean diffPolarity){
            if (right) {
                if(diffPolarity) rotateRight(s);
                rotateLeft(p);
            } else {
                if(diffPolarity) rotateLeft(s);
                rotateRight(p);
            }
    }

    private void fix_up_delete(INode newNode, INode p) {
            if(newNode == getRoot()) {
                newNode.setColor(INode.BLACK);
                return;
            }
            INode s;
            if(getRightOrLeft(p,newNode)){
                s = p.getLeftChild();
            } else {
                s = p.getRightChild();
            }
            if(s.getValue() != null) {
                if(s.getColor() == INode.BLACK) {
                    INode l = s.getLeftChild();
                    INode r = s.getRightChild();
                    if(l.getValue() != null && l.getColor() == INode.RED || r.getValue() != null && r.getColor() == INode.RED) {
                        if (getRightOrLeft(p, s)) {
                            if(r.getValue() != null && r.getColor() == INode.RED) {
                                handleCase(p, s, true, false);
                            }
                            else if((r.getValue() == null || r.getColor() == INode.BLACK) && l.getValue() != null && l.getColor() == INode.RED){
                                handleCase(p, s, true, true);
                            }
                        } else {
                            if(l.getValue() != null && l.getColor() == INode.RED) {
                                handleCase(p, s, false, false);
                            }
                            else if((l.getValue() == null || l.getColor() == INode.BLACK) && r.getValue() != null && r.getColor() == INode.RED){
                                handleCase(p, s, false, true);
                            }
                        }
                    } else {
                        if(p.getColor() == INode.RED) p.setColor(INode.BLACK);
                        else {
                            s.setColor(INode.RED);
                            fix_up_delete(p,p.getParent());
                        }
                    }
                } else {
                    if(getRightOrLeft(p,s)){
                        rotateLeft(p);
                        p.setColor(INode.RED);
                        s.setColor(INode.BLACK);
                    } else {
                        rotateRight(p);
                        p.setColor(INode.RED);
                        s.setColor(INode.BLACK);
                    }
                    fix_up_delete(newNode,p);
                }
        }
    }


    @Override
    public boolean delete(Comparable key) {
        INode node = getNode(root, key);
        if(node.getValue() == null) return false;
        INode newNode = bstDelete(node,key);
        if( newNode.getValue() != null && (node.getColor() == INode.RED || newNode.getColor() == INode.RED)) newNode.setColor(INode.BLACK);
        else fix_up_delete(newNode,node.getParent());
        return true;
    }

    public static void main(String[] args) {
        RedBlackTree redBlackTree=new RedBlackTree();
        redBlackTree.insert(311,1);
        redBlackTree.insert(6887,1);
        redBlackTree.insert(7095,1);
        redBlackTree.insert(4023,1);
        redBlackTree.insert(2482,1);
        redBlackTree.delete(311);
        redBlackTree.delete(6887);
        redBlackTree.delete(7095);
        redBlackTree.delete(4023);
        redBlackTree.delete(2482);
        redBlackTree=null;
    }
}
