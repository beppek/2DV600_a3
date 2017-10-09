package bk222bh;

import graphs.DirectedGraph;
import graphs.GML;

public class MyGML<E> extends GML<E> {
    public MyGML(DirectedGraph<E> dg) {
        super(dg);
    }

    @Override
    public String toGML() {
        return null;
    }
}
