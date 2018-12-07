package DataStructures;

import java.io.*;
import java.util.*;

public class Graph_BFS_ShortestReachInAGraph {

    public static void main(String[] args) {
        List<Graph> graphs = readInputAndCreateGraphs();

        for (Graph graph : graphs) {
            int[] distances = graph.getDistances();
            for (int j = 0; j < distances.length; j++) {
                System.out.print(distances[j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Read in input.
     * The first line contains an integer, q, the number of queries.
     * Each of the following q sets of lines is as follows:
     *  The first line contains two space-separated integers,
     *   n and m, the number of nodes and the number of edges.
     *  Each of the next m lines contains two space-separated
     *   integers, u and v, describing an edge connecting
     *   node u to node v.
     *  The last line contains a single integer, s, the index
     *   of the starting node.
     */
    private static List<Graph> readInputAndCreateGraphs() {
        List<Graph> graphs = new ArrayList<>();

        Scanner scan = new Scanner(System.in);
        int q = scan.nextInt();
        for (int i=0; i<q; i++) {
            int n = scan.nextInt();
            Node[] nodes = new Node[n];
            for (int j = 0; j < n; j++) {
                nodes[j] = new Node(j + 1);
            }

            int m = scan.nextInt();
            for (int j = 0; j < m; j++) {
                int u = scan.nextInt();
                int v = scan.nextInt();
                Edge e = new Edge(nodes[u - 1], nodes[v - 1]);
                nodes[u - 1].edges.add(e);
                Edge eRev = new Edge(nodes[v - 1], nodes[u - 1]);
                nodes[v - 1].edges.add(eRev);
            }
            int s = scan.nextInt();

            graphs.add(new Graph(nodes, s));
        }

        return graphs;
    }

    private static class Node {
        public int id;
        public int distance;
        public List<Edge> edges = new ArrayList<>();

        public Node(int x) {this.id = x;}
    }

    private static class Edge {
        public Node u;
        public Node v;

        public Edge(Node a, Node b) {
            this.u = a;
            this.v = b;
        }
    }

    private static class Graph {
        private int startNodeId;
        private Node[] nodes;

        private static final int EDGE_LENGTH = 6;

        public Graph(Node[] ns, int s) {
            this.nodes = ns;
            this.startNodeId = s;
        }

        public int[] getDistances() {
            int[] distances = new int[nodes.length-1];
            // initialize distances to all be -1
            for (int i=0; i<distances.length; i++) {
                distances[i] = -1;
            }

            // BFS from start node to every connected node
            Node startNode = nodes[startNodeId-1];
            startNode.distance = 0;
            List<Node> nextToVisit = new ArrayList<>();
            Set<Integer> visited = new HashSet<>();
            nextToVisit.add(startNode);
            visited.add(startNodeId);
            while (!nextToVisit.isEmpty()) {
                Node n = nextToVisit.remove(0);
                if (n.id != startNodeId) {
                    int nDistancesIndex = n.id > startNodeId ? n.id - 2 : n.id - 1;
                    distances[nDistancesIndex] = n.distance * EDGE_LENGTH;
                }
                if (!n.edges.isEmpty()) {
                    for (Edge e : n.edges) {
                        Node u = e.u;
                        Node v = e.v;
                        // sanity check the edge
                        if (u.id != n.id && v.id != n.id) {
                            System.out.println("Found issue with Node " + n.id + " and edge (" + e.u + "," + e.v +")");
                        }
                        if (!visited.contains(v.id)) {
                            v.distance = u.distance+1;
                            nextToVisit.add(v);
                            visited.add(v.id);
                        }
                    }
                }
            }

            return distances;
        }
    }
}

