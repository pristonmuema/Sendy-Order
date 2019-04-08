/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendy.orders;

/**
 *
 * @author PRISTON
 */
import java.util.ArrayList;

/**
 * This class provides an abstract data type for a Graph.
 *
 *
 * @param <T>
 *         Type of data that vertices in the graph will be holding.
 */
public class Graph<T> {
    public double deltaWeight = 0.0;
    /**
     * This holds the number of vertex in the graph.
     */
    private int V;
    /**
     * This holds the number of Vertices in the graph.
     */
    private int E;
    /**
     * This holds array of Edges of type T.
     */
    private ArrayList<Vertex<T>> vertices;

    /**
     * This initializes the graph with V vertices and values.
     *
     * @param V
     * @param values
     */
    @SuppressWarnings("unchecked")
    public Graph(int V, T[] values) {
        this.V = V;
        // Array is 1 based indexing.
        //this.vertices = new Vertex[V + 1];
        this.vertices = new ArrayList<Vertex<T>>();
        vertices.add(null);
        //this.adjMatrix = new int[V + 1][V + 1];
        for (int i = 1, j = 0; i < V + 1; i++, j++) {
            this.vertices.add(i, new Vertex<T>(values[j]));

        }
    }

    /**
     * this method is used to add new vertex to graph
     */
    public Vertex<Integer> addVertex(Vertex<Integer> newVertex) {
        this.V = this.V + 1;
        this.vertices.add((Vertex<T>) newVertex);
        return newVertex;
    }

    /**
     * This method adds an edge between two vertices in a graph.
     *
     * @param u
     * @param v
     * @param w
     */
    public void addEdge(int u, int v, double w) {
        Vertex<T> from = this.vertices.get(u);
        Vertex<T> to = this.vertices.get(v);
//        for (Edge<Vertex<T>> edge : from.sortedAdjacencyList()) {
//            if (edge.from() == from && edge.to()==to){
//                System.out.println("simlar edgle detected");
//                if (edge.getW() > w) {
//                    edge.setW(w);
//                    return;
//                }
//            }
//        }

        from.sortedAdjacencyList().add(new Edge<Vertex<T>>(from, to, w, 'o'));
//        to.sortedAdjacencyList().add(new Edge<Vertex<T>>(to, from, w, 'i'));
        E++;
    }

    public void addEdge(Edge<Vertex<Integer>> edge) {
        addEdge(edge.from().getValue(), edge.to().getValue(), edge.weight());

    }

    /**
     * This method returns the number of vertices in the graph.
     *
     * @return
     */
    public int noOfVertices() {
        return V;
    }

    /**
     * This method returns the array of vertices.
     *
     * @return
     */
    public ArrayList<Vertex<T>> vertices() {
        return this.vertices;
    }

    /**
     * This method returns the number of edges in the graph.
     *
     * @return
     */
    public int noOfEdges() {
        return E;
    }


}

