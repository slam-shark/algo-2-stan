package main.java.shortestPath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class Dijkstra {
    Graph graph;

    public Dijkstra(Graph gr) throws ShortestPathException {
	if (gr.isNegativeEdge())
	    throw new ShortestPathException(
		    "There is negative edge, Dijksta's algorithm correct only on nonnegative edges");
	graph = gr;
    }

    public Dijkstra(String filePath) throws ShortestPathException {
	this(new Graph(filePath));
    }

    public void computePaths(Vertex source) {
	// PriorityQueue<Vertex> heap = new PriorityQueue<Vertex>(11, new
	// distComparator());
	TreeSet<Vertex> heap = new TreeSet<Vertex>(new distComparator());
	source.setMinDistance(0);
	heap.add(source);
	while (!heap.isEmpty()) {
	    // Vertex current = heap.poll();
	    Vertex current = heap.pollFirst();
	    int currDist = current.getMinDistance();
	    for (Edge edge : current.getEdges()) {
		Vertex lookupVert = edge.getDestination();
		if (lookupVert != current) {
		    if ((long) currDist + edge.getWeight() < lookupVert.getMinDistance()) {
			heap.remove(lookupVert);
			lookupVert.setMinDistance(currDist + edge.getWeight());
			lookupVert.setPrevious(current);
			heap.add(lookupVert);
		    }
		}
	    }
	}
    }

    public void computePaths(int sourceID) {
	computePaths(graph.getVertices().get(sourceID));
    }

    public List<Vertex> getShortestPathTo(Vertex destination) {
	List<Vertex> path = new ArrayList<Vertex>();
	for (Vertex vertex = destination; vertex != null; vertex = vertex.getPrevious())
	    path.add(vertex);
	Collections.reverse(path);
	return path;
    }

    public List<Vertex> getShortestPathTo(int destinationID) {
	return getShortestPathTo(graph.getVertices().get(destinationID));
    }

    public int getShortestDistanceTo(Vertex destination) {
	return destination.getMinDistance();
    }

    public int getShortestDistanceTo(int destinationID) {
	return getShortestDistanceTo(graph.getVertices().get(destinationID));
    }

    public List<Vertex> getMinimalShortestPath() {
	return getShortestPathTo(getMinimalDistanceVertex());
    }

    public Vertex getMinimalDistanceVertex() {
	Vertex tempMinimal = graph.getVertices().values().iterator().next();
	for (Vertex current : graph.getVertices().values()) {
	    if (current.getMinDistance() < tempMinimal.getMinDistance())
		tempMinimal = current;
	}
	return tempMinimal;
    }

    public int getMinimalDistance() {
	return getShortestDistanceTo(getMinimalDistanceVertex());
    }

    class distComparator implements Comparator<Vertex> {
	@Override
	public int compare(Vertex o1, Vertex o2) {
	    int cmp = Integer.compare(o1.getMinDistance(), o2.getMinDistance());
	    if (cmp != 0)
		return cmp;
	    return Integer.compare(o1.getLabel(), o2.getLabel());
	}
    }
}
