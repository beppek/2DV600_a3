package bk222bh;

import graphs.Node;

import java.util.*;

/**
 * Class that represents a node in a graph
 * */
public class MyNode<E> extends Node {
    private Set<Node<E>> predecessors = new HashSet<>();
    private Set<Node<E>> successors = new HashSet<>();

    /**
     * Constructs a new node using <tt>item</tt> as key.
     *
     * @param item
     */
    protected MyNode(Object item) {
        super(item);
    }

    /**
     * Returns true if a given node is among the successors of this node
     * */
    @Override
    public boolean hasSucc(Node node) {
        return successors.contains(node);
    }

    /**
     * Returns number of outgoing connections from this node
     * */
    @Override
    public int outDegree() {
        return successors.size();
    }

    /**
     * Returns iterator to iterate over the successors of the node
     * */
    @Override
    public Iterator<Node<E>> succsOf() {
        return successors.iterator();
    }

    /**
     * Returns true of a given node is among the predecessors of this node
     * */
    @Override
    public boolean hasPred(Node node) {
        return predecessors.contains(node);
    }

    /**
     * Returns number of ingoing connections to the node
     * */
    @Override
    public int inDegree() {
        return predecessors.size();
    }

    /**
     * Returns iterator to iterate over the predecessors of this node
     * */
    @Override
    public Iterator<Node<E>> predsOf() {
        return predecessors.iterator();
    }

    /**
     * Adds a given successor to this node
     * */
    @Override
    protected void addSucc(Node succ) {
        successors.add(succ);
    }

    /**
     * Removes a given successor from the node
     * */
    @Override
    protected void removeSucc(Node succ) {
        successors.remove(succ);
    }

    /**
     * Adds a given predecessor to the node
     * */
    @Override
    protected void addPred(Node pred) {
        predecessors.add(pred);
    }

    /**
     * Removes a given predecessor from the node
     * */
    @Override
    protected void removePred(Node pred) {
        predecessors.remove(pred);
    }

    /**
     * Disconnects a node from all predecessors and successors
     * */
    @Override
    protected void disconnect() {
        //Only remove predecessors if this node is not the first as there would be none
        if (!this.isHead()) {
            List<MyNode> predecessors = new ArrayList<>(this.inDegree());
            Iterator predsIt = this.predsOf();
            while (predsIt.hasNext()) {
                predecessors.add((MyNode) predsIt.next());
            }
            for (MyNode pred : predecessors) {
                this.removePred(pred);
                pred.removeSucc(this);
            }
        }
        //Only remove successors if this node is not the last as there would be none
        if (!this.isTail()) {
            List<MyNode> successors = new ArrayList<>(this.outDegree());
            Iterator succsIt = this.succsOf();
            while (succsIt.hasNext()) {
                successors.add((MyNode) succsIt.next());
            }
            for (MyNode succs : successors) {
                this.removeSucc(succs);
                succs.removePred(this);
            }
        }
    }
}
