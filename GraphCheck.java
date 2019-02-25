package graph;

import java.io.FileReader;
import java.util.*;

class Graph{
	class Edge{
		String vertex;
		int weight;
		public Edge(String v, int w) {
			this.vertex = v;
			this.weight = w;
		}
		@Override
		public String toString() {
			return "("+vertex+","+weight+")";
		}
	}
	List<Edge> gr[];
	String[] st = new String[] {"India","Canada","Africa","America","China","Russia","Korea","SriLanka","USA","UK"};
	@SuppressWarnings("unchecked")
	public Graph(int edge_num) {
		gr = new LinkedList[edge_num];
		for(int i =0; i <gr.length; i++) {
			gr[i] = new LinkedList<Edge>();
		}
	}
	void addEdge(int a, String b, int c) {
		gr[a].add(0, new Edge(b,c));
	}
	
	@Override
	public String toString() {
		String result = "";
		for(int i =0; i<gr.length; i++)
			result+=st[i]+"==>"+gr[i]+"\n";
		return result;
	}
}

public class GraphCheck {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String[] Adj = new String[] {"Pakistan","Bangladesh", "Nepal"};
//		HashMap<String, String[]> graph = new HashMap<String, String[]>();
//		graph.put("India", Adj);
//		System.out.println(graph);
		
		Graph g = new Graph(10);
		g.addEdge(0, "Pakistan", 1);
		g.addEdge(0, "China", 1);
		g.addEdge(1, "America", 1);
		g.addEdge(3, "Canada", 1);
		System.out.println(g);
		System.out.println(g.gr[0].get(1).weight);
	}
}