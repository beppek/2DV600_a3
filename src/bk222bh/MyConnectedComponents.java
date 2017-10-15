package bk222bh;

import graphs.ConnectedComponents;
import graphs.DirectedGraph;
import graphs.Node;

import java.util.*;

/**
 * Class to compute the connected components of a directed graph
 * */
public class MyConnectedComponents<E> implements ConnectedComponents {
    private Set<Node<E>> nodes;
    private Set<Node<E>> visited;

    /**
     * Computes the connected components of the given graph dg
     * Iterates over all nodes in the graph and returns a collection of collections of all connected components
     * @return Collection of Collections of Nodes that are within the same connected closure
     * */
    @Override
    public Collection<Collection<Node<E>>> computeComponents(DirectedGraph dg) {
        Collection<Collection<Node<E>>> partitions = new HashSet<>();
        nodes = new HashSet<>();
        visited = new HashSet<>();

        Iterator<Node<E>> it = dg.iterator();
        while (it.hasNext()) {
            Node<E> node = it.next();
            if (!visited.contains(node)) {
                computeComponents(node);
                partitions.add(nodes);
                nodes = new HashSet<>();
            }
        }
        return partitions;
    }

    /**
     * Private helper method to find all connected components.
     * Achieved by doing a depth first search.
     * The dfs treats the graph like undirected to cover all reachable nodes.
     * This is done by checking both inbound and outbound connections of the node.
     * @param node - Node to check for all incoming and outgoing connections
     * */
    private void computeComponents(Node<E> node) {
        nodes.add(node);
        visited.add(node);
        Iterator<Node<E>> out = node.succsOf();
        while (out.hasNext()) {
            Node<E> next = out.next();
            if (!visited.contains(next)) {
                computeComponents(next);
            }
        }
        Iterator<Node<E>> in = node.predsOf();
        while (in.hasNext()) {
            Node<E> prev = in.next();
            if (!visited.contains(prev)) {
                computeComponents(prev);
            }
        }
    }
}
