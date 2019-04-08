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
import java.util.PriorityQueue;

public class Vertex<T> implements Comparable{
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((obj == null) ? 0 : obj.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        if (this.obj == null) {
            if (other.obj != null)
                return false;
        } else if (!this.obj.equals(other.obj))
            return false;
        return true;
    }

    /**
     * This holds the data that vertex is storing.
     */
    private T obj;

    /**
     * This holds the list of edges that starts from this vertex.
     */
    private PriorityQueue<Edge<Vertex<T>>> adj1;

    // Visited or not
    private boolean isVisited = false;

    /**
     * This constructor initializes the vertex with a value.
     *
     * @param value
     */
    public Vertex(T value) {
        this.obj = value;
        this.adj1 = new PriorityQueue<Edge<Vertex<T>>>();
    }

    /**
     * This method returns the value the vertex is holding.
     *
     * @return
     */
    public T getValue() {
        return obj;
    }

    /**
     * This method returns the list of edges which have this vertex has starting
     * point.
     *
     * @return
     */

    public PriorityQueue<Edge<Vertex<T>>> sortedAdjacencyList() {
        return adj1;
    }


    /**
     * This method returns the string interpretation of the value stored in the
     * vertex.
     */
    public String toString() {
        return obj.toString();
    }

    /**
     * @return the isVisited
     */
    public boolean isVisited() {
        return isVisited;
    }

    /**
     * @param isVisited the isVisited to set
     */
    public void setVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public int compareTo(Object o) {
        return 0;
    }
}

