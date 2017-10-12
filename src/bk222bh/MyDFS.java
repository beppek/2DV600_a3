package bk222bh;

import graphs.DFS;
import graphs.DirectedGraph;
import graphs.Node;

import java.util.*;

public class MyDFS<E> implements DFS {
    private int dfsNum = 1;
    private List<Node<E>> nodes;
    private Set<Node<E>> visited;

    @Override
    public List<Node<E>> dfs(DirectedGraph graph, Node root) {
        nodes = new ArrayList<>();
        visited = new HashSet<>();
        nodes.add(root);
        visited.add(root);
        root.num = dfsNum++;
        Iterator<Node<E>> it = root.succsOf();
        while (it.hasNext()) {
            Node next = it.next();
            if (!visited.contains(next)) {
                dfs(next);
            }
        }
        return nodes;
    }

    @Override
    public List<Node<E>> dfs(DirectedGraph graph) {
        nodes = new ArrayList<>();
        visited = new HashSet<>();
        Iterator<Node<E>> it = graph.iterator();
        while (it.hasNext()) {
            Node node = it.next();
            if (!visited.contains(node)) {
                dfs(node);
            }
        }
        return nodes;
    }

    @Override
    public List<Node<E>> postOrder(DirectedGraph g, Node root) {
        nodes = new ArrayList<>();
        visited = new HashSet<>();
        visited.add(root);
        Iterator<Node<E>> it = root.succsOf();
        while (it.hasNext()) {
            Node next = it.next();
            if (!visited.contains(next)) {
                postOrder(next);
            }
        }
        nodes.add(root);
        root.num = dfsNum++;
        return nodes;
    }

    @Override
    public List<Node<E>> postOrder(DirectedGraph g) {
        nodes = new ArrayList<>();
        visited = new HashSet<>();
        Iterator<Node<E>> it = g.iterator();
        while (it.hasNext()) {
            Node next = it.next();
            if (!visited.contains(next)) {
                postOrder(next);
            }
        }
        return nodes;
    }

    //TODO: implement
    @Override
    public List<Node> postOrder(DirectedGraph g, boolean attach_dfs_number) {
        return null;
    }

    @Override
    public boolean isCyclic(DirectedGraph graph) {
        List<Node<E>> postOrdered = postOrder(graph);
        for (Node<E> node : postOrdered) {
            Iterator<Node<E>> it = node.succsOf();
            while (it.hasNext()) {
                Node<E> next = it.next();
                if (next.num >= node.num) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Topological sort
     * Achieved by reversing the order of the post order sort
     * */
    @Override
    public List<Node<E>> topSort(DirectedGraph graph) {
        List<Node<E>> nodes = postOrder(graph);
        Collections.reverse(nodes);
        return nodes;
    }

    /**
     * Private helper method to avoid duplicate code
     * Goes through each node of the depth first search using recursive approach
     * @param node - Node to check for outgoing edges
     * */
    private void dfs(Node<E> node) {

        node.num = dfsNum++;
        nodes.add(node);
        visited.add(node);
        Iterator<Node<E>> it = node.succsOf();
        while (it.hasNext()) {
            Node<E> next = it.next();
            if (!visited.contains(next)) {
                dfs(next);
            }
        }

    }

    /**
     * Private helper method for postOrder methods
     * Adds each node in post order using recursive approach
     * @param node - Node to check for outgoing edges
     * */
    private void postOrder(Node<E> node) {

        visited.add(node);
        Iterator<Node<E>> it = node.succsOf();
        while (it.hasNext()) {
            Node<E> next = it.next();
            if (!visited.contains(next)) {
                postOrder(next);
            }
        }
        node.num = dfsNum++;
        nodes.add(node);

    }
}
