package eg.edu.alexu.csd.filestructure.redblacktree;

import com.sun.corba.se.impl.resolver.INSURLOperationImpl;
import com.sun.org.apache.regexp.internal.RE;
import javafx.util.Pair;

import javax.management.RuntimeErrorException;
import javax.swing.*;
import javax.swing.plaf.basic.BasicLookAndFeel;

public class RedBlackTree implements IRedBlackTree {
    INode root = null;
    @Override
    public INode getRoot() { return root; }

    @Override
    public boolean isEmpty() { return (root==null||root.isNull()); }

    @Override
    public void clear() {
        root=null;
    }

    @Override
    public Object search(Comparable key) {
        if(key == null) throw new RuntimeErrorException(new Error());
        if(isEmpty())return null;
        Pair<INode,INode> temp=getNodeWithKey(key);
        if(temp.getKey()==null)return null;
        return temp.getKey().getValue();
    }

    Pair<INode,INode> getNodeWithKey(Comparable key){
        INode curr=getRoot();
        if(curr==null||curr.isNull())return new Pair<>(null,null);
        if(key==null)throw new RuntimeErrorException(new Error());
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
    public boolean contains(Comparable key) {
        return search(key)!=null;
    }


    @Override
    public void insert(Comparable key, Object value) {
        if(value==null)throw new RuntimeErrorException(new Error());
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
                else if((s == null || s.getColor() == INode.BLACK)&&newNode.getParent().getRightChild()==newNode)
                {
                    if(newNode.getParent().getParent().getRightChild()==newNode.getParent())
                    {
                        newNode=newNode.getParent();
                        rotateILeft(newNode.getParent());
                        newNode.getLeftChild().setColor(INode.RED);
                        newNode.setColor(INode.BLACK);
                    }
                    else {
                        newNode=newNode.getParent();
                        rotateILeft(newNode);
                    }
                }
                else if((s== null || s.getColor() == INode.BLACK)&&newNode==newNode.getParent().getLeftChild())
                {
                    if(newNode.getParent().getParent().getRightChild()==newNode.getParent())
                    {
                        newNode=newNode.getParent();
                        rotateIRight(newNode);
                    }
                    else {
                        newNode.getParent().setColor(INode.BLACK);
                        newNode.getParent().getParent().setColor(INode.RED);
                        rotateIRight(newNode.getParent().getParent());
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
    private void changeIParent(INode parent,INode child, INode node){
        if(parent == null){
            root  = child;
            child.setParent(null);
        }
        else if(getRightOrLeft(parent, node)) {
            parent.setRightChild(child);
            if(child != null) child.setParent(parent);
        } else {
            parent.setLeftChild(child);
            if(child != null) child.setParent(parent);
        }
    }
    private void changeParent(INode parent, INode child, INode  node){
        if(parent == null || parent.getValue() == null){
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
    private void rotateILeft(INode node){
        INode child = node.getRightChild();
        INode parent = node.getParent();
        INode rlChild = node.getRightChild().getLeftChild();
        changeIParent(parent,child,node);
        child.setLeftChild(node);
        node.setParent(child);
        node.setRightChild(rlChild);
        if(rlChild != null) rlChild.setParent(node);
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
    private void rotateIRight(INode node){
        INode child = node.getLeftChild();
        INode parent = node.getParent();
        INode lrChild = node.getLeftChild().getRightChild();
        changeIParent(parent,child,node);
        child.setRightChild(node);
        node.setParent(child);
        node.setLeftChild(lrChild);
        if(lrChild!= null) lrChild.setParent(node);
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
            return successor;
        }
        return new Node();
    }

    private INode minValue(INode node) {
        INode current = node;
        while (current.getLeftChild().getValue() != null) current = current.getLeftChild();
        return current;
    }
    INode secondNode = new Node();
    private INode bstDelete(INode node) {
        boolean color = false;
        if (node.getValue() == null) return node;
        if (node.getRightChild().getValue() == null && node.getLeftChild().getValue() == null) {
            secondNode = node;
            INode newNode = new Node();
            if(node.getParent() != null) {
                changeParent(node.getParent(),newNode, node);
            } else {
                root = newNode;
            }
            return newNode;
        }
        else if (node.getRightChild().getValue() != null && node.getLeftChild().getValue() != null) {
            INode inorderSuccessor = getInOrderSuccessor(node);
            INode newNode = new Node();
            node.setValue(inorderSuccessor.getValue());
            node.setKey(inorderSuccessor.getKey());
            secondNode = inorderSuccessor;
            if(inorderSuccessor.getRightChild().getValue() == null && inorderSuccessor.getLeftChild().getValue() == null) {
                changeParent(inorderSuccessor.getParent(),newNode,inorderSuccessor);
                return newNode;
            } else {
                changeParent(inorderSuccessor.getParent(),inorderSuccessor.getRightChild(),inorderSuccessor);
                return inorderSuccessor.getRightChild();
            }
        } else {
            INode left = node.getLeftChild();
            INode right = node.getRightChild();
            secondNode = node;
            if (left.getValue() != null) {
                changeParent(node.getParent(), left, node);
                return left;
            }
            if (right.getValue() != null) {
                changeParent(node.getParent(), right, node);
                return right;
            }
        }
        return node;
    }

    private void handleCase(INode p, INode s, boolean right, boolean diffPolarity) {
            if (right) {
                if(diffPolarity) {
                    s.getLeftChild().setColor(p.getColor());
                    rotateRight(s);
                    rotateLeft(p);
                } else {
                    s.getRightChild().setColor(s.getColor());
                    s.setColor(p.getColor());
                    rotateLeft(p);
                }
            } else {
                if(diffPolarity) {
                    s.getRightChild().setColor(p.getColor());
                    rotateLeft(s);
                    rotateRight(p);
                }
                else {
                    s.getLeftChild().setColor(s.getColor());
                    s.setColor(p.getColor());
                    rotateRight(p);
                }
            }
            p.setColor(INode.BLACK);
    }

    private void fix_up_delete(INode newNode) {
            INode p = newNode.getParent();
            if(newNode == getRoot()) {
                newNode.setColor(INode.BLACK);;
                return;
            }
            INode s;
            if(getRightOrLeft(p,newNode)){
                s = p.getLeftChild();
            } else {
                s = p.getRightChild();
            }
            if(s == null){
                fix_up_delete(p);
            }
            if (s.getValue() != null) {
                if(s.getColor() == INode.BLACK) {
                    INode l = s.getLeftChild();
                    INode r = s.getRightChild();
                    if(l.getValue() != null && l.getColor() == INode.RED || r.getValue() != null && r.getColor() == INode.RED) {
                        if (getRightOrLeft(p, s)) {
                            if(r.getValue() != null && r.getColor() == INode.RED) {
                                handleCase(p, s, true, false);
                                r.setColor(INode.BLACK);
                            }
                            else if((r.getValue() == null || r.getColor() == INode.BLACK) && l.getValue() != null && l.getColor() == INode.RED){
                                handleCase(p, s, true, true);
                            }
                        } else {
                            if(l.getValue() != null && l.getColor() == INode.RED) {
                                handleCase(p, s, false, false);
                                l.setColor(INode.BLACK);
                            }
                            else if((l.getValue() == null || l.getColor() == INode.BLACK) && r.getValue() != null && r.getColor() == INode.RED){
                                handleCase(p, s, false, true);
                            }
                        }
                    } else {
                        s.setColor(INode.RED);
                        if(p.getColor() == INode.RED) p.setColor(INode.BLACK);
                        else {
                            fix_up_delete(p);
                        }
                    }
                } else {
                    p.setColor(INode.RED);
                    s.setColor(INode.BLACK);
                    if(getRightOrLeft(p,s)){
                        rotateLeft(p);
                    } else {
                        rotateRight(p);
                    }
                    fix_up_delete(newNode);
                }
            }
    }


    @Override
    public boolean delete(Comparable key) {
        if(!contains(key)) return false;
        if(key==null)throw new RuntimeErrorException(new Error());
        INode node = getNode(root, key);
        if(node.getValue() == null) return false;
        INode newNode = bstDelete(node);
        if(newNode == null) return true;
        if(secondNode.getColor() == INode.RED || newNode.getColor() == INode.RED) newNode.setColor(INode.BLACK);
        else fix_up_delete(newNode);
        return true;
    }
    
}
