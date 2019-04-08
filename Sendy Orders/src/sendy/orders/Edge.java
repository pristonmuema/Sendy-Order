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
public class Edge<T> implements Comparable<Edge<T>>{
    /**
     * This holds the source of this edge.
     */
    private T u;

    /**
     * This tells if it is incoming or outgoing.
     */
    private T v;

    /**
     * This holds the destination of this edge.
     */
    private double w;

    /**
     * This holds the weight of the edge.
     */
    private char eType;
    /**
     * This constructor initializes an Edge.
     * @param from
     * @param to
     * @param w
     * @param eType
     */
    public Edge(T from, T to, double w,char eType) {
        this.u = from;
        this.v = to;
        this.w = w;
        this.eType = eType;
    }

    /**
     * This method returns the start object of the edge.
     * @return
     */
    public T from() {
        return u;
    }

    /**
     * This method returns the end object of the edge.
     * @return
     */
    public T to() {
        return v;
    }

    /**
     * This method returns the weight of the edge.
     * @return
     */
    public double weight() {
        return w;
    }

    /**
     * @return the eType
     */
    public char geteType() {
        return eType;
    }

    /**
     * @param eType the eType to set
     */
    public void seteType(char eType) {
        this.eType = eType;
    }

    /**
     * @return the u
     */
    public T getU() {
        return u;
    }

    /**
     * @param u the u to set
     */
    public void setU(T u) {
        this.u = u;
    }

    /**
     * @return the v
     */
    public T getV() {
        return v;
    }

    /**
     * @param v the v to set
     */
    public void setV(T v) {
        this.v = v;
    }

    /**
     * @return the w
     */
    public double getW() {
        return w;
    }

    /**
     * @param w the w to set
     */
    public void setW(double w) {
        this.w = w;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + eType;
        result = prime * result + ((u == null) ? 0 : u.hashCode());
        result = prime * result + ((v == null) ? 0 : v.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        Edge<T> cbj = (Edge<T>)obj;
        if (cbj.getClass() == obj.getClass()) {
            if (this.to() == cbj.to() && this.from() == cbj.from() && this.weight() == cbj.weight())
                return true;
        }
        return false;
    }


    public int compareTo(Edge<T> o) {
        if(this.w > o.w) return 1;
        if(this.w < o.w) return -1;
        else             return 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[ ").append(this.u).append(" -> ")
                .append("[ ").append(this.v).append("]").append(" = ").append(this.w).append("\n");
        return builder.toString();
    }
}

