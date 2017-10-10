package bk222bh;

import graphs.DirectedGraph;
import graphs.GML;

import java.util.Iterator;

public class MyGML<E> extends GML<E> {
    public MyGML(DirectedGraph<E> dg) {
        super(dg);
    }

    @Override
    public String toGML() {
        StringBuilder nodeSb = new StringBuilder();
        StringBuilder edgeSb = new StringBuilder();
        Iterator it = graph.iterator();
        while (it.hasNext()) {
            MyNode node = (MyNode) it.next();
            nodeSb.append("\n\tnode [");
            nodeSb.append("\n\t\tid " + node.hashCode());
            nodeSb.append("\n\t\tlabel " + node.toString());
            nodeSb.append("\n\t]");
            Iterator succsIt = node.succsOf();
            while (succsIt.hasNext()) {
                MyNode succs = (MyNode) succsIt.next();
                edgeSb.append("\n\tedge [");
                edgeSb.append("\n\t\tsource " + node.hashCode());
                edgeSb.append("\n\t\ttarget " + succs.hashCode());
                edgeSb.append("\n\t]");
            }
        }
        String gml = "graph [" + nodeSb.toString() + edgeSb.toString() + "\n]";
        return gml;
    }
}
