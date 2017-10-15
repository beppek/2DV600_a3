### Depth first search
The depth first search gets a time complexity of O(n + e). This is because we are only iterating over each node (n) once and over each edge (e) once. Once it has been visited it is then marked and not visited a second time. Therefore the time is dependent on the number of nodes + the number of edges.

### Breadth first search
The breadth first search works basically the same as a depth first search, with a few minor differences, mainly in the order of visiting each node and edge. They are still both only visited once however meaning the time complexity is again O(n + e).

### Transitive closure
My implementation of the transitive closure does a depth first search on each node in the graph. However, since each node is visited more than once (first in the loop of the entire graph and then again in the dfs) the time complexity becomes O(n(n + e)).

### Connected components
My solution to connected components treats the graph as undirected and does a depth first search of each node (but in both in and out directions). Each node is still only visited once and each edge is also only visited once. Therefore my connected components solution is also time complexity O(n + e).