package bk222bh;

import graphs.Node;

import java.util.*;

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

    @Override
    public boolean hasSucc(Node node) {
        return successors.contains(node);
    }

    @Override
    public int outDegree() {
        return successors.size();
    }

    @Override
    public Iterator<Node<E>> succsOf() {
        return successors.iterator();
    }

    @Override
    public boolean hasPred(Node node) {
        return predecessors.contains(node);
    }

    @Override
    public int inDegree() {
        return predecessors.size();
    }

    @Override
    public Iterator<Node<E>> predsOf() {
        return predecessors.iterator();
    }

    @Override
    protected void addSucc(Node succ) {
        successors.add(succ);
    }

    @Override
    protected void removeSucc(Node succ) {
        successors.remove(succ);
    }

    @Override
    protected void addPred(Node pred) {
        predecessors.add(pred);
    }

    @Override
    protected void removePred(Node pred) {
        predecessors.remove(pred);
    }

    @Override
    protected void disconnect() {
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
