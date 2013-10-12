package main.java.shortestPath;

public class Edge {
    private final Vertex source;
    private final Vertex destination;
    private int weight;

    public Edge(Vertex src, Vertex dst, int weight) {
	this.weight = weight;
	if (src.equals(dst))
	    throw new IllegalArgumentException("oops, verticles are same");
	if (src == null || dst == null)
	    throw new IllegalArgumentException("Both vertices are required");
	source = src;
	destination = dst;
	src.addEdge(this);
	dst.addEdge(this);

    }

    public int getWeight() {
	return weight;
    }

    public void setWeight(int newWeight) {
	weight = newWeight;
    }

    public void changeWeightBy(int delta) {
	weight += delta;
    }

    public Vertex getSource() {
	return source;
    }

    public Vertex getDestination() {
	return destination;
    }

    @Override
    public String toString() {
	return String.format("(%4s ->%4s) |%4s", source, destination, weight);
    }

}
