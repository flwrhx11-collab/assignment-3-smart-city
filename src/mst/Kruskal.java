package mst;

import graph.Edge;
import graph.Graph;
import dsu.DisjointSetUnion;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kruskal {
    public double mstWeight;
    public boolean isConnected;
    public List<Edge> mstEdges;

    public Kruskal(Graph graph) {
        mstWeight = 0;
        isConnected = false;
        mstEdges = new ArrayList<>();

        List<Edge> edges = new ArrayList<>(graph.allEdges);
        Collections.sort(edges);

        DisjointSetUnion dsu = new DisjointSetUnion(graph.vertices);
        int edgesInMst = 0;

        for (Edge edge : edges) {
            if (dsu.union(edge.source, edge.destination)) {
                mstWeight += edge.weight;
                mstEdges.add(edge);
                edgesInMst++;
            }
            if (edgesInMst == graph.vertices - 1) {
                break;
            }
        }

        isConnected = (edgesInMst == graph.vertices - 1) || (graph.vertices <= 1);
    }
}