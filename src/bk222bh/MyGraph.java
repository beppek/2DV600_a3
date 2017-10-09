package bk222bh;

import graphs.DirectedGraph;
import graphs.Node;

import java.util.*;

public class MyGraph<E> implements DirectedGraph {
    private List<Node<E>> nodes = new LinkedList<>();
    private Map<E, MyNode<E>> nodeMap = new HashMap<>();
    private Set<Node<E>> heads = new HashSet<>();
    private Set<Node<E>> tails = new HashSet<>();

    public MyGraph() {

    }

    @Override
    public Node addNodeFor(Object item) {
        if (item == null) {
            throw new RuntimeException("Item can not be null");
        }
        if (nodeMap.containsValue(item)) {
            return nodeMap.get(item);
        } else {
            Node newNode = new MyNode(item);
            nodes.add(newNode);
            return newNode;
        }
    }

    @Override
    public Node getNodeFor(Object item) {
        if (item == null) {
            throw new RuntimeException("Item can not be null");
        } else if (!nodes.contains(item)) {
            throw new RuntimeException("Item doesn't exist");
        }
        return null;
    }

    @Override
    public boolean addEdgeFor(Object from, Object to) {
        if (from == null || to == null) {
            throw new RuntimeException("To and from can not be null");
        }
        MyNode<E> src = (MyNode<E>) addNodeFor(from);
        MyNode<E> tgt = (MyNode<E>) addNodeFor(to);
        if (src.hasSucc(tgt)) {
            return false;
        } else {
            src.addSucc(tgt);
            tgt.addPred(src);

            tails.remove(src);
            heads.remove(tgt);
            return true;
        }
    }

    @Override
    public boolean containsNodeFor(Object item) {
        if (item == null) {
            throw new RuntimeException("Item can't be null");
        }
        return false;
    }

    @Override
    public int nodeCount() {
        return 0;
    }

    @Override
    public Iterator<Node<E>> iterator() {
        return nodes.iterator();
    }

    @Override
    public Iterator<Node<E>> heads() {
        return heads.iterator();
    }

    @Override
    public int headCount() {
        return heads.size();
    }

    @Override
    public Iterator<Node<E>> tails() {
        return tails.iterator();
    }

    @Override
    public int tailCount() {
        return tails.size();
    }

    @Override
    public List allItems() {
        return null;
    }

    @Override
    public int edgeCount() {
        return 0;
    }

    @Override
    public void removeNodeFor(Object item) {
        if (item == null || !nodes.contains(item)) {
            throw new RuntimeException("Item can't be null");
        }
    }

    @Override
    public boolean containsEdgeFor(Object from, Object to) {
        if (from == null || to == null) {
            throw new RuntimeException("To and from can't be null");
        }
        return false;
    }

    @Override
    public boolean removeEdgeFor(Object from, Object to) {
        if (from == null || to == null ) {
            throw new RuntimeException("To and from can't be null");
        }
        return false;
    }
}
