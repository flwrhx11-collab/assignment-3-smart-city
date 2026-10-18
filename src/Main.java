import graph.Graph;
import graph.GraphGenerator;
import mst.Kruskal;
import mst.Prim;
import shortestpath.Dijkstra;
import connectivity.Tarjan;
import connectivity.TopologicalSort;

public class Main {
    public static void main(String[] args) {
        int[][] sizes = {
                {10, 30},
                {100, 500},
                {1000, 5000}
        };

        System.out.println("--- Experimental Analysis ---");

        for (int[] size : sizes) {
            int v = size[0];
            int e = size[1];
            System.out.printf("\nGraph: %d vertices, %d edges\n", v, e);

            long timeKruskal = 0;
            long timePrim = 0;
            long timeDijkstra = 0;
            long timeTarjan = 0;
            long timeTopo = 0;

            int runs = 5;

            for (int i = 0; i < runs; i++) {
                Graph undirectedGraph = GraphGenerator.generateConnectedUndirectedGraph(v, e);
                Graph directedGraph = GraphGenerator.generateDirectedGraph(v, e);

                long startKruskal = System.nanoTime();
                Kruskal kruskal = new Kruskal(undirectedGraph);
                timeKruskal += (System.nanoTime() - startKruskal);

                long startPrim = System.nanoTime();
                Prim prim = new Prim(undirectedGraph);
                timePrim += (System.nanoTime() - startPrim);

                long startDijkstra = System.nanoTime();
                Dijkstra dijkstra = new Dijkstra(directedGraph, 0);
                timeDijkstra += (System.nanoTime() - startDijkstra);

                long startTarjan = System.nanoTime();
                Tarjan tarjan = new Tarjan(directedGraph);
                timeTarjan += (System.nanoTime() - startTarjan);

                long startTopo = System.nanoTime();
                TopologicalSort topo = new TopologicalSort(directedGraph);
                timeTopo += (System.nanoTime() - startTopo);
            }

            System.out.printf("Average Kruskal time: %,d ns\n", timeKruskal / runs);
            System.out.printf("Average Prim time: %,d ns\n", timePrim / runs);
            System.out.printf("Average Dijkstra time: %,d ns\n", timeDijkstra / runs);
            System.out.printf("Average Tarjan SCC time: %,d ns\n", timeTarjan / runs);
            System.out.printf("Average Topological Sort time: %,d ns\n", timeTopo / runs);
        }
    }
}