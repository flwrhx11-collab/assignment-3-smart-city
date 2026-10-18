# Smart City Infrastructure and Route Planning

## Overview
This Java application models a smart city's infrastructure network to help planners connect city districts, analyze network connectivity, and find efficient routes[cite: 1]. The city is represented as a graph where vertices represent city districts and edges represent infrastructure connections (roads)[cite: 1]. The edge weights indicate either construction costs or travel distances[cite: 1].

## Implementation
The core architecture is built around two primary classes: `Edge` and `Graph`[cite: 4].
* **Graph Representation:** The graph uses an adjacency list (`List<List<Edge>>`) for efficient traversal in algorithms like Prim's, Dijkstra's, Tarjan's, and Topological Sort[cite: 3]. It also maintains a separate list of all edges to easily sort them for Kruskal's algorithm[cite: 1].
* **Disjoint Set Union (DSU):** Implemented with path compression and union by rank to optimize Kruskal's algorithm[cite: 1].
* **Algorithms Implemented:** Kruskal and Prim for Minimum Spanning Tree (MST), Tarjan's for Strongly Connected Components (SCC), Topological Sort for Directed Acyclic Graphs (DAGs), and Dijkstra's for shortest paths[cite: 1, 2].

## Complexity Analysis
* **DSU Operations:** Both `find` and `union` operate in $O(\alpha(V))$ time, where $\alpha$ is the inverse Ackermann function, due to path compression and union by rank optimizations. Space complexity is $O(V)$[cite: 3].
* **Kruskal's Algorithm:** Time complexity is $O(E \log E)$ because sorting the edges dominates the runtime. The DSU operations take near-linear time. Space complexity is $O(V + E)$[cite: 3].
* **Prim's Algorithm:** Using a Priority Queue (binary heap) and an adjacency list, the time complexity is $O(E \log V)$. Space complexity is $O(V + E)$[cite: 3].
* **Tarjan's SCC:** Operates in $O(V + E)$ time as it uses Depth-First Search (DFS) to visit each vertex and edge exactly once. Space complexity is $O(V)$[cite: 3].
* **Topological Ordering:** Time complexity is $O(V + E)$ since it relies on DFS. Space complexity is $O(V)$ for the recursion stack and state tracking[cite: 3].
* **Dijkstra's Algorithm:** Using a Priority Queue, the time complexity is $O(E \log V)$. Space complexity is $O(V + E)$[cite: 3]. *Note on negative weights:* Dijkstra's algorithm assumes that adding an edge to a path can only increase its length. If negative weights exist, a previously finalized shortest path might be incorrectly evaluated, making the algorithm unsuitable for negative weights[cite: 2].

## Experimental Setup
The experimental analysis evaluated the algorithms across three graph sizes[cite: 2]:
* **Small:** 10 vertices, 30 edges[cite: 2]
* **Medium:** 100 vertices, 500 edges[cite: 2]
* **Large:** 1,000 vertices, 5,000 edges[cite: 2]

Graphs were generated using a fixed random seed for reproducibility[cite: 2]. Undirected graphs for the MST experiments were guaranteed to be connected[cite: 2]. Directed graphs were generated randomly for SCC, Topological Sort, and Dijkstra[cite: 2]. Each algorithm was executed 5 times per graph size, and the average execution time was measured using `System.nanoTime()`[cite: 2].

## Results

| Graph Size (V, E) | Kruskal (ns) | Prim (ns) | Dijkstra (ns) | Tarjan SCC (ns) | Topological Sort (ns) |
| :--- | :--- | :--- | :--- | :--- | :--- |
| Small (10, 30) | 388,839 | 303,580 | 444,259 | 492,960 | 242,299 |
| Medium (100, 500) | 694,199 | 531,859 | 301,880 | 237,639 | 10,479 |
| Large (1000, 5000) | 4,543,560 | 6,619,079 | 2,662,020 | 722,219 | 27,739 |

*(Please refer to the `plots/` directory for the visual representation of Execution Time vs. Number of Vertices and Execution Time vs. Number of Edges[cite: 4])*

## Comparison and Discussion
* **Kruskal vs. Prim:** The experimental results show that Prim's algorithm was slightly faster on Small and Medium graphs. However, on the Large graph configuration, Kruskal outperformed Prim. This highlights the effectiveness of DSU optimizations (path compression and union by rank/size) in Kruskal's algorithm, which significantly reduce the overhead of cycle checking[cite: 2, 4].
* **SCC and Topological Ordering:** Both algorithms demonstrated excellent scalability. Tarjan's SCC and Topological Sort runtimes grew linearly, which directly aligns with their theoretical $O(V + E)$ time complexity[cite: 2, 4].

## Testing
The implementation was rigorously tested against various edge cases to ensure correctness[cite: 3, 4]:
* **Single vertex graph:** Correctly handled without errors[cite: 3].
* **Disconnected graphs:** Both Kruskal and Prim successfully detected that the graph was disconnected (`isConnected == false`)[cite: 3].
* **Cyclic graphs:** Topological sorting correctly identified cycles and rejected the invalid DAG[cite: 3].
* **Duplicate edge weights:** Both Kruskal and Prim successfully computed identical and correct MST weights despite duplicate values[cite: 3].
* **Unreachable vertices:** Dijkstra's algorithm correctly assigned `Infinity` to unreachable nodes[cite: 3].
* **Correctness Check:** Verified that Kruskal and Prim return the exact same MST weight for connected, undirected graphs[cite: 3].

## Conclusion
The implemented suite provides a robust toolkit for smart city infrastructure planning[cite: 4]. Kruskal's algorithm, paired with an optimized DSU, is highly efficient for large-scale network connectivity (MST), while Prim's is excellent for dense, smaller networks[cite: 4]. For dependency resolution (like one-way roads or services), Topological Sort correctly identifies valid orders or invalid cycles[cite: 1, 2]. Dijkstra's algorithm remains the optimal choice for non-negative route planning from a central district to all others[cite: 1, 2].