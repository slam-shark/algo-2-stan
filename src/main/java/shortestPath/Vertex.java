package main.java.shortestPath;

import java.util.HashSet;
import java.util.Set;

public class Vertex {

    private final int label;
    private final Set<Edge> edges = new HashSet<Edge>();
    private int minDistance = Integer.MAX_VALUE;
    private Vertex previous = null;

    public Vertex(int label) {
	this.label = label;
    }

    public void addEdge(Edge e) {
	edges.add(e);
    }

    public int getLabel() {
	return label;

    }

    public Set<Edge> getEdges() {
	return edges;
    }

    public int getMinDistance() {
	return minDistance;
    }

    public void setMinDistance(int minDistance) {
	this.minDistance = minDistance;
    }

    public void changeMinDistanceBy(int delta) {
	this.minDistance += delta;
    }

    public void setDistanceToMax() {
	this.minDistance = Integer.MAX_VALUE;
    }

    public Vertex getPrevious() {
	return previous;
    }

    public void setPrevious(Vertex previous) {
	this.previous = previous;
    }

    public void removePrevious() {
	this.previous = null;
    }

    private void removeEdge(Edge e) {
	edges.remove(e);
    }

    public void removeTails() {
	for (Edge e : edges) {
	    e.getDestination().removeEdge(e);
	}
    }

    public void reset() {
	removePrevious();
	setDistanceToMax();
    }

    @Override
    public String toString() {
	return "" + label + "{" + getMinDistance() + "}";
    }

}
