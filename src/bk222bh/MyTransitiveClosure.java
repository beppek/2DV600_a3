package bk222bh;

import graphs.DFS;
import graphs.DirectedGraph;
import graphs.Node;
import graphs.TransitiveClosure;

import java.util.*;

public class MyTransitiveClosure<E> implements TransitiveClosure {
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
