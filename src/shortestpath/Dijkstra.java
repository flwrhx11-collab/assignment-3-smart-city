package shortestpath;

import graph.Edge;
import graph.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
    public double[] distances;
    public int[] previous;

    private static class Node implements Comparable<Node> {
        int vertex;
        double distance;

        Node(int vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Double.compare(this.distance, other.distance);
        }
    }

    public Dijkstra(Graph graph, int source) {
        int n = graph.vertices;
        distances = new double[n];
        previous = new int[n];

        Arrays.fill(distances, Double.POSITIVE_INFINITY);
        Arrays.fill(previous, -1);

        distances[source] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(source, 0.0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;

            if (current.distance > distances[u]) {
                continue;
            }

            for (Edge edge : graph.adjList.get(u)) {
                int v = edge.destination;
                double weight = edge.weight;

                if (distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                    previous[v] = u;
                    pq.add(new Node(v, distances[v]));
                }
            }
        }
    }

    public List<Integer> getPath(int target) {
        List<Integer> path = new ArrayList<>();
        if (distances[target] == Double.POSITIVE_INFINITY) {
            return path;
        }
        for (int at = target; at != -1; at = previous[at]) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}