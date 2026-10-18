package connectivity;

import graph.Edge;
import graph.Graph;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopologicalSort {
    public List<Integer> order;
    public boolean hasCycle;

    public TopologicalSort(Graph graph) {
        order = new ArrayList<>();
        hasCycle = false;
        int n = graph.vertices;
        int[] state = new int[n]; // 0 = unvisited, 1 = visiting, 2 = visited

        for (int i = 0; i < n; i++) {
            if (state[i] == 0) {
                if (dfs(graph, i, state)) {
                    hasCycle = true;
                    order.clear();
                    return;
                }
            }
        }
        Collections.reverse(order);
    }

    private boolean dfs(Graph graph, int u, int[] state) {
        state[u] = 1;
        for (Edge edge : graph.adjList.get(u)) {
            int v = edge.destination;
            if (state[v] == 1) return true;
            if (state[v] == 0 && dfs(graph, v, state)) return true;
        }
        state[u] = 2;
        order.add(u);
        return false;
    }
}