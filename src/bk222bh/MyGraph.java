package bk222bh;

import graphs.DirectedGraph;
import graphs.Node;

import java.util.*;

public class MyGraph<E> implements DirectedGraph {
    private Map<E, MyNode<E>> nodeMap;
    private Set<Node<E>> heads;
    private Set<Node<E>> tails;

    public MyGraph() {
        nodeMap = new HashMap<>();
        heads = new HashSet<>();
        tails = new HashSet<>();
    }

    @Override
    public Node addNodeFor(Object item) {
        if (item == null) {
            throw new RuntimeException("Item can not be null");
        }
        if (nodeMap.containsKey((E)item)) {
            return nodeMap.get(item);
        } else {
            MyNode newNode = new MyNode(item);
            nodeMap.put((E)item,newNode);
            heads.add(newNode);
            tails.add(newNode);
            return newNode;
        }
    }

    @Override
    public Node getNodeFor(Object item) {
        if (item == null) {
            throw new RuntimeException("Item can not be null");
        } else if (!nodeMap.containsKey((E)item)) {
            throw new RuntimeException("Item doesn't exist");
        }
        MyNode<E> node = nodeMap.get(item);
        return node;
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
        return nodeMap.get(item) != null;
    }

    @Override
    public int nodeCount() {
        return nodeMap.size();
    }

    @Override
    public Iterator<Node<E>> iterator() {
        return new NodeIterator();
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
        List<E> allItems = new ArrayList<E>(nodeMap.keySet());
        return allItems;
    }

    @Override
    public int edgeCount() {
        int edgeCount = 0;
        for(MyNode<E> node : nodeMap.values()) {
            edgeCount += node.inDegree();
        }
        return edgeCount;
    }

    @Override
    public void removeNodeFor(Object item) {
        if (item == null || !nodeMap.containsKey(item)) {
            throw new RuntimeException("Item can't be null");
        }
        MyNode node = nodeMap.get(item);
        node.disconnect();
        nodeMap.remove(item);
    }

    @Override
    public boolean containsEdgeFor(Object from, Object to) {
        if (from == null || to == null) {
            throw new RuntimeException("To and from can't be null");
        }
        if (!containsNodeFor(from) || !containsNodeFor(to)) {
            return false;
        }
        MyNode<E> src = (MyNode<E>) addNodeFor(from);
        MyNode<E> tgt = (MyNode<E>) addNodeFor(to);

        return src.hasSucc(tgt);
    }

    @Override
    public boolean removeEdgeFor(Object from, Object to) {
        if (from == null || to == null ) {
            throw new RuntimeException("To and from can't be null");
        }

        if (!containsNodeFor(from) || !containsNodeFor(to)) {
            return false;
        }
        MyNode<E> src = (MyNode<E>) addNodeFor(from);
        MyNode<E> tgt = (MyNode<E>) addNodeFor(to);
        if (!src.hasSucc(tgt)) {
            return false;
        } else {
            src.removeSucc(tgt);
            tgt.removePred(src);

            if (src.outDegree() == 0) {
                tails.add(src);
            }
            if (tgt.inDegree() == 0) {
                heads.add(tgt);
            }

            return true;
        }
    }

    /**
     * Private inner class used by iterator()
     * */
    class NodeIterator implements Iterator<Node<E>> {
        private Iterator it = nodeMap.entrySet().iterator();

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public Node next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException();
            }
            Map.Entry entry = (Map.Entry)it.next();
            Node<E> node = (Node<E>) entry.getValue();
            return node;
        }
    }
}
