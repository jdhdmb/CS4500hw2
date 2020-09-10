import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class Graph {
	class Edge {
		int v; //Ending vertex of the edge
		
		//Constructor
		public Edge(int v) {
			this.v = v;
		}
		@Override
		public String toString() {
			return "("+ v +")";
		}
	}
	List<Edge> G[]; //
	@SuppressWarnings("unchecked")
	//Builds empty graph structure
	public Graph(int n) {
		G = new LinkedList[n]; 
		for(int i = 0; i <G.length; i++) {
			G[i] = new LinkedList<Edge>();
		}
	}
	
	//Checks if the vertices are connected by an edge
	boolean isConnected(int u, int v) {
		for(Edge i: G[u]) {
			if(i.v==v) return true;
		}
		return false;
	}
	
	//Connects two vertices with each other
	void addEdge(int u, int v) {
		G[u].add(0, new Edge(v));
	}
	
	@Override
	//Prints graph
	public String toString() {
		String result= "";
		for(int i = 0; i < G.length; i++) {
			result+=i+"=>"+G[i]+"\n";
		}
		return result;
	}
}

public class Main {
	
	public static boolean isValidConnection(String name) {
		String edgeRegex = "^[a-gA-G] {1}$"; //Regular expression to check for vertex connection
		
		Pattern p = Pattern.compile(edgeRegex);
		
		if(name == null) {
			return false;
		}
		
		Matcher m = p.matcher(name);
		
		return m.matches();
	}
	@SuppressWarnings("resource")
	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		int vertices = 0;  //Number of vertices your game will have
		int edges = 0;  //Number of edges that will connect your vertices
		int weight = 0; //Weight of each vertex (starts at 10 for loop check)
		int[] vertexWeight; //Array to hold each vertex weight
		int vertex1; //First vertex to be connected
		int vertex2; //Second vertex to be connected
		
		//Description of the game
		System.out.println("In this program you will be playing the Money Game. The objective is to make every vertice of your graph"
				+ " have a non-negative value.\nWhen moving money around each time that you push money out of a point it gives $1 to "
				+ "each connected player,\ntherefore if you have 3 edges connected to a point and you give money out you will lose "
				+ "$3 on that point. \nEnd the game with all points having $0 or more and you win the game!\n");
		
		//Loop to validate users input on number of Vertices
		System.out.println("Please enter the number of vertices that you would like to have (between 2-7): ");
		vertices = input.nextInt();
		while(vertices > 7 || vertices < 2) {
			System.out.println("Not a valid input, please try again. The number needs to be between 2 and 7 inclusive.");
			vertices = input.nextInt();
		}
		
		//Loop to validate users input on number of Edges
		System.out.println("Please enter the number of edges you would like to have (must be more than the number of vertices minus 1)");
		edges = input.nextInt();
		while(edges < vertices-1) {
			System.out.println("Not a valid input, please try again. The number must be more than the number of vertices minus 1.");
			edges = input.nextInt();
		}
		
		vertexWeight = new int[vertices]; //Creates the array to track the weight of each vertex on the graph
		
		//Loop to validate user input for vertex weight and add the valid input to the array
		for(int i = 0; i < vertices; i++) {
			System.out.println("Please enter how much money you would like vertex " + (i+1) + " to start with. Must be between -9 and 9.");
			weight = input.nextInt();
			while(weight > 9 || weight < -9) {
				System.out.println("Not a valid input, please try again. The number must be between -9 and 9 inclusive.");
				weight = input.nextInt();
			}
			vertexWeight[i] = weight; //Adds the number of dollars specified by the user to that vertex
		}
		
		Graph g = new Graph(vertices);
		//Loop to setup the edges of your graph
		for(int i = 0; i < edges; i++) {
			System.out.println("What vertex would you like to connect? (Vertex 1, vertex 2...)");
			vertex1 = input.nextInt();
			System.out.println("What vertex would you like to connect " + vertex1 + " with? (Vertex 1, vertex 2...)");
			vertex2 = input.nextInt();
			//Loop to validate input for each of the vertices
			while(vertex1 > vertices || vertex1 < 0 || vertex2 > vertices || vertex2 < 0) {
				System.out.println("Not a valid input, please try again. You must enter valid vertices to connect. "
						+ "What vertex would you like to connect? (Vertex 1, vertex 2...)");
				vertex1 = input.nextInt();
				System.out.println("What vertex would you like to connect " + vertex1 + " with? (Vertex 1, vertex 2...)");
				vertex2 = input.nextInt();
			}
			if(g.isConnected(vertex1, vertex2)) {
				System.out.println("Sorry those 2 vertices are already connected. Try again.");
				i--;
				System.out.println("You have " + (edges-(i+1)) + " edges left to set.\n");
			}
			else if(vertex1==vertex2) {
				System.out.println("Sorry you can not connect a vertex with itself. Try again.");
				i--;
				System.out.println("You have " + (edges-(i+1)) + " edges left to set.");
			}
			else {
				vertex1--;
				vertex2--;
				g.addEdge(vertex1, vertex2);
				System.out.println("You have " + (edges-(i+1)) + " edges left to set.\n");
			}	
		}
		
	}
}
