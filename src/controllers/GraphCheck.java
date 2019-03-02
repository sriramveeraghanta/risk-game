package controllers;
import java.io.FileReader;
import java.util.*;

//import org.json.JSONArray;
//import org.json.JSONObject;

/**
 * Graph class is used to create the connected countries and
 *  its sub continent as well.It is reading the data from map_data.json file under resources folder
 * @author Shabnam Hasan
 * @category Graph
 *
 */ 
class Graph{
	/**
	 * Edge class is used to add the vertex , i.e country name and its weight, i.e the distance
	 * @param vertex adjacent country name
	 * @param weight the distance between the countries, if its adjacent ,it's value is 1
	 * 
	 *
	 */
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
	/**
	 * Constructor Graph is used to initialize the graph adjacency list with the given number of countries
	 * @param gr adjacency list with type Edge
	 * @param country_list array of countries
	 */
	List<Edge> gr[];
	String[] country_list = new String[42];
	@SuppressWarnings("unchecked")
	public Graph(int edge_num) {
		gr = new LinkedList[edge_num];
		for(int i =0; i <gr.length; i++) {
			gr[i] = new LinkedList<Edge>();
		}
	}
	/**
	 * 
	 * @param a index in adjacency list, country names are taken from country_list array
	 * @param b adjacent country
	 * @param c weight i.e the distance 
	 */
	void addEdge(int a, String b, int c) {
		gr[a].add(0, new Edge(b,c));
	}
	
	@Override
	public String toString() {
		String result = "";
		for(int i =0; i<gr.length; i++)
			result+=country_list[i]+"==>"+gr[i]+"\n";
		return result;
	}
	/**
	 * getArray() method is used to parse the JSON file map_data.json
	 *  to extract the country and its adjacent country name
	 */
//	@SuppressWarnings("deprecation")
//	public void getArray() {
//		JSONParser parser = new JSONParser();
//		try {
//			Object fileReaderObject = parser.parse
//					(new FileReader("C:\\Users\\admin\\git\\SOEN_6441_Project_G40\\src\\resources\\map_data.json"));
//			JSONObject fileReaderJsonObject = (JSONObject)fileReaderObject;
//			JSONArray countryListJsonArray = (JSONArray) fileReaderJsonObject.get("countries");
//			for (int i = 0; i < countryListJsonArray.size(); i++)
//			{
//			    Object countryObject = countryListJsonArray.get(i);
//			    JSONObject countryJsonObject = (JSONObject) countryObject;
//			    country_list[i] = (String) countryJsonObject.get("name");
//			    JSONArray adjacentJsonArray = (JSONArray) countryJsonObject.get("neighbor");
//			    Iterator<String> iterator = adjacentJsonArray.iterator();
//			    while(iterator.hasNext()) {
//			    	this.addEdge(i, iterator.next(), 1);
//			    }
//			}
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
/**
 * GraphCheck is the main class used to initialize and to parse the JSON
 * @author Shabnam Hasan
 *
 */
	public class GraphCheck {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Graph g = new Graph(42);
		//g.getArray();
		
		System.out.println(g);
	}
}