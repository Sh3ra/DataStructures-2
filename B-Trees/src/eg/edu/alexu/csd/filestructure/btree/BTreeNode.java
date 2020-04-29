package eg.edu.alexu.csd.filestructure.btree;

import java.util.List;

public class BTreeNode implements IBTreeNode {
    @Override
    public int getNumOfKeys() {
        return 0;
    }

    @Override
    public void setNumOfKeys(int numOfKeys) {

    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public void setLeaf(boolean isLeaf) {

    }

    @Override
    public List getKeys() {
        return null;
    }

    @Override
    public void setKeys(List keys) {

    }

    @Override
    public List getValues() {
        return null;
    }

    @Override
    public void setValues(List values) {

    }

    @Override
    public List<IBTreeNode> getChildren() {
        return null;
    }

    @Override
    public void setChildren(List children) {

    }
}
