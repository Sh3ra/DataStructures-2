package eg.edu.alexu.csd.filestructure.redblacktree;

import javafx.util.Pair;
import org.omg.SendingContext.RunTime;

import javax.management.RuntimeErrorException;
import java.util.*;

public class TreeMap implements ITreeMap {
    IRedBlackTree tree = new RedBlackTree();

    private Map.Entry traverse(Comparable key,boolean ceil, boolean floor){
        INode node = tree.getRoot();
        INode v = node.getParent();
        INode value = tree.getRoot();
        while(node != null){
            if(ceil) if(node.getKey().compareTo(value.getKey()) >= 0) value = node;
            if(floor) if(node.getKey().compareTo(value.getKey()) <= 0) value = node;
            if (key.compareTo(node.getKey()) < 0) node = node.getLeftChild();
            if (key.compareTo(node.getKey()) > 0) node = node.getRightChild();
            if(key.compareTo(node.getKey()) == 0) break;
        }
        return new AbstractMap.SimpleEntry(value.getKey(),value.getValue());
    }
    @Override
    public Map.Entry ceilingEntry(Comparable key) {
        Map.Entry<Comparable, Object> entry = traverse(key, true, false);
        if(entry.getKey().compareTo(key) < 0) return null;
        return entry;
    }

    @Override
    public Comparable ceilingKey(Comparable key) {
        Map.Entry<Comparable, Object> entry = traverse(key, true, false);
        if(entry.getKey().compareTo(key) < 0) return null;
        return entry.getKey();
    }

    @Override
    public void clear() {
        tree.clear();
    }

    @Override
    public boolean containsKey(Comparable key) {
        return tree.contains(key);
    }

    @Override
    public boolean containsValue(Object value) {
        Queue <INode> q = new LinkedList<>();
        q.add(tree.getRoot());
        while(q.size()>0){
            INode node = q.poll();
            if(node == null) continue;
            if(node.getValue() == value) return true;
            q.add(node.getRightChild());
            q.add(node.getLeftChild());
        }
        return false;
    }

    @Override
    public Set<Map.Entry> entrySet() {
        Queue <INode> q = new LinkedList<>();
        Set <Map.Entry> s = new HashSet<>();
        q.add(tree.getRoot());
        while(q.size()>0){
            INode node = q.poll();
            if(node == null) continue;
            s.add(new AbstractMap.SimpleEntry<>(node.getKey(),node.getValue()));
            q.add(node.getRightChild());
            q.add(node.getLeftChild());
        }
        return s;
    }

    @Override
    public Map.Entry firstEntry() {
        INode node = tree.getRoot();
        while(node.getLeftChild() != null) node = node.getLeftChild();
        Map.Entry<Comparable, Object> entry = new AbstractMap.SimpleEntry<>(node.getKey(),node.getValue());
        return entry;
    }

    @Override
    public Comparable firstKey() {
        Map.Entry<Comparable, Object> entry = firstEntry();
        return entry.getKey();
    }

    @Override
    public Map.Entry floorEntry(Comparable key) {
        Map.Entry<Comparable, Object> entry = traverse(key, false, true);
        if(entry.getKey().compareTo(key) > 0) return null;
        return entry;
    }

    @Override
    public Comparable floorKey(Comparable key) {
        Map.Entry<Comparable, Object> entry = traverse(key, false, true);
        if(entry.getKey().compareTo(key) > 0) return null;
        return entry.getKey();
    }

    @Override
    public Object get(Comparable key) {
        if(!tree.contains(key)) return null;
        INode node = tree.getRoot();
        while(key != node.getKey()) {
            if (key.compareTo(node.getKey()) < 0) node = node.getLeftChild();
            if (key.compareTo(node.getKey()) > 0) node = node.getRightChild();
            if (key.compareTo(node.getKey()) == 0) break;
        }
        return node.getValue();
    }

