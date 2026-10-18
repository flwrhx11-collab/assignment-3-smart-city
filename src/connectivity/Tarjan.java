package connectivity;

import graph.Edge;
import graph.Graph;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Tarjan {
    private int id;
    private int[] ids;
    private int[] low;
    private boolean[] onStack;
    private Stack<Integer> stack;
    public List<List<Integer>> sccs;

    public Tarjan(Graph graph) {
        int n = graph.vertices;
        id = 0;
        ids = new int[n];
        low = new int[n];
        onStack = new boolean[n];
        stack = new Stack<>();
        sccs = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            ids[i] = -1;
        }

        for (int i = 0; i < n; i++) {
            if (ids[i] == -1) {
                dfs(i, graph);
            }
        }
    }

    private void dfs(int at, Graph graph) {
        stack.push(at);
        onStack[at] = true;
        id++;
        ids[at] = id;
        low[at] = id;

        for (Edge edge : graph.adjList.get(at)) {
            int to = edge.destination;
            if (ids[to] == -1) {
                dfs(to, graph);
                low[at] = Math.min(low[at], low[to]);
            } else if (onStack[to]) {
                low[at] = Math.min(low[at], ids[to]);
            }
        }

        if (ids[at] == low[at]) {
            List<Integer> scc = new ArrayList<>();
            for (int node = stack.pop(); ; node = stack.pop()) {
                onStack[node] = false;
                scc.add(node);
                if (node == at) break;
            }
            sccs.add(scc);
        }
    }
}