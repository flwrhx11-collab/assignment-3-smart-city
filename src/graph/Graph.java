package graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    public int vertices;
    public List<List<Edge>> adjList;
    public List<Edge> allEdges;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.adjList = new ArrayList<>(vertices);
        this.allEdges = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, double weight, boolean isDirected) {
        Edge edge = new Edge(source, destination, weight);
        adjList.get(source).add(edge);
        allEdges.add(edge);

        if (!isDirected) {
            Edge reverseEdge = new Edge(destination, source, weight);
            adjList.get(destination).add(reverseEdge);
        }
    }
}