package main.java.shortestPath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BellmanFord {
    Graph graph;
    private boolean negativeCycle = false;

    public BellmanFord(String filePath) {
	graph = new Graph(filePath);
    }

    public BellmanFord(Graph gr) {
	graph = gr;
    }

    public void computePaths(Vertex source) throws ShortestPathException {
	int numOfVert = graph.getVertices().size();
	source.setMinDistance(0);
	boolean canStopEarly = false;
	for (int i = 1; (i < numOfVert) && (!canStopEarly); i++) {
	    int updatesCount = 0;
	    for (Vertex vert : graph.getVertices().values()) {
		updatesCount += updateVertex(vert);
	    }
	    if (updatesCount == 0 && i > 1) {
		canStopEarly = true;
	    }
	}
	if (graph.isNegativeEdge()) {
	    // possible negative cycle
	    int updatesCount = 0;
	    for (Vertex vert : graph.getVertices().values()) {
		updatesCount += updateVertex(vert);
	    }
	    if (updatesCount > 0) {
		negativeCycle = true;
		throw new ShortestPathException("Negative cycle detected");
	    }
	}
    }

    public void computePaths(int sourceID) throws ShortestPathException {
	computePaths(graph.getVertices().get(sourceID));
    }

    private int updateVertex(Vertex vert) {
	int minWeight = vert.getMinDistance();
	Vertex prev = null;
	for (Edge e : vert.getEdges()) {
	    if (e.getDestination() == vert) {
		if (minWeight > (long) e.getWeight() + e.getSource().getMinDistance()) {
		    minWeight = e.getWeight() + e.getSource().getMinDistance();
		    prev = e.getSource();
		}
	    }
	}
	if (prev != null) {
	    vert.setMinDistance(minWeight);
	    vert.setPrevious(prev);
	    return 1;
	}
	return 0;
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

    public boolean isNegativeCycle() {
	return negativeCycle;
    }
}
