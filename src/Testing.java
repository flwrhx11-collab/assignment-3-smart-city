import graph.Graph;
import mst.Kruskal;
import mst.Prim;
import connectivity.TopologicalSort;
import connectivity.Tarjan;
import shortestpath.Dijkstra;

public class Testing {
    public static void main(String[] args) {
        testSingleVertex();
        testDisconnectedGraph();
        testGraphWithCycles();
        testDuplicateWeights();
        testUnreachableVertices();
    }

    private static void testSingleVertex() {
        Graph g = new Graph(1);
        Kruskal k = new Kruskal(g);
        Prim p = new Prim(g);
        if (k.isConnected && p.isConnected && k.mstWeight == p.mstWeight) {
            System.out.println("Single vertex test: PASS");
        }
    }

    private static void testDisconnectedGraph() {
        Graph g = new Graph(4);
        g.addEdge(0, 1, 10, false);
        g.addEdge(2, 3, 10, false);
        Kruskal k = new Kruskal(g);
        Prim p = new Prim(g);
        if (!k.isConnected && !p.isConnected) {
            System.out.println("Disconnected graph test: PASS");
        }
    }

    private static void testGraphWithCycles() {
        Graph g = new Graph(3);
        g.addEdge(0, 1, 1, true);
        g.addEdge(1, 2, 1, true);
        g.addEdge(2, 0, 1, true);
        TopologicalSort topo = new TopologicalSort(g);
        if (topo.hasCycle) {
            System.out.println("Cycle detection test: PASS");
        }
    }

    private static void testDuplicateWeights() {
        Graph g = new Graph(3);
        g.addEdge(0, 1, 5, false);
        g.addEdge(1, 2, 5, false);
        g.addEdge(0, 2, 5, false);
        Kruskal k = new Kruskal(g);
        Prim p = new Prim(g);
        if (k.mstWeight == p.mstWeight && k.mstWeight == 10) {
            System.out.println("Duplicate weights test: PASS");
        }
    }

    private static void testUnreachableVertices() {
        Graph g = new Graph(3);
        g.addEdge(0, 1, 5, true);
        Dijkstra d = new Dijkstra(g, 0);
        if (d.distances[1] == 5.0 && d.distances[2] == Double.POSITIVE_INFINITY) {
            System.out.println("Unreachable vertices test: PASS");
        }
    }
}