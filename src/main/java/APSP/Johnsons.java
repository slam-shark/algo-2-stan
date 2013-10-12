package main.java.APSP;

import main.java.shortestPath.BellmanFord;
import main.java.shortestPath.Dijkstra;
import main.java.shortestPath.Edge;
import main.java.shortestPath.Go;
import main.java.shortestPath.Graph;
import main.java.shortestPath.ShortestPathException;
import main.java.shortestPath.Vertex;

public class Johnsons {
    Graph graph;
    int[] vertValues;
    int[][] paths;
    int minimum = Integer.MAX_VALUE;

    public Johnsons(String filePath) {
	this(new Graph(filePath));
    }

    public Johnsons(Graph gr) {
	graph = gr;
	vertValues = new int[gr.getVertices().size() + 1];
    }

    public void computePaths() throws ShortestPathException {
	// add new vertex '0' and edges to all other vertices -> form G' from G
	Vertex vSrc = formNewGraph();
	// run Bellman-Ford algorithm on G'
	runBellmanFordFrom(vSrc);
	// System.out.println("BELLMAN-FORD: DONE");
	// remove '0' vertex & every it's edge
	vSrc.removeTails();
	System.out.println("removed tails: " + Go.getTime());
	// for (int i = graph.getEdges().size() - 1; i > 0; i--)
	// if (vSrc.getEdges().contains(graph.getEdges().get(i)))
	// graph.getEdges().set(i, null);
	// else break;
	graph.getEdges().removeAll(vSrc.getEdges());
	System.out.println("removed edges: " + Go.getTime());
	graph.getVertices().remove(vSrc.getLabel());

	// calculate shortest paths for each vertex
	for (Vertex vert : graph.getVertices().values()) {
	    vertValues[vert.getLabel()] = vert.getMinDistance();
	}
	// calculate new weights of every edge (now they all will be
	// nonnegative!)
	for (Edge edge : graph.getEdges()) {
	    edge.changeWeightBy(vertValues[edge.getSource().getLabel()] - vertValues[edge.getDestination().getLabel()]);
	}
	graph.setNegativeEdge(false);
	// run Dijkstra's algorithm with new (nonnegative) edge lengths
	paths = new int[graph.getVertices().size() + 1][graph.getVertices().size() + 1];
	System.out.println("allocated");
	Dijkstra dijkstra = new Dijkstra(graph);
	for (Vertex source : graph.getVertices().values()) {
	    graph.reset(); // if commented - result value twice bigger needed
			   // if uncommented - works too long:(
	    int sId = source.getLabel();
	    dijkstra.computePaths(source);
	    for (Vertex dest : graph.getVertices().values()) {
		if (source != dest) {
		    int dId = dest.getLabel();
		    // check integer overflow
		    if ((long) dijkstra.getShortestDistanceTo(dest) - vertValues[sId] + vertValues[dId] >= Integer.MAX_VALUE) {
			paths[sId][dId] = Integer.MAX_VALUE;
		    } else {
			paths[sId][dId] = dijkstra.getShortestDistanceTo(dest) - vertValues[sId] + vertValues[dId];
			if (paths[sId][dId] < minimum)
			    minimum = paths[sId][dId];
		    }
		}
	    }
	}
    }

    public int getMinimumDistance() {
	return minimum;
    }

    private Vertex formNewGraph() {
	Vertex vSrc = new Vertex(0);
	for (Vertex vDst : graph.getVertices().values()) {
	    Edge e = new Edge(vSrc, vDst, 0);
	    graph.addEdge(e);
	}
	graph.addVertex(vSrc);
	return vSrc;
    }

    private void runBellmanFordFrom(Vertex source) throws ShortestPathException {
	BellmanFord bf = new BellmanFord(graph);
	bf.computePaths(source);
    }

}
