package bk222bh;

import graphs.DFS;
import graphs.DirectedGraph;
import graphs.Node;
import graphs.TransitiveClosure;

import java.util.*;

/**
 * Class that computes the transitive closure of a given graph
 * Uses the depth first search implementation in MyDFS to make the calculation
 * */
public class MyTransitiveClosure<E> implements TransitiveClosure {

    /**
     * Computes the transitive closure
     * @param DirectedGraph dg - the directed graph to compute the closure on
     * */
    @Override
    public Map<Node<E>, Collection<Node<E>>> computeClosure(DirectedGraph dg) {
        Map<Node<E>, Collection<Node<E>>> map = new HashMap();
        Iterator<Node<E>> it = dg.iterator();
        DFS dfs = new MyDFS<Node<E>>();
        while (it.hasNext()) {
            Node<E> node = it.next();
            map.put(node, dfs.dfs(dg, node));
        }
        return map;
    }
}