    private  void bubblesort(ArrayList<Map.Entry> a){
        for(int i = 0;i<a.size();i++){
            for(int j = 0;j<a.size()-1;j++){
                Map.Entry<Comparable,Object> entry1 = a.get(j);
                Map.Entry<Comparable,Object> entry2 = a.get(j+1);
                if(entry2.getKey().compareTo(entry1.getKey())< 0){
                    Map.Entry<Comparable,Object> temp = a.get(j);
                    a.set(j, a.get(j+1));
                    a.set(j+1,temp);
                }
            }
        }
    }
    @Override
    public ArrayList<Map.Entry> headMap(Comparable toKey) {
        ArrayList<Map.Entry> headmap = new ArrayList<>();
        Queue <INode> q = new LinkedList<>();
        q.add(tree.getRoot());
        while(q.size()>0){
            INode node = q.poll();
            if(node == null) continue;
            if(node.getKey().compareTo(toKey) < 0)
            headmap.add(new AbstractMap.SimpleEntry(node.getKey(),node.getValue()));
            q.add(node.getLeftChild());
            q.add(node.getRightChild());
        }
        bubblesort(headmap);
        return headmap;
    }



    @Override
    public ArrayList<Map.Entry> headMap(Comparable toKey, boolean inclusive) {
        ArrayList<Map.Entry> headmap = new ArrayList<>();
        Queue <INode> q = new LinkedList<>();
        q.add(tree.getRoot());
        while(q.size()>0){
            INode node = q.poll();
            if(node == null) continue;
            if(node.getKey().compareTo(toKey) <= 0)
                headmap.add(new AbstractMap.SimpleEntry(node.getKey(),node.getValue()));
            q.add(node.getLeftChild());
            q.add(node.getRightChild());
        }
        bubblesort(headmap);
        return headmap;
    }

    @Override
    public Set keySet() {
        Queue <INode> q = new LinkedList<>();
        Set <Comparable> s = new HashSet<>();
        q.add(tree.getRoot());
        while(q.size()>0){
            INode node = q.poll();
            if(node == null) continue;
            s.add(node.getKey());
            q.add(node.getRightChild());
            q.add(node.getLeftChild());
        }
        return s;
    }

    @Override
    public Map.Entry lastEntry() {
        INode node = tree.getRoot();
        if(node == null) return null;
        while( node.getRightChild() != null) node = node.getRightChild();
        Map.Entry<Comparable, Object> entry = new AbstractMap.SimpleEntry<>(node.getKey(),node.getValue());
        return entry;
    }

    @Override
    public Comparable lastKey() {
        Map.Entry<Comparable, Object> entry = lastEntry();
        return entry.getKey();
    }

    @Override
    public Map.Entry pollFirstEntry() {
        Map.Entry<Comparable,Object> entry = firstEntry();
        tree.delete(entry.getKey());
        return  entry;
    }

    @Override
    public Map.Entry pollLastEntry() {
        Map.Entry<Comparable,Object> entry = lastEntry();
        tree.delete(entry.getKey());
        return  entry;
    }

    @Override
    public void put(Comparable key, Object value) {
        tree.insert(key,value);
    }

    @Override
    public void putAll(Map map) {
        Iterator it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry entry = (Map.Entry) it.next();
            tree.insert((Comparable) entry.getKey(),entry.getValue());
        }
    }

    @Override
    public boolean remove(Comparable key) {
        return tree.delete(key);
    }

    @Override
    public int size() {
        int size = 0;
        Queue <INode> q = new LinkedList<>();
        q.add(tree.getRoot());
        while(q.size()>0){
            INode node = q.poll();
            if(node == null) continue;
            size++;
            q.add(node.getRightChild());
            q.add(node.getLeftChild());
        }
        return size;
    }

    @Override
    public Collection values() {
        ArrayList<Object> obj = new ArrayList<>();
        Queue <INode> q = new LinkedList<>();
        q.add(tree.getRoot());
        while(q.size()>0){
            INode node = q.poll();
            if(node == null) continue;
            obj.add(node.getValue());
            q.add(node.getLeftChild());
            q.add(node.getRightChild());
        }
        return obj;
    }
}
