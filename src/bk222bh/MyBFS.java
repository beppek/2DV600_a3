package bk222bh;

import graphs.BFS;
import graphs.DirectedGraph;
import graphs.Node;

import java.util.*;

public class MyBFS<E> implements BFS {
    private int bfsNum;
    private List<Node<E>> nodes;
    private List<Node<E>> breadth;
    private Set<Node<E>> visited;

    @Override
    public List<Node<E>> bfs(DirectedGraph graph, Node root) {
        bfsNum = 1;
        nodes = new ArrayList<>();
        breadth = new LinkedList<>();
        visited = new HashSet<>();

        breadth.add(root);
        root.num = bfsNum++;
        nodes.add(root);
        visited.add(root);
        bfs();

        return nodes;
    }

    @Override
    public List<Node<E>> bfs(DirectedGraph graph) {
        bfsNum = 1;
        nodes = new ArrayList<>();
        breadth = new LinkedList<>();
        visited = new HashSet<>();
        Iterator<Node<E>> it = graph.iterator();
        while (it.hasNext()) {
            Node<E> node = it.next();
            if (!visited.contains(node)) {
                breadth.add(node);
                node.num = bfsNum++;
                nodes.add(node);
                visited.add(node);
                bfs();
            }

        }

        return nodes;
    }

    /**
     * Private helper method to avoid duplicate code
     * Goes through each breadth level of the breadth first search
     * */
    private void bfs() {
        while (breadth.size() > 0) {
            Node<E> n = breadth.get(breadth.size() - 1);
            breadth.remove(n);
            Iterator<Node<E>> it = n.succsOf();
            while (it.hasNext()) {
                Node<E> next = it.next();
                if (!visited.contains(next)) {
                    next.num = bfsNum++;
                    nodes.add(next);
                    visited.add(next);
                    breadth.add(next);
                }
            }
        }
    }
}
