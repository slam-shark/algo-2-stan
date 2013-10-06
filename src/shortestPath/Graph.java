package shortestPath;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Graph {
    private Map<Integer, Vertex> vertices = new TreeMap<Integer, Vertex>();
    private List<Edge> edges = new ArrayList<Edge>();
    private boolean negativeEdge = false;

    public Graph(String filePath) {
	getArray(filePath);
    }

    private void getArray(String filePath) {
	try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	    String line = br.readLine(); // we don't need this line
	    while ((line = br.readLine()) != null) {
		String[] split = line.trim().split("(\\s)+");
		int vSrcId = Integer.parseInt(split[0]);
		Vertex vSrc = vertices.containsKey(vSrcId) ? vertices.get(vSrcId) : new Vertex(vSrcId);
		int vDstId = Integer.parseInt(split[1]);
		Vertex vDst = vertices.containsKey(vDstId) ? vertices.get(vDstId) : new Vertex(vDstId);
		int weight = Integer.parseInt(split[2]);
		Edge e = new Edge(vSrc, vDst, weight);
		edges.add(e);
		vertices.put(vDstId, vDst);
		vertices.put(vSrcId, vSrc);
		if (weight < 0)
		    negativeEdge = true;
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public Map<Integer, Vertex> getVertices() {
	return vertices;
    }

    public void addVertex(Vertex v) {
	vertices.put(v.getLabel(), v);

    }

    public List<Edge> getEdges() {
	return edges;
    }

    public void addEdge(Edge e) {
	edges.add(e);
    }

    public boolean isNegativeEdge() {
	return negativeEdge;
    }
    
    public void setNegativeEdge(boolean b){
	negativeEdge = b;
    }
    
    public void reset(){
	for(Vertex v:vertices.values()){
	    v.reset();
	}
    }
}
