package shortestPath;

import main.java.APSP.Johnsons;

public class Go {

    static long timer = System.currentTimeMillis();

    public static void main(String[] args) {

	getTime();
	String filePath = "large.txt";
	Graph gr = new Graph(filePath);
	gr.setNegativeEdge(false);
	System.out.println("graph loaded: "+getTime());

	Johnsons j = new Johnsons(gr);
	System.out.println("J created: "+getTime());
	// int start = 134;
	// int end = 434;

	// d.computePaths(start);
	try {
	    j.computePaths();
	    System.out.println("TIME: "+getTime());
	    System.out.println("Result: "+j.getMinimumDistance());
	} catch (ShortestPathException e) {
	    System.out.println("Negative cycle");
	}
	

    }

    public static long getTime() {
	long tmpTime = (System.currentTimeMillis() - timer) / 1;
	timer = System.currentTimeMillis();
	return tmpTime;

    }
}
