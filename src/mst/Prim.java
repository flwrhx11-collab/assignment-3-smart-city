package mst;

import graph.Edge;
import graph.Graph;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;

public class Prim {
    public double mstWeight;
    public boolean isConnected;
    public List<Edge> mstEdges;

    public Prim(Graph graph) {
        mstWeight = 0;
        isConnected = false;
        mstEdges = new ArrayList<>();

        if (graph.vertices == 0) {
            isConnected = true;
            return;
        }

        boolean[] visited = new boolean[graph.vertices];
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        visited[0] = true;
        pq.addAll(graph.adjList.get(0));

        int visitedCount = 1;

        while (!pq.isEmpty() && visitedCount < graph.vertices) {
            Edge edge = pq.poll();

            if (visited[edge.destination]) {
                continue;
            }

            visited[edge.destination] = true;
            mstWeight += edge.weight;
            mstEdges.add(edge);
            visitedCount++;

            for (Edge nextEdge : graph.adjList.get(edge.destination)) {
                if (!visited[nextEdge.destination]) {
                    pq.add(nextEdge);
                }
            }
        }

        isConnected = (visitedCount == graph.vertices);
    }
}