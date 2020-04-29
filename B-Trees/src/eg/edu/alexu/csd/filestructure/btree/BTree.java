package eg.edu.alexu.csd.filestructure.btree;

import java.util.List;

public class BTree implements IBTree{
    @Override
    public int getMinimumDegree() {
        return 0;
    }

    @Override
    public IBTreeNode getRoot() {
        return null;
    }

    @Override
    public void insert(Comparable key, Object value) {

    }

    @Override
    public Object search(Comparable key) {
        return null;
    }
    private IBTreeNode deleteSearch(Comparable key){
        IBTreeNode res=null;
        IBTreeNode root=getRoot();
        boolean done=false;
        while (!done){
            boolean changed=false;
            List keys=root.getKeys();
            List children =root.getChildren();
            for(int i=0;i<root.getNumOfKeys();i++)
            {
                if(key.compareTo(keys.get(i))==0)
                {
                    res=root;
                    done=true;
                    break;
                }
                else {
                    if (key.compareTo(keys.get(i)) < 0) {
                        if(children!=null)root= (IBTreeNode) children.get(i);
                        changed=true;
                        break;
                    }
                }
            }
            if(children==null)
            {
                break;
            }
            if(!changed)
            {
                root= (IBTreeNode) children.get(children.size()-1);
            }
        }
        return res;
    }
    @Override
    public boolean delete(Comparable key) {
        IBTreeNode node=deleteSearch(key);
        if(node.getNumOfKeys()>getMinimumDegree()-1)
        {
            List keys=node.getKeys();
            for(int i=0;i<node.getNumOfKeys();i++)
            {
                if(key.compareTo(keys.get(i))==0)
                {
                    keys.remove(i);
                    node.setKeys(keys);
                }
            }
        }
        return false;
    }
}
