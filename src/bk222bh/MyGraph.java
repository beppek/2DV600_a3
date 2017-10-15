package bk222bh;

import graphs.DirectedGraph;
import graphs.Node;

import java.util.*;

/**
 * Class that implements DirectedGraph interface.
 * */
public class MyGraph<E> implements DirectedGraph {
    private Map<E, MyNode<E>> nodeMap;
    private Set<Node<E>> heads;
    private Set<Node<E>> tails;

    public MyGraph() {
        nodeMap = new HashMap<>();
        heads = new HashSet<>();
        tails = new HashSet<>();
    }

    /**
     * Adds a node for a given item
     * @throws RuntimeException if item is null
     * Checks if item is already in the graph before adding, if already in graph returns existing
     * @param item - item to add to the graph
     * */
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

    /**
     * Gets the node for a given item
     * @throws RuntimeException if item is null or does not exist in the graph
     * @param item - item to get the node for
     * */
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

    /**
     * Adds edge from one node to another
     * @throws RuntimeException if from or to is null
     * @return boolean if adding edge was successful or not
     * */
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

    /**
     * Checks if there is a node for a given item
     * @throws RuntimeException if item is null
     * @return boolean if graph contains node or not
     * */
    @Override
    public boolean containsNodeFor(Object item) {
        if (item == null) {
            throw new RuntimeException("Item can't be null");
        }
        return nodeMap.get(item) != null;
    }

    /**
     * Returns number of nodes in the graph
     * */
    @Override
    public int nodeCount() {
        return nodeMap.size();
    }

    /**
     * Return an iterator to iterate over the entire graph
     * */
    @Override
    public Iterator<Node<E>> iterator() {
        return new NodeIterator();
    }

    /**
     * Return iterator to iterate over the heads in the graph
     * */
    @Override
    public Iterator<Node<E>> heads() {
        return heads.iterator();
    }

    /**
     * Return number of head nodes in the graph
     * */
    @Override
    public int headCount() {
        return heads.size();
    }

    /**
     * Return iterator to iterate over the number of tails in the graph
     * */
    @Override
    public Iterator<Node<E>> tails() {
        return tails.iterator();
    }

    /**
     * Return number of tails in the graph
     * */
    @Override
    public int tailCount() {
        return tails.size();
    }

    /**
     * Returns all items in the graph as list
     * */
    @Override
    public List allItems() {
        List<E> allItems = new ArrayList<>(nodeMap.keySet());
        return allItems;
    }

    /**
     * Returns the number of edges in the graph
     * */
    @Override
    public int edgeCount() {
        int edgeCount = 0;
        for(MyNode<E> node : nodeMap.values()) {
            edgeCount += node.inDegree();
        }
        return edgeCount;
    }

    /**
     * Removes the node for a given object
     * @throws RuntimeException if item is null
     * */
    @Override
    public void removeNodeFor(Object item) {
        if (item == null || !nodeMap.containsKey(item)) {
            throw new RuntimeException("Item can't be null");
        }
        MyNode node = nodeMap.get(item);
        node.disconnect();
        nodeMap.remove(item);
    }

    /**
     * Checks if there is an edge from two given nodes
     * @throws RuntimeException if to or from is null
     * */
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

    /**
     * Removes edge from two given nodes
     * @throws RuntimeException if to or from is null
     * */
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
